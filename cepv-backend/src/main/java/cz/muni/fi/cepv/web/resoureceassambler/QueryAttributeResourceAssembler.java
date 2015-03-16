package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.QueryAttribute;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author xgarcar
 */
@Component
public class QueryAttributeResourceAssembler implements ResourceAssembler<QueryAttribute, Resource<QueryAttribute>> {

    @Override
    public Resource<QueryAttribute> toResource(QueryAttribute entity) {
        Resource<QueryAttribute> queryAttributeResource = new Resource<>(entity);

        queryAttributeResource.add(new Link(LinkUtil.getQueryAttributeResourceLink(entity.getId())).withSelfRel());
        queryAttributeResource.add(new Link(LinkUtil.getQueryResourceLink(entity.getQuery().getId())).withRel("query"));

        return queryAttributeResource;
    }
}
