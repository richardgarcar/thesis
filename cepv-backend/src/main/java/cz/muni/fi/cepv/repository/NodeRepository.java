package cz.muni.fi.cepv.repository;

import cz.muni.fi.cepv.model.Node;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xgarcar
 */
public interface NodeRepository extends PagingAndSortingRepository<Node, Long>, QueryDslPredicateExecutor<Node> {

    Node findByExternalId(final String externalId);
}
