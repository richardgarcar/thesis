package cz.muni.fi.cepv.dao;

import cz.muni.fi.cepv.enums.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.model.QueryExecutionsInterval;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * @author xgarcar
 */
public class HSQLDBQueryExecutionDAO implements QueryExecutionDAO {

    public static final String FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL =
            "SELECT trunc(qe.execution_time, '%s') AS interval_endpoint, COUNT(*) AS amount " +
                    "FROM query_execution qe WHERE qe.query_id = :queryId GROUP BY interval_endpoint " +
                    "ORDER BY interval_endpoint DESC LIMIT %d";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(final Long queryId,
                                                                           final QueryExecutionsIntervalEnum interval) {
        final Query query = entityManager.createNativeQuery(getQueryWithIntervalAndLimit(interval),
                "queryExecutionInterval");

        query.setParameter("queryId", queryId);

        return ((List<QueryExecutionsInterval>) query.getResultList());
    }


    /**
     * This method serve as quick workaround because of problem with setting parameter to hsqldb pre-built function
     *
     * @param interval
     * @return
     */
    private String getQueryWithIntervalAndLimit(final QueryExecutionsIntervalEnum interval) {
        String result = "";
        switch (interval) {
            case DAY: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "HH", 24);
                break;
            case HOUR: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "MI", 60);
                break;
            case MINUTE: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "SS", 60);
                break;
        }

        return result;
    }
}
