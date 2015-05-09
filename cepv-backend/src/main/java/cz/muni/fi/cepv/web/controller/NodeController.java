package cz.muni.fi.cepv.web.controller;

import cz.muni.fi.cepv.domain.Node;
import cz.muni.fi.cepv.repository.NodeRepository;
import cz.muni.fi.cepv.web.LinkUtil;
import cz.muni.fi.cepv.web.exception.ResourceNotFoundException;
import cz.muni.fi.cepv.web.resoureceassambler.NodeResourceAssembler;
import cz.muni.fi.cepv.web.to.NewNodeTO;
import cz.muni.fi.cepv.web.to.NodeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author xgarcar
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private NodeResourceAssembler nodeResourceAssembler;

    @RequestMapping(value = LinkUtil.NODES, method = RequestMethod.GET)
    public HttpEntity<PagedResources<Resource<Node>>> getNodes(final Pageable pageable,
                                                               final PagedResourcesAssembler<Node> assembler) {

        final Page<Node> nodePageResources =  nodeRepository.findAll(pageable);

        return new ResponseEntity<>(assembler.toResource(nodePageResources, nodeResourceAssembler), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.NODE, method = RequestMethod.GET)
    public HttpEntity<Resource<Node>> getNode(@PathVariable String externalNodeId) {

        final Node node = nodeRepository.findByExternalId(externalNodeId);

        if (node == null) {
            throw new ResourceNotFoundException("Node with provided external id '" + externalNodeId
                    + "' does not exist.");
        }

        return new ResponseEntity<>(nodeResourceAssembler.toResource(node), HttpStatus.OK);
    }

    @RequestMapping(value = LinkUtil.NODES, method = RequestMethod.POST)
    public HttpEntity<Void> createNode(@Valid @RequestBody final NewNodeTO nodeTO) {

        final Node node = new Node(nodeTO.getExternalId(), nodeTO.getName(), nodeTO.getDescription());
        final Node savedNode =  nodeRepository.save(node);

        Resource<Node> resource = nodeResourceAssembler.toResource(savedNode);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = LinkUtil.NODE, method = RequestMethod.PATCH)
    public HttpEntity<Void> partialUpdateNode(@PathVariable("externalNodeId") final String externalNodeId,
                                              @Valid @RequestBody final NodeTO nodeTO) {

        final Node originalNode = nodeRepository.findByExternalId(externalNodeId);

        if (originalNode == null) {
            throw new ResourceNotFoundException("Node with provided external id '" + externalNodeId
                    + "' does not exist.");
        }
        if (nodeTO.getUpdatedFields().contains(NodeTO.NodeUpdatableField.name)) {
            originalNode.setName(nodeTO.getName());
        }
        if (nodeTO.getUpdatedFields().contains(NodeTO.NodeUpdatableField.description)) {
            originalNode.setDescription(nodeTO.getDescription());
        }

        final Node updatedNode =  nodeRepository.save(originalNode);

        Resource<Node> resource = nodeResourceAssembler.toResource(updatedNode);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(resource.getLink("self").getHref()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }
}
