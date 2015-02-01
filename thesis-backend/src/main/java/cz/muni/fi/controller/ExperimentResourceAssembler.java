package cz.muni.fi.controller;

import cz.muni.fi.model.Experiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
* @author xgarcar
*/
@Component
public class ExperimentResourceAssembler implements ResourceAssembler<Experiment, Resource<Experiment>> {

    @Autowired
    // available for DI using @EnableHypermediaSupport
    private EntityLinks entityLinks;

    @Override
    public Resource<Experiment> toResource(Experiment entity) {
        Resource<Experiment> resource = new Resource<>(entity);

        final LinkBuilder lb = entityLinks.linkForSingleResource(Experiment.class, entity.getId());

        resource.add(lb.withSelfRel());
        resource.add(lb.slash("experiment2NodeList").withRel("experiment2NodeList"));

        return resource;
    }
}
