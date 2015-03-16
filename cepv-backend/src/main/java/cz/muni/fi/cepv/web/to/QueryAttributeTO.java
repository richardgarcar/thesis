package cz.muni.fi.cepv.web.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xgarcar
 */
public class QueryAttributeTO implements Serializable {

    private static final long serialVersionUID = 5128146975750560679L;

    private String key;
    private String value;

    private final List<QueryAttributeUpdatableField> updatedFields = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        updatedFields.add(QueryAttributeUpdatableField.key);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        updatedFields.add(QueryAttributeUpdatableField.value);
    }

    public List<QueryAttributeUpdatableField> getUpdatedFields() {
        return updatedFields;
    }

    public enum QueryAttributeUpdatableField {
        key,
        value
    }
}
