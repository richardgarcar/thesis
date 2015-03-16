package cz.muni.fi.cepv.repository;

import cz.muni.fi.cepv.model.Experiment2Node;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xgarcar
 */
public interface Experiment2NodeRepository extends PagingAndSortingRepository<Experiment2Node, Long>, QueryDslPredicateExecutor<Experiment2Node> {
}
