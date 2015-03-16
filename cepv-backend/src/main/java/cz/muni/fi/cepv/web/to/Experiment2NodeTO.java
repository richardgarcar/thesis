package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xgarcar
 */
public class Experiment2NodeTO implements Serializable {

    private static final long serialVersionUID = 7076645314111276791L;

    // id of existing Experiment entity
    private Long experiment;
    // external id of existing Node entity
    private String node;
    private Date additionTime;
    private Date removalTime;

    public Long getExperiment() {
        return experiment;
    }

    public void setExperiment(Long experiment) {
        this.experiment = experiment;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "CET")
    public Date getAdditionTime() {
        return additionTime;
    }

    public void setAdditionTime(Date additionTime) {
        this.additionTime = additionTime;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "CET")
    public Date getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(Date removalTime) {
        this.removalTime = removalTime;
    }
}
