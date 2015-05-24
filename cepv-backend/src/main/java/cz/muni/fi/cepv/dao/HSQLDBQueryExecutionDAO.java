package cz.muni.fi.cepv.dao;

import cz.muni.fi.cepv.enumeration.QueryExecutionsIntervalEnum;
import cz.muni.fi.cepv.domain.QueryExecutionsInterval;

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
            "FROM query_execution qe WHERE qe.query_id = :queryId " +
            "AND qe.execution_time >= TIMESTAMPADD(%s, -1, " +
                  "(SELECT max(sub_qe.execution_time) FROM query_execution sub_qe where sub_qe.query_id = :queryId)) " +
            "GROUP BY interval_endpoint " +
            "ORDER BY interval_endpoint DESC";

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
            case DAY: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "HH", "SQL_TSI_DAY");
                break;
            case HOUR: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "MI", "SQL_TSI_HOUR");
                break;
            case MINUTE: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "SS", "SQL_TSI_MINUTE");
                break;
        }

        return result;
    }
}
