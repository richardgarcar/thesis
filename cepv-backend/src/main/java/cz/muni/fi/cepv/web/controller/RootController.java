package cz.muni.fi.cepv.web.controller;

import cz.muni.fi.cepv.web.LinkUtil;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xgarcar
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> getRoot() {

        final ResourceSupport resourceSupport = new ResourceSupport();

        final Link experimentLink = new Link(new UriTemplate(LinkUtil.BASE + LinkUtil.EXPERIMENTS).with(
                LinkUtil.getPageTemplateVariables()), "experiments");

        final Link filteredExperimentLink = new Link(new UriTemplate(LinkUtil.BASE + LinkUtil.EXPERIMENTS_FILTER).with(
                LinkUtil.getFilteredExperimentsTemplateVariables().
                        concat(LinkUtil.getPageTemplateVariables())), "filteredExperiments");

        resourceSupport.add(experimentLink);
        resourceSupport.add(filteredExperimentLink);

        return new ResponseEntity<>(resourceSupport, HttpStatus.OK);
    }
}
