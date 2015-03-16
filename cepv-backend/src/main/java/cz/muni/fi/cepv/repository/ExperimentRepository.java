package cz.muni.fi.cepv.repository;

import cz.muni.fi.cepv.model.Experiment;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xgarcar
 */
public interface ExperimentRepository extends PagingAndSortingRepository<Experiment, Long>, QueryDslPredicateExecutor<Experiment> {
}
