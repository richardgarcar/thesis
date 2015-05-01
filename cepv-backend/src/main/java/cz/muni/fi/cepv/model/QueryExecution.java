package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.cepv.web.CustomDateSerializer;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author xgarcar
 */
@SqlResultSetMapping(name="queryExecutionInterval",
        classes={
                @ConstructorResult(targetClass=QueryExecutionsInterval.class, columns={
                        @ColumnResult(name="interval_endpoint", type=Date.class),
                        @ColumnResult(name="amount", type= BigInteger.class),
                })
        }
)
@Entity
@Table(name = "QUERY_EXECUTION")
@Relation(collectionRelation = "queryExecutions")
public class QueryExecution extends CommonEntity{

    private Query query;
    private Date executionTime;

    public QueryExecution() {
    }

    public QueryExecution(Query query, Date executionTime) {
        this.query = query;
        this.executionTime = executionTime;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "QUERY_ID", updatable = false)
    @JsonIgnore
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    @Column(name = "EXECUTION_TIME", nullable = false, updatable = false)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }
}
