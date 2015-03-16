package cz.muni.fi.cepv.web.controller;

import cz.muni.fi.cepv.model.*;
import cz.muni.fi.cepv.repository.QueryAttributeRepository;
import cz.muni.fi.cepv.repository.QueryExecutionRepository;
import cz.muni.fi.cepv.repository.QueryRepository;
import cz.muni.fi.cepv.web.LinkUtil;
import cz.muni.fi.cepv.web.resoureceassambler.QueryAttributeResourceAssembler;
import cz.muni.fi.cepv.web.resoureceassambler.QueryExecutionResourceAssembler;
import cz.muni.fi.cepv.web.resoureceassambler.QueryResourceAssembler;
import cz.muni.fi.cepv.web.to.QueryAttributeTO;
import cz.muni.fi.cepv.web.to.QueryExecutionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;

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
        return new ResponseEntity<>(queryResourceAssembler.toResource(query), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY_ATTRIBUTE, method = RequestMethod.GET)
    public HttpEntity<Resource<QueryAttribute>> getQueryAttribute(@PathVariable final Long queryAttributeId) {

        final QueryAttribute queryAttribute =  queryAttributeRepository.findOne(queryAttributeId);

        return new ResponseEntity<>(queryAttributeResourceAssembler.toResource(queryAttribute), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_ATTRIBUTES, method = RequestMethod.POST)
    public HttpEntity<Void>  createQueryAttribute(@PathVariable Long queryId, @RequestBody final QueryAttributeTO queryAttributeTO) {

        final Query query = queryRepository.findOne(queryId);
        final QueryAttribute queryAttribute = new QueryAttribute(query, queryAttributeTO.getKey(), queryAttributeTO.getValue());

        final QueryAttribute savedQueryAttribute = queryAttributeRepository.save(queryAttribute);

        Resource<QueryAttribute> resource = queryAttributeResourceAssembler.toResource(savedQueryAttribute);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_ATTRIBUTE, method = RequestMethod.PUT)
    public HttpEntity<Void> fullUpdateQueryAttribute(@PathVariable final Long queryAttributeId,
                                           @RequestBody final QueryAttributeTO queryAttributeTO) {

        final QueryAttribute originalQueryAttribute = queryAttributeRepository.findOne(queryAttributeId);
        originalQueryAttribute.setKey(queryAttributeTO.getKey());
        originalQueryAttribute.setValue(queryAttributeTO.getValue());

        final QueryAttribute queryAttribute =  queryAttributeRepository.save(originalQueryAttribute);

        Resource<QueryAttribute> resource = queryAttributeResourceAssembler.toResource(queryAttribute);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_ATTRIBUTE, method = RequestMethod.PATCH)
    public HttpEntity<Void> partialUpdateQueryAttribute(@PathVariable final Long queryAttributeId,
                                                        @RequestBody final QueryAttributeTO queryAttributeTO) {

        final QueryAttribute originalQueryAttribute = queryAttributeRepository.findOne(queryAttributeId);

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

        return new ResponseEntity<>(queryExecutionResourceAssembler.toResource(queryExecution), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.QUERY_QUERY_EXECUTIONS, method = RequestMethod.POST)
    public HttpEntity<Void>  createQueryExecution(@PathVariable Long queryId, @RequestBody final QueryExecutionTO queryExecutionTO) {

        final Query query = queryRepository.findOne(queryId);

        final QueryExecution queryExecution = new QueryExecution(query, queryExecutionTO.getExecutionTime());
        final QueryExecution savedQueryExecution = queryExecutionRepository.save(queryExecution);

        Resource<QueryExecution> resource = queryExecutionResourceAssembler.toResource(savedQueryExecution);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_QUERIES, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Query>>> getQueriesOfExperiment(
            @PathVariable final Long experimentId, final Pageable pageable, final PagedResourcesAssembler<Query> assembler) {

        final Page<Query> queryPageResources = queryRepository.findAll(QQuery.query.node.experiment2NodeList.any().experiment.id.eq(experimentId), pageable);

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
}
