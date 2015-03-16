package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.QueryExecution;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

/**
 * @author xgarcar
 */
@Component
public class QueryExecutionResourceAssembler implements ResourceAssembler<QueryExecution, Resource<QueryExecution>> {

    @Override
    public Resource<QueryExecution> toResource(QueryExecution entity) {
        Resource<QueryExecution> queryExecutionResource = new Resource<>(entity);

        queryExecutionResource.add(new Link(LinkUtil.getQueryExecutionResourceLink(entity.getId())).withSelfRel());
        queryExecutionResource.add(new Link(LinkUtil.getQueryResourceLink(entity.getQuery().getId())).withRel("query"));

        return queryExecutionResource;
    }
}
