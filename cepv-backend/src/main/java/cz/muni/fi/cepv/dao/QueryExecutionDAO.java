package cz.muni.fi.cepv.dao;

import cz.muni.fi.cepv.enums.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.model.QueryExecutionsInterval;

import java.util.List;

/**
 * @author xgarcar
 */
public interface QueryExecutionDAO {

    public List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(Long queryId,
                                                                            QueryExecutionsIntervalEnum interval);
}
