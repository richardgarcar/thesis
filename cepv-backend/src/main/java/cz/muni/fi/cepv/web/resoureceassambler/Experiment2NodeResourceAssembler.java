package cz.muni.fi.cepv.web.resoureceassambler;

import cz.muni.fi.cepv.domain.Experiment2Node;
import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author xgarcar
 */
@Component
public class Experiment2NodeResourceAssembler implements ResourceAssembler<Experiment2Node, Resource<Experiment2Node>> {

    @Override
    public Resource<Experiment2Node> toResource(Experiment2Node entity) {
        Resource<Experiment2Node> experiment2NodeResource = new Resource<>(entity);

        experiment2NodeResource.add(new Link(LinkUtil.getExperiment2NodeResourceLink(entity.getId())).withSelfRel());
        experiment2NodeResource.add(new Link(LinkUtil.getExperimentResourceLink(entity.getExperiment().getId())).withRel("experiment"));
        experiment2NodeResource.add(new Link(LinkUtil.getNodeResourceLink(entity.getNode().getExternalId())).withRel("node"));

        return experiment2NodeResource;
    }
}
