package cz.muni.fi.cepv.repository;

import cz.muni.fi.cepv.domain.Node2Node;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xgarcar
 */
public interface Node2NodeRepository extends PagingAndSortingRepository<Node2Node, Long>, QueryDslPredicateExecutor<Node2Node> {
}
