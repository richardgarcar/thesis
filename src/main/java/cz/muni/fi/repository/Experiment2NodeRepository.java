package cz.muni.fi.repository;

import cz.muni.fi.model.Experiment;
import cz.muni.fi.model.Experiment2Node;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
@RepositoryRestResource()
public interface Experiment2NodeRepository extends CrudRepository<Experiment2Node, Long> {

    @RestResource(rel = "byExperiment", path = "byExperiment")
    List<Experiment2Node> findByExperiment(@Param("experiment") Experiment experiment);

    @RestResource(rel = "findByExperimentAndAdditionTimeInInterval", path = "findByExperimentAndAdditionTimeInInterval")
    @Query("select e2n from Experiment2Node e2n " +
            "where e2n.experiment = :experiment and e2n.additionTime >= :fromTime and e2n.additionTime <= :toTime")
    List<Experiment2Node> findByExperimentAndAdditionTimeInInterval(@Param("experiment") Experiment experiment,
                                                         @Param("fromTime") @DateTimeFormat() Date fromTime,
                                                         @Param("toTime") @DateTimeFormat() Date toTime);
}
