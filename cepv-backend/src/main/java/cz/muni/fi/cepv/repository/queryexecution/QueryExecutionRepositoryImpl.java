package cz.muni.fi.cepv.repository.queryexecution;

import cz.muni.fi.cepv.enumeration.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.domain.QueryExecutionsInterval;
import cz.muni.fi.cepv.dao.QueryExecutionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xgarcar
 */
class QueryExecutionRepositoryImpl implements QueryExecutionRepositoryCustom {

    @Autowired
    private QueryExecutionDAO queryExecutionDAO;

    @Override
    public List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(final Long queryId,
                                                                           final QueryExecutionsIntervalEnum interval) {
        return queryExecutionDAO.findQueryExecutionsInTimeInterval(queryId, interval);
    }
}
