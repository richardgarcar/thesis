package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.Query;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author xgarcar
 */
@Component
public class QueryResourceAssembler implements ResourceAssembler<Query, Resource<Query>> {

    @Override
    public Resource<Query> toResource(Query entity) {
        Resource<Query> queryResource = new Resource<>(entity);

        final Link selfLink = new Link(LinkUtil.getQueryResourceLink(entity.getId())).withSelfRel();
        final Link queryExecutionsLink = new Link(LinkUtil.getQueryResourceQueryExecutionsLink(entity.getId())).withRel("queryExecutions");
        final Link nodeLink = new Link(LinkUtil.getNodeResourceLink(entity.getNode().getExternalId())).withRel("node");

        queryResource.add(selfLink);
        queryResource.add(queryExecutionsLink);
        queryResource.add(nodeLink);

        return queryResource;
    }
}
