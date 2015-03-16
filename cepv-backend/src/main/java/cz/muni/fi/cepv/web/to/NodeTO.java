package cz.muni.fi.cepv.web.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xgarcar
 */
public class NodeTO implements Serializable {

    private static final long serialVersionUID = 7362197592488120554L;

    private String externalId;
    private String name;
    private String description;

    private final List<NodeUpdatableField> updatedFields = new ArrayList<>();

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updatedFields.add(NodeUpdatableField.name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updatedFields.add(NodeUpdatableField.description);
    }

    public List<NodeUpdatableField> getUpdatedFields() {
        return updatedFields;
    }

    public enum NodeUpdatableField {
        name,
        description
    }
}
