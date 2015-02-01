package cz.muni.fi.repository;

import cz.muni.fi.model.Experiment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author xgarcar
 */
public interface ExperimentRepository extends PagingAndSortingRepository<Experiment, Long>, QueryDslPredicateExecutor {

    @RestResource(rel = "byNameLike", path = "byNameLike")
    Page<Experiment> findByNameContaining(@Param("name")String name, Pageable pageable);
}
