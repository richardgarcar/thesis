package cz.muni.fi.cepv.repository;

import cz.muni.fi.cepv.domain.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xgarcar
 */
public interface QueryRepository extends PagingAndSortingRepository<Query, Long>, QueryDslPredicateExecutor<Query> {
}
