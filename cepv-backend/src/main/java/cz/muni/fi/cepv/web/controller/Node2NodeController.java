package cz.muni.fi.cepv.web.controller;

import cz.muni.fi.cepv.model.Experiment;
import cz.muni.fi.cepv.model.Node;
import cz.muni.fi.cepv.model.Node2Node;
import cz.muni.fi.cepv.repository.ExperimentRepository;
import cz.muni.fi.cepv.repository.Node2NodeRepository;
import cz.muni.fi.cepv.repository.NodeRepository;
import cz.muni.fi.cepv.repository.querydsl.Node2NodeQueryDsl;
import cz.muni.fi.cepv.web.LinkUtil;
import cz.muni.fi.cepv.web.resoureceassambler.Node2NodeResourceAssembler;
import cz.muni.fi.cepv.web.to.Node2NodeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author xgarcar
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class Node2NodeController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private Node2NodeRepository node2NodeRepository;

    @Autowired
    private Node2NodeResourceAssembler node2NodeResourceAssembler;

    @RequestMapping(value = LinkUtil.NODE_CONNECTIONS, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Node2Node>>> getExperiment2NodeRelations(final Pageable pageable,
                                                                                       final PagedResourcesAssembler<Node2Node> assembler) {

        final Page<Node2Node> node2NodePageResources =  node2NodeRepository.findAll(pageable);

        return new ResponseEntity<>(assembler.toResource(node2NodePageResources, node2NodeResourceAssembler), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.NODE_CONNECTION, method = RequestMethod.GET)
    public HttpEntity<Resource<Node2Node>> getExperiment2NodeRelation(@PathVariable Long node2NodeId) {

        final Node2Node node2Node =  node2NodeRepository.findOne(node2NodeId);
        return new ResponseEntity<>(node2NodeResourceAssembler.toResource(node2Node), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_CONNECTIONS, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Node2Node>>> getNodeConnections(
            @PathVariable final Long experimentId, final Pageable pageable, final PagedResourcesAssembler<Node2Node> assembler) {

        final Page<Node2Node> node2NodePageResources = node2NodeRepository.findAll(Node2NodeQueryDsl.isRelatedToExperiment(experimentId), pageable);

        final PagedResources<Resource<Node2Node>> pagedNode2NodeResources = assembler.toResource(node2NodePageResources, node2NodeResourceAssembler);

        return new ResponseEntity<>(pagedNode2NodeResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_CONNECTIONS_FILTER, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Node2Node>>> getFilteredNodeConnections(
            @PathVariable final Long experimentId,
            @RequestParam(required = false) final String firstNodeName, @RequestParam(required = false) final String secondNodeName,
            @RequestParam(required = false) final String firstNodeDescription, @RequestParam(required = false) final String secondNodeDescription,
            @RequestParam(required = false) final Date gtConnectionTime, @RequestParam(required = false) final Date ltConnectionTime,
            @RequestParam(required = false) final Date gtDisconnectionTime, @RequestParam(required = false) final Date ltDisconnectionTime,
            final Pageable pageable, final PagedResourcesAssembler<Node2Node> assembler) {

        final Page<Node2Node> node2NodePageResources = node2NodeRepository.
                findAll(Node2NodeQueryDsl.filter(experimentId, firstNodeName, secondNodeName, firstNodeDescription, secondNodeDescription,
                        gtConnectionTime, ltConnectionTime, gtDisconnectionTime, ltDisconnectionTime), pageable);

        final Link link = new Link(new UriTemplate(linkTo(methodOn(Node2NodeController.class).
                getFilteredNodeConnections(experimentId, firstNodeName, secondNodeName, firstNodeDescription, secondNodeDescription,
                        gtConnectionTime, ltConnectionTime, gtDisconnectionTime, ltDisconnectionTime, pageable, assembler)).
                toUriComponentsBuilder().build().toUriString(), LinkUtil.getFilteredNodeConnectionsTemplateVariables()), "self");

        final PagedResources<Resource<Node2Node>> pagedNode2NodeResources = assembler.
                toResource(node2NodePageResources, node2NodeResourceAssembler, link);
        return new ResponseEntity<>(pagedNode2NodeResources, HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_CONNECTIONS, method = RequestMethod.POST)
    public HttpEntity<Void> createNode2NodeConnection(@PathVariable final Long experimentId, @RequestBody final Node2NodeTO node2NodeTO) {

        final Experiment experiment = experimentRepository.findOne(experimentId);
        final Node firstNode = nodeRepository.findByExternalId(node2NodeTO.getFirstNode());
        final Node secondNode = nodeRepository.findByExternalId(node2NodeTO.getSecondNode());

        final Node2Node node2Node = new Node2Node(firstNode, secondNode, experiment,
                node2NodeTO.getConnectionTime(), node2NodeTO.getDisconnectionTime());

        final Node2Node savedNode2Node = node2NodeRepository.save(node2Node);

        Resource<Node2Node> resource = node2NodeResourceAssembler.toResource(savedNode2Node);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.EXPERIMENT_NODE_CONNECTION, method = RequestMethod.PATCH)
    public HttpEntity<Void> updateNode2NodeConnection(@PathVariable final Long node2NodeId, @RequestBody final Node2NodeTO node2NodeTO) {

        final Node2Node originalNode2Node = node2NodeRepository.findOne(node2NodeId);
        if (node2NodeTO.getUpdatedFields().contains(Node2NodeTO.Node2NodeUpdatableField.connectionTime)) {
            originalNode2Node.setConnectionTime(node2NodeTO.getConnectionTime());
        }

        if (node2NodeTO.getUpdatedFields().contains(Node2NodeTO.Node2NodeUpdatableField.disconnectionTime)) {
            originalNode2Node.setDisconnectionTime(node2NodeTO.getDisconnectionTime());
        }

        final Node2Node updatedNode2Node = node2NodeRepository.save(originalNode2Node);

        Resource<Node2Node> resource = node2NodeResourceAssembler.toResource(updatedNode2Node);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }
}
