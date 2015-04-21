package cz.muni.fi.cepv.repository.queryexecution;

import cz.muni.fi.cepv.enums.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.model.QueryExecutionsInterval;

import java.util.List;

/**
 * @author xgarcar
 */
interface QueryExecutionRepositoryCustom {

    List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(Long queryId, QueryExecutionsIntervalEnum interval);
}
