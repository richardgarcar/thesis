package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
public class ExperimentTO implements Serializable {
    private static final long serialVersionUID = -6132106789033516460L;

    private String name;
    private String description;
    private Date start;
    private Date end;

    private final List<ExperimentUpdatableField> updatedFields = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updatedFields.add(ExperimentUpdatableField.name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updatedFields.add(ExperimentUpdatableField.description);
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "CET")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
        updatedFields.add(ExperimentUpdatableField.start);
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "CET")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
        updatedFields.add(ExperimentUpdatableField.end);
    }

    public List<ExperimentUpdatableField> getUpdatedFields() {
        return updatedFields;
    }

    public enum ExperimentUpdatableField {
        name,
        description,
        start,
        end
    }
}
