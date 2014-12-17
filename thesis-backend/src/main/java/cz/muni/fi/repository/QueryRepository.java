package cz.muni.fi.repository;

import cz.muni.fi.model.Experiment;
import cz.muni.fi.model.Node;
import cz.muni.fi.model.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author xgarcar
 */
@RepositoryRestResource
public interface QueryRepository extends CrudRepository<Query, Long> {

    @RestResource(rel = "byExperiment", path = "byExperiment")
    List<Query> findByExperiment(@Param("experiment") Experiment experiment);

    @RestResource(rel = "byExperimentAndNode", path = "byExperimentAndNode")
    List<Query> findByExperimentAndNode(@Param("experiment") Experiment experiment, @Param("node") Node node);
}
