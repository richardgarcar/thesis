package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xgarcar
 */
public class QueryTO implements Serializable {

    private static final long serialVersionUID = 7601337509224749067L;

    // id of existing Node entity
    private Long node;
    // id of existing Node entity
    private Long experiment;
    private Date deploymentTime;
    private String content;

    public Long getNode() {
        return node;
    }

    public void setNode(Long node) {
        this.node = node;
    }

    public Long getExperiment() {
        return experiment;
    }

    public void setExperiment(Long experiment) {
        this.experiment = experiment;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "CET")
    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
