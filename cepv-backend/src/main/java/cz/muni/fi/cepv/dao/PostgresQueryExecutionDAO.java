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
public class PostgresQueryExecutionDAO implements QueryExecutionDAO {

    public static final String FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL =
            "SELECT date_trunc('%s', qe.execution_time) AS interval_endpoint, COUNT(*) AS amount " +
            "FROM cepv.query_execution qe WHERE qe.query_id = :queryId " +
            "AND qe.execution_time >= ((" +
                   "SELECT MAX(sub_qe.execution_time) FROM cepv.query_execution sub_qe " +
                   "WHERE sub_qe.query_id = :queryId) - interval '1 %s') " +
            "GROUP BY interval_endpoint ORDER BY interval_endpoint DESC";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(Long queryId, QueryExecutionsIntervalEnum interval) {
        final Query query = entityManager.createNativeQuery(getQueryWithPresetIntervalValues(interval), "queryExecutionInterval");

        query.setParameter("queryId", queryId);

        return ((List<QueryExecutionsInterval>) query.getResultList());
    }

    /**
     * This method is used as quick workaround because Hibernate doesn't support {@code INTERVAL}.
     * Similar topic is discussed
     * <a href="http://stackoverflow.com/questions/17720674/oracle-jpa-querying-with-interval">here</a>.
     *
     * @param interval
     * @return
     */
    private String getQueryWithPresetIntervalValues(final QueryExecutionsIntervalEnum interval) {
        String result = "";
        switch (interval) {
            case DAY: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "hour", "day");
                break;
            case HOUR: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "minute", "hour");
                break;
            case MINUTE: result = String.format(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "second", "minute");
                break;
        }

        return result;
    }
}
