package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
public class Node2NodeTO implements Serializable {

    private static final long serialVersionUID = 6171937424139713060L;

    // external id of existing Node entity
    private String firstNode;
    // external id of existing Node entity
    private String secondNode;
    private Date connectionTime;
    private Date disconnectionTime;

    private final List<Node2NodeUpdatableField> updatedFields = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(String firstNode) {
        this.firstNode = firstNode;
    }

    public String getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(String secondNode) {
        this.secondNode = secondNode;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "CET")
    public Date getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Date connectionTime) {
        this.connectionTime = connectionTime;
        updatedFields.add(Node2NodeUpdatableField.connectionTime);
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "CET")
    public Date getDisconnectionTime() {
        return disconnectionTime;
    }

    public void setDisconnectionTime(Date disconnectionTime) {
        this.disconnectionTime = disconnectionTime;
        updatedFields.add(Node2NodeUpdatableField.disconnectionTime);
    }

    public List<Node2NodeUpdatableField> getUpdatedFields() {
        return updatedFields;
    }

    public enum Node2NodeUpdatableField {
        connectionTime,
        disconnectionTime
    }
}
