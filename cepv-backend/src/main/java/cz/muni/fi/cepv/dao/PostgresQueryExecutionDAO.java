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
public class PostgresQueryExecutionDAO implements QueryExecutionDAO {

    public static final String FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL =
            "SELECT date_trunc(?1, qe.execution_time) AS interval_endpoint, COUNT(*) AS amount " +
            "FROM cepv.query_execution qe WHERE qe.query_id = ?2 GROUP BY interval_endpoint " +
            "ORDER BY interval_endpoint DESC LIMIT ?3";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QueryExecutionsInterval> findQueryExecutionsInTimeInterval(Long queryId, QueryExecutionsIntervalEnum interval) {
        final Query query = entityManager.createNativeQuery(FIND_QUERY_EXECUTIONS_IN_TIME_INTERVAL, "queryExecutionInterval");

        switch (interval) {
            case DAY: query.setParameter(1, "hour");
                        query.setParameter(3, 24);
                break;
            case HOUR: query.setParameter(1, "minute");
                          query.setParameter(3, 60);
                break;
            case MINUTE: query.setParameter(1, "second");
                          query.setParameter(3, 60);
                break;
        }
        query.setParameter(2, queryId);

        return ((List<QueryExecutionsInterval>) query.getResultList());
    }
}
