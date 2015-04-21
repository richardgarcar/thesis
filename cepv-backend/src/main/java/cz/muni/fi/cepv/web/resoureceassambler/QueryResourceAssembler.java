package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.Query;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.UriTemplate;
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
        final Link queryExecutionsLink = new Link(new UriTemplate(LinkUtil.getQueryResourceQueryExecutionsLink(entity.getId()),
                        LinkUtil.getPageTemplateVariables()), "queryExecutions");
        final Link queryExecutionsStatisticsLink = new Link(new UriTemplate(LinkUtil.getQueryResourceQueryExecutionsStatisticsLink(entity.getId()),
                LinkUtil.getQueryExecutionsStatisticsTemplateVariables()), "queryExecutionsStatistics");
        final Link experimentLink = new Link(LinkUtil.getExperimentResourceLink(entity.getExperiment().getId())).withRel("experiment");
        final Link nodeLink = new Link(LinkUtil.getNodeResourceLink(entity.getNode().getExternalId())).withRel("node");

        queryResource.add(selfLink);
        queryResource.add(queryExecutionsLink);
        queryResource.add(queryExecutionsStatisticsLink);
        queryResource.add(experimentLink);
        queryResource.add(nodeLink);

        return queryResource;
    }
}
