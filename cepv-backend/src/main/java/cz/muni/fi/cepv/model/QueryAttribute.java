package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "QUERY_ATTRIBUTE")
@Relation(collectionRelation = "queryAttributes")
public class QueryAttribute extends CommonEntity {

    private String key;
    private String value;
    private Query query;

    public QueryAttribute() {
    }

    public QueryAttribute(Query query, String key, String value) {
        this.key = key;
        this.value = value;
        this.query = query;
    }

    @Column(name = "KEY", nullable = false)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "VALUE", nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "QUERY_ID", updatable = false)
    @JsonIgnore
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
