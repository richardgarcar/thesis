package cz.muni.fi.controller;

import com.mysema.query.types.expr.BooleanExpression;
import cz.muni.fi.model.Experiment;
import cz.muni.fi.model.Experiment2Node;
import cz.muni.fi.model.QExperiment;
import cz.muni.fi.repository.ExperimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author xgarcar
 */
@RestController
public class ExperimentController {

    private static final QExperiment experiment = QExperiment.experiment;

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private ExperimentResourceAssembler experimentResourceAssembler;

    @Transactional()
    @RequestMapping(value = "experiments/search/findByFilter", method = RequestMethod.GET, produces = "application/hal+json")
    public HttpEntity<PagedResources<Resource<Experiment>>> searchUsingFilter(@RequestParam(required = false) String name,
                                                                              @RequestParam(required = false) String description,
                                                                              @RequestParam(required = false) Date gtStart,
                                                                              @RequestParam(required = false) Date ltStart,
                                                                              @RequestParam(required = false) Date gtEnd,
                                                                              @RequestParam(required = false) Date ltEnd,
                                                                              Pageable pageable,
                                                                              PagedResourcesAssembler<Experiment> assembler) {

        final Page<Experiment> experiments =  experimentRepository.findAll(query(name, description, gtStart, ltStart, gtEnd, ltEnd), pageable);

        //todo issue this differently
        for (Experiment experiment : experiments.getContent()) {
            experiment.setExperiment2NodeList(new ArrayList<Experiment2Node>(0));
        }

        return new ResponseEntity<>(assembler.toResource(experiments, experimentResourceAssembler), HttpStatus.OK);
    }
    //todo wrap to class
    private BooleanExpression query(final String name, final String description, final Date gtStart, final Date ltStart, final Date gtEnd, final Date ltEnd) {

        BooleanExpression filter = null;

        if (isNameLike(name) != null) {
            filter = isNameLike(name);
        }
        if (isDescriptionLike(description) != null){
            filter = isDescriptionLike(description).and(filter);
        }
        if (isStartInInterval(gtStart, ltStart) != null){
            filter = isStartInInterval(gtStart, ltStart).and(filter);
        }
        if (isEndInInterval(gtEnd, ltEnd) != null){
            filter = isEndInInterval(gtEnd, ltEnd).and(filter);
        }

        return filter;
    }

    private BooleanExpression isNameLike(final String name) {
        return name != null ? experiment.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression isDescriptionLike(final String description) {
        return description != null ? experiment.description.containsIgnoreCase(description) : null;
    }

    private BooleanExpression isStartInInterval(final Date gtStart, final Date ltStart) {
        BooleanExpression afterCondition = gtStart != null ? experiment.start.after(gtStart) : null;
        BooleanExpression beforeCondition = ltStart != null ? experiment.start.before(ltStart) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }

    private BooleanExpression isEndInInterval(final Date gtEnd, final Date ltEnd) {
        BooleanExpression afterCondition = gtEnd != null ? experiment.end.after(gtEnd) : null;
        BooleanExpression beforeCondition = ltEnd != null ? experiment.end.before(ltEnd) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }
}
