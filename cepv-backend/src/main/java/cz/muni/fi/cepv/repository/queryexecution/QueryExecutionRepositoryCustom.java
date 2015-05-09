package cz.muni.fi.cepv.repository.queryexecution;

import cz.muni.fi.cepv.enumeration.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.domain.QueryExecutionsInterval;

import java.util.List;

/**
 * @author xgarcar
 */
interface QueryExecutionRepositoryCustom {

    List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(Long queryId, QueryExecutionsIntervalEnum interval);
}
