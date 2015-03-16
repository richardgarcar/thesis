package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.Node2Node;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

/**
 * @author xgarcar
 */
@Component
public class Node2NodeResourceAssembler implements ResourceAssembler<Node2Node, Resource<Node2Node>> {

    @Override
    public Resource<Node2Node> toResource(Node2Node entity) {
        Resource<Node2Node> node2NodeResource = new Resource<>(entity);

        node2NodeResource.add(new Link(LinkUtil.getNode2NodeResourceLink(entity.getId())).withSelfRel());
        node2NodeResource.add(new Link(LinkUtil.getNodeResourceLink(entity.getFirstNode().getExternalId())).withRel("firstNode"));
        node2NodeResource.add(new Link(LinkUtil.getNodeResourceLink(entity.getSecondNode().getExternalId())).withRel("secondNode"));

        return node2NodeResource;
    }
}
