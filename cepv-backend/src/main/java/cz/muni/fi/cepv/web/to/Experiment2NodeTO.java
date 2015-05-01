package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cz.muni.fi.cepv.web.CustomDateDeserializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xgarcar
 */
public class Experiment2NodeTO implements Serializable {

    private static final long serialVersionUID = 7076645314111276791L;

    // external id of existing Node entity
    private String node;
    private Date additionTime;
    private Date removalTime;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date getAdditionTime() {
        return additionTime;
    }

    public void setAdditionTime(Date additionTime) {
        this.additionTime = additionTime;
    }

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(Date removalTime) {
        this.removalTime = removalTime;
    }
}
