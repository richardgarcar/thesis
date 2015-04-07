package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.model.Experiment;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

/**
* @author xgarcar
*/
@Component
public class ExperimentResourceAssembler implements ResourceAssembler<Experiment, Resource<Experiment>> {

    @Override
    public Resource<Experiment> toResource(Experiment entity) {
        Resource<Experiment> experimentResource = new Resource<>(entity);

        experimentResource.add(new Link(LinkUtil.getExperimentResourceLink(entity.getId())).withSelfRel());

        experimentResource.add(new Link(new UriTemplate(LinkUtil.getExperimentResourceExperiment2NodeRelationsLink(entity.getId()),
                LinkUtil.getPageTemplateVariables()), "experiment2NodeRelations"));

        experimentResource.add(new Link(new UriTemplate(LinkUtil.getExperimentResourceFilteredExperiment2NodeRelationsLink(entity.getId()),
               LinkUtil.getFilteredExperiment2NodeRelationsTemplateVariables().concat(
                       LinkUtil.getPageTemplateVariables())), "filteredExperiment2NodeRelations"));

        experimentResource.add(new Link(new UriTemplate(LinkUtil.getExperimentResourceNodeConnectionsLink(entity.getId()),
                LinkUtil.getPageTemplateVariables()), "nodeConnections"));

        experimentResource.add(new Link(new UriTemplate(LinkUtil.getExperimentResourceFilteredNodeConnectionsLink(entity.getId()),
               LinkUtil.getFilteredNodeConnectionsTemplateVariables().concat(
                       LinkUtil.getPageTemplateVariables())), "filteredNodeConnections"));

        experimentResource.add(new Link(new UriTemplate(LinkUtil.getExperimentResourceQueriesLink(entity.getId()),
                LinkUtil.getPageTemplateVariables()), "queries"));

        return experimentResource;
    }
}
