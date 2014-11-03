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
}
