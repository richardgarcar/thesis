package cz.muni.fi.cepv.web.controller;

import cz.muni.fi.cepv.enums.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.model.*;
import cz.muni.fi.cepv.querydsl.QueryQueryDsl;
import cz.muni.fi.cepv.repository.ExperimentRepository;
import cz.muni.fi.cepv.repository.NodeRepository;
import cz.muni.fi.cepv.repository.QueryAttributeRepository;
import cz.muni.fi.cepv.repository.QueryRepository;
import cz.muni.fi.cepv.repository.queryexecution.QueryExecutionRepository;
import cz.muni.fi.cepv.web.LinkUtil;
import cz.muni.fi.cepv.web.exception.ResourceNotFoundException;
import cz.muni.fi.cepv.web.resoureceassambler.QueryAttributeResourceAssembler;
import cz.muni.fi.cepv.web.resoureceassambler.QueryExecutionResourceAssembler;
import cz.muni.fi.cepv.web.resoureceassambler.QueryResourceAssembler;
import cz.muni.fi.cepv.web.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author xgarcar
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class QueryController {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private QueryAttributeRepository queryAttributeRepository;

    @Autowired
    private QueryExecutionRepository queryExecutionRepository;

    @Autowired
    private QueryResourceAssembler queryResourceAssembler;

    @Autowired
    private QueryExecutionResourceAssembler queryExecutionResourceAssembler;

    @Autowired
    private QueryAttributeResourceAssembler queryAttributeResourceAssembler;

    @RequestMapping(value = LinkUtil.QUERIES, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Query>>> getQueries(final Pageable pageable, final PagedResourcesAssembler<Query> assembler) {

        final Page<Query> queryPageResources =  queryRepository.findAll(pageable);

        return new ResponseEntity<>(assembler.toResource(queryPageResources, queryResourceAssembler), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY, method = RequestMethod.GET)
    public HttpEntity<Resource<Query>> getQuery(@PathVariable Long queryId) {

        final Query query =  queryRepository.findOne(queryId);
        if (query == null) {
            throw new ResourceNotFoundException("Query with provided id '" + queryId
                    + "' does not exist.");
        }

        return new ResponseEntity<>(queryResourceAssembler.toResource(query), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_QUERIES, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Query>>> getQueriesOfExperiment(
            @PathVariable final Long experimentId, final Pageable pageable, final PagedResourcesAssembler<Query> assembler) {

        final Page<Query> queryPageResources = queryRepository.findAll(QQuery.query.experiment.id.eq(experimentId), pageable);

        final PagedResources<Resource<Query>> pagedQueryResources = assembler.toResource(queryPageResources, queryResourceAssembler);
        return new ResponseEntity<>(pagedQueryResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.NODE_QUERIES, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Query>>> getQueriesOfNode(
            @PathVariable final String externalNodeId, final Pageable pageable, final PagedResourcesAssembler<Query> assembler) {

        final Page<Query> queryPageResources = queryRepository.findAll(QQuery.query.node.externalId.eq(externalNodeId), pageable);

        final PagedResources<Resource<Query>> pagedQueryResources = assembler.toResource(queryPageResources, queryResourceAssembler);
        return new ResponseEntity<>(pagedQueryResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_QUERIES, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Query>>> getQueriesByExperimentAndNode(
            @PathVariable final Long experimentId, @PathVariable final String externalNodeId, final Pageable pageable, final PagedResourcesAssembler<Query> assembler) {

        final Page<Query> queryPageResources = queryRepository.findAll(
                QueryQueryDsl.isRelatedToExperimentAndNode(experimentId, externalNodeId), pageable);

        final PagedResources<Resource<Query>> pagedQueryResources = assembler.toResource(queryPageResources, queryResourceAssembler);
        return new ResponseEntity<>(pagedQueryResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_QUERIES_FILTER, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Query>>> getFilteredQueriesByExperimentAndNode(
            @PathVariable final Long experimentId, @PathVariable final String externalNodeId,
            @RequestParam(required = false) final Date gtDeploymentTime, @RequestParam(required = false) final Date ltDeploymentTime,
            final Pageable pageable, final PagedResourcesAssembler<Query> assembler) {

        final Page<Query> queryPageResources = queryRepository.findAll(
                QueryQueryDsl.filter(experimentId, externalNodeId, gtDeploymentTime, ltDeploymentTime), pageable);

        final Link link = new Link(new UriTemplate(linkTo(methodOn(QueryController.class).
                getFilteredQueriesByExperimentAndNode(experimentId, externalNodeId, gtDeploymentTime, ltDeploymentTime, pageable, assembler)).
                toUriComponentsBuilder().build().toUriString(), LinkUtil.getFilteredQueriesTemplateVariables()), "self");

        final PagedResources<Resource<Query>> pagedQueryResources = assembler.toResource(queryPageResources, queryResourceAssembler, link);
        return new ResponseEntity<>(pagedQueryResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_QUERIES, method = RequestMethod.POST)
    public HttpEntity<Void>  createQuery(@PathVariable final Long experimentId,
                                         @PathVariable final String externalNodeId,
                                         @Valid @RequestBody final NewQueryTO queryTO) {

        final Experiment experiment = experimentRepository.findOne(experimentId);
        if (experiment == null) {
            throw new ResourceNotFoundException("Experiment with provided id '" + experimentId
                    + "' does not exist.");
        }
        final Node node = nodeRepository.findByExternalId(externalNodeId);
        if (node == null) {
            throw new ResourceNotFoundException("Node with provided external id '" + externalNodeId
                    + "' does not exist.");
        }

        final Query queryExecution = new Query(node, experiment, queryTO.getDeploymentTime(), queryTO.getContent());
        final Query query = queryRepository.save(queryExecution);

        Resource<Query> resource = queryResourceAssembler.toResource(query);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.QUERY, method = RequestMethod.PATCH)
    public HttpEntity<Void> partialUpdateQuery(@PathVariable final Long queryId,
                                               @Valid @RequestBody final QueryTO queryTO) {

        final Query originalQuery = queryRepository.findOne(queryId);
        if (originalQuery == null) {
            throw new ResourceNotFoundException("Query with provided external id '" + queryId
                    + "' does not exist.");
        }

        if (queryTO.getUpdatedFields().contains(QueryTO.QueryUpdatableField.deploymentTime)) {
            originalQuery.setDeploymentTime(queryTO.getDeploymentTime());
        }
        if (queryTO.getUpdatedFields().contains(QueryTO.QueryUpdatableField.content)) {
            originalQuery.setContent(queryTO.getContent());
        }

        final Query query =  queryRepository.save(originalQuery);

        Resource<Query> resource = queryResourceAssembler.toResource(query);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = LinkUtil.QUERY_ATTRIBUTE, method = RequestMethod.GET)
    public HttpEntity<Resource<QueryAttribute>> getQueryAttribute(@PathVariable final Long queryAttributeId) {

        final QueryAttribute queryAttribute =  queryAttributeRepository.findOne(queryAttributeId);
        if (queryAttribute == null) {
            throw new ResourceNotFoundException("Query Attribute with provided external id '" + queryAttributeId
                    + "' does not exist.");
        }

        return new ResponseEntity<>(queryAttributeResourceAssembler.toResource(queryAttribute), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_ATTRIBUTES, method = RequestMethod.POST)
    public HttpEntity<Void>  createQueryAttribute(@PathVariable Long queryId,
                                                  @Valid @RequestBody final NewQueryAttributeTO queryAttributeTO) {

        final Query query = queryRepository.findOne(queryId);
        final QueryAttribute queryAttribute = new QueryAttribute(query, queryAttributeTO.getKey(), queryAttributeTO.getValue());

        final QueryAttribute savedQueryAttribute = queryAttributeRepository.save(queryAttribute);

        Resource<QueryAttribute> resource = queryAttributeResourceAssembler.toResource(savedQueryAttribute);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.QUERY_ATTRIBUTE, method = RequestMethod.PUT)
    public HttpEntity<Void> fullUpdateQueryAttribute(@PathVariable final Long queryAttributeId,
                                           @Valid @RequestBody final QueryAttributeTO queryAttributeTO) {

        final QueryAttribute originalQueryAttribute = queryAttributeRepository.findOne(queryAttributeId);
        if (originalQueryAttribute == null) {
            throw new ResourceNotFoundException("Query Attribute with provided external id '" + queryAttributeId
                    + "' does not exist.");
        }

        originalQueryAttribute.setKey(queryAttributeTO.getKey());
        originalQueryAttribute.setValue(queryAttributeTO.getValue());

        final QueryAttribute queryAttribute =  queryAttributeRepository.save(originalQueryAttribute);

        Resource<QueryAttribute> resource = queryAttributeResourceAssembler.toResource(queryAttribute);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = LinkUtil.QUERY_ATTRIBUTE, method = RequestMethod.PATCH)
    public HttpEntity<Void> partialUpdateQueryAttribute(@PathVariable final Long queryAttributeId,
                                                        @Valid @RequestBody final QueryAttributeTO queryAttributeTO) {

        final QueryAttribute originalQueryAttribute = queryAttributeRepository.findOne(queryAttributeId);
        if (originalQueryAttribute == null) {
            throw new ResourceNotFoundException("Query Attribute with provided external id '" + queryAttributeId
                    + "' does not exist.");
        }
        if (queryAttributeTO.getUpdatedFields().contains(QueryAttributeTO.QueryAttributeUpdatableField.key)) {
            originalQueryAttribute.setKey(queryAttributeTO.getKey());
        }
        if (queryAttributeTO.getUpdatedFields().contains(QueryAttributeTO.QueryAttributeUpdatableField.value)) {
            originalQueryAttribute.setValue(queryAttributeTO.getValue());
        }

        final QueryAttribute queryAttribute =  queryAttributeRepository.save(originalQueryAttribute);

        Resource<QueryAttribute> resource = queryAttributeResourceAssembler.toResource(queryAttribute);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_EXECUTIONS, method = RequestMethod.GET)
         public HttpEntity<PagedResources<Resource<QueryExecution>>> getQueryExecutions(
            @PathVariable final Long queryId, final Pageable pageable, final PagedResourcesAssembler<QueryExecution> assembler) {

        final Page<QueryExecution> queryExecutionPageResources =  queryExecutionRepository.findAll(QQueryExecution.queryExecution.query.id.eq(queryId), pageable);

        final Link link = linkTo(methodOn(QueryController.class).getQueryExecutions(queryId, pageable, assembler)).withSelfRel();

        return new ResponseEntity<>(assembler.toResource(queryExecutionPageResources, queryExecutionResourceAssembler, link), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY_EXECUTION, method = RequestMethod.GET)
    public HttpEntity<Resource<QueryExecution>> getQueryExecution(@PathVariable final Long queryExecutionId) {

        final QueryExecution queryExecution =  queryExecutionRepository.findOne(queryExecutionId);
        if (queryExecution == null) {
            throw new ResourceNotFoundException("Query Execution with provided external id '" + queryExecutionId
                    + "' does not exist.");
        }

        return new ResponseEntity<>(queryExecutionResourceAssembler.toResource(queryExecution), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_EXECUTIONS, method = RequestMethod.POST)
    public HttpEntity<Void>  createQueryExecution(@PathVariable Long queryId,
                                                  @Valid @RequestBody final QueryExecutionTO queryExecutionTO) {

        final Query query = queryRepository.findOne(queryId);
        if (query == null) {
            throw new ResourceNotFoundException("Query with provided external id '" + queryId
                    + "' does not exist.");
        }

        final QueryExecution queryExecution = new QueryExecution(query, queryExecutionTO.getExecutionTime());
        final QueryExecution savedQueryExecution = queryExecutionRepository.save(queryExecution);

        Resource<QueryExecution> resource = queryExecutionResourceAssembler.toResource(savedQueryExecution);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_EXECUTIONS_STATISTICS, method = RequestMethod.GET)
    public HttpEntity<Resources<QueryExecutionsInterval>> getQueryExecutionStatistics(@PathVariable final Long queryId,
                                                                                 @RequestParam final String interval) {

        final Link link = linkTo(methodOn(QueryController.class).
                getQueryExecutionStatistics(queryId, interval)).withSelfRel();

        final List<QueryExecutionsInterval> executionsIntervals = queryExecutionRepository.
                findQueryExecutionsInTimeInterval(queryId, QueryExecutionsIntervalEnum.valueOf(interval));

        return new ResponseEntity<>(new Resources<>(executionsIntervals, link), HttpStatus.OK);
    }
}
