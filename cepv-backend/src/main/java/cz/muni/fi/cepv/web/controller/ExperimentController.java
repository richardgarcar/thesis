package cz.muni.fi.cepv.web.controller;

import cz.muni.fi.cepv.domain.Experiment;
import cz.muni.fi.cepv.repository.ExperimentRepository;
import cz.muni.fi.cepv.querydsl.ExperimentQueryDsl;
import cz.muni.fi.cepv.web.LinkUtil;
import cz.muni.fi.cepv.web.exception.ResourceNotFoundException;
import cz.muni.fi.cepv.web.resoureceassambler.ExperimentResourceAssembler;
import cz.muni.fi.cepv.web.to.ExperimentTO;
import cz.muni.fi.cepv.web.to.NewExperimentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author xgarcar
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class ExperimentController {

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private ExperimentResourceAssembler experimentResourceAssembler;

    @RequestMapping(value = LinkUtil.EXPERIMENTS, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Experiment>>> getExperiments(final Pageable pageable, final PagedResourcesAssembler<Experiment> assembler) {

        final Page<Experiment> experiments =  experimentRepository.findAll(pageable);

        final PagedResources<Resource<Experiment>> pagedExperimentResources = assembler.toResource(experiments, experimentResourceAssembler);
        return new ResponseEntity<>(pagedExperimentResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT, method = RequestMethod.GET)
    public HttpEntity<Resource<Experiment>> getExperiment(@PathVariable final Long experimentId) {

        final Experiment experiment =  experimentRepository.findOne(experimentId);
        if (experiment == null) {
            throw new ResourceNotFoundException("Experiment with provided id '" + experimentId + "' does not exist.");
        }

        return new ResponseEntity<>(experimentResourceAssembler.toResource(experiment), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENTS_FILTER, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Experiment>>> searchFilteredExperiments(
            @RequestParam(required = false) final String name, @RequestParam(required = false) final String description,
            @RequestParam(required = false) final Date gtStart, @RequestParam(required = false) final Date ltStart,
            @RequestParam(required = false) final Date gtEnd, @RequestParam(required = false) final Date ltEnd,
            final Pageable pageable, final PagedResourcesAssembler<Experiment> assembler) {

        final Page<Experiment> experiments = experimentRepository.
                findAll(ExperimentQueryDsl.filter(name, description, gtStart, ltStart, gtEnd, ltEnd), pageable);

        final Link link = new Link(new UriTemplate(linkTo(methodOn(ExperimentController.class).
                searchFilteredExperiments(name, description, gtStart, ltStart, gtEnd, ltEnd, pageable, assembler)).
                toUriComponentsBuilder().build().toUriString(), LinkUtil.getFilteredExperimentsTemplateVariables()), "self");

        final PagedResources<Resource<Experiment>> pagedExperimentResources = assembler.toResource(experiments, experimentResourceAssembler, link);
        return new ResponseEntity<>(pagedExperimentResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENTS, method = RequestMethod.POST)
    public HttpEntity<Void> createExperiment(@Valid @RequestBody final NewExperimentTO experimentTO) {

        final Experiment experiment = new Experiment(experimentTO.getName(), experimentTO.getDescription(), experimentTO.getStart(), experimentTO.getEnd());
        final Experiment savedExperiment =  experimentRepository.save(experiment);

        Resource<Experiment> resource = experimentResourceAssembler.toResource(savedExperiment);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT, method = RequestMethod.PUT)
    public HttpEntity<Void> fullUpdateExperiment(@PathVariable final Long experimentId,
                                                 @Valid @RequestBody final ExperimentTO experimentTO) {

        final Experiment originalExperiment = experimentRepository.findOne(experimentId);
        if (originalExperiment == null) {
            throw new ResourceNotFoundException("Experiment with provided id '" + experimentId + "' does not exist.");
        }
        originalExperiment.setName(experimentTO.getName());
        originalExperiment.setDescription(experimentTO.getDescription());
        originalExperiment.setStart(experimentTO.getStart());
        originalExperiment.setEnd(experimentTO.getEnd());

        final Experiment updatedExperiment =  experimentRepository.save(originalExperiment);

        Resource<Experiment> resource = experimentResourceAssembler.toResource(updatedExperiment);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT, method = RequestMethod.PATCH)
    public HttpEntity<Void> partialUpdateExperiment(@PathVariable final Long experimentId,
                                                    @Valid  @RequestBody final ExperimentTO experimentTO) {

        final Experiment originalExperiment = experimentRepository.findOne(experimentId);
        if (originalExperiment == null) {
            throw new ResourceNotFoundException("Experiment with provided id '" + experimentId + "' does not exist.");
        }
        if (experimentTO.getUpdatedFields().contains(ExperimentTO.ExperimentUpdatableField.name)) {
            originalExperiment.setName(experimentTO.getName());
        }
        if (experimentTO.getUpdatedFields().contains(ExperimentTO.ExperimentUpdatableField.description)) {
            originalExperiment.setDescription(experimentTO.getDescription());
        }
        if (experimentTO.getUpdatedFields().contains(ExperimentTO.ExperimentUpdatableField.start)) {
            originalExperiment.setStart(experimentTO.getStart());
        }
        if (experimentTO.getUpdatedFields().contains(ExperimentTO.ExperimentUpdatableField.end)) {
            originalExperiment.setEnd(experimentTO.getEnd());
        }

        final Experiment updatedExperiment =  experimentRepository.save(originalExperiment);

        Resource<Experiment> resource = experimentResourceAssembler.toResource(updatedExperiment);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }
}
