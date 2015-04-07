package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
public class QueryTO implements Serializable {

    private static final long serialVersionUID = 7601337509224749067L;

    private Date deploymentTime;
    private String content;

    private final List<QueryUpdatableField> updatedFields = new ArrayList<>();

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "CET")
    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
        updatedFields.add(QueryUpdatableField.deploymentTime);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        updatedFields.add(QueryUpdatableField.content);
    }

    public List<QueryUpdatableField> getUpdatedFields() {
        return updatedFields;
    }

    public enum QueryUpdatableField {
        deploymentTime,
        content
    }
}
