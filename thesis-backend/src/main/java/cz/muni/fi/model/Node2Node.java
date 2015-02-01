package cz.muni.fi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "NODE_2_NODE")
public class Node2Node extends CommonEntity {

    private Node firstNode;
    private Node secondNode;
    private Date connectionTime;
    private Date disconnectionTime;
    private Experiment experiment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FIRST_NODE_ID")
    public Node getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "SECOND_NODE_ID")
    public Node getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
    }

    @Column(name = "CONNECTION_TIME", nullable = false)
    public Date getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Date connectionTime) {
        this.connectionTime = connectionTime;
    }

    @Column(name = "DISCONNECTION_TIME")
    public Date getDisconnectionTime() {
        return disconnectionTime;
    }

    public void setDisconnectionTime(Date disconnectionTime) {
        this.disconnectionTime = disconnectionTime;
    }

    @Transient
    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }
}
