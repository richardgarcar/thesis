package cz.muni.fi.model;

import javax.persistence.*;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "QUERY_ATTRIBUTE")
public class QueryAttribute extends CommonEntity {

    private String key;
    private String value;
    private Query query;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "QUERY_ID")
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
