package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.Node;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author xgarcar
 */
@Component
public class NodeResourceAssembler implements ResourceAssembler<Node, Resource<Node>> {

    @Override
    public Resource<Node> toResource(Node entity) {
        Resource<Node> nodeResource = new Resource<>(entity);

        nodeResource.add(new Link(LinkUtil.getNodeResourceLink(entity.getExternalId())).withSelfRel());
        nodeResource.add(new Link(LinkUtil.getNodeResourceQueriesLink(entity.getExternalId())).withRel("queries"));
        return nodeResource;
    }
}
