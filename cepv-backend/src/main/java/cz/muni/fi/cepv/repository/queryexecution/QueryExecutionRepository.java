package cz.muni.fi.cepv.repository.queryexecution;

import cz.muni.fi.cepv.domain.QueryExecution;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xgarcar
 */
public interface QueryExecutionRepository extends PagingAndSortingRepository<QueryExecution, Long>,
        QueryDslPredicateExecutor<QueryExecution>, QueryExecutionRepositoryCustom {

}
