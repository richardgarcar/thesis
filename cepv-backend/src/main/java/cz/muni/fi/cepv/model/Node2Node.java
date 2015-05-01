package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.cepv.web.CustomDateSerializer;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "NODE_2_NODE")
@Relation(collectionRelation = "node2Nodes")
public class Node2Node extends CommonEntity {

    private Node firstNode;
    private Node secondNode;
    private Experiment experiment;
    private Date connectionTime;
    private Date disconnectionTime;

    public Node2Node() {
    }

    public Node2Node(Node firstNode, Node secondNode, Experiment experiment, Date connectionTime, Date disconnectionTime) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.experiment = experiment;
        this.connectionTime = connectionTime;
        this.disconnectionTime = disconnectionTime;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "FIRST_NODE_ID", updatable = false)
    @JsonProperty("embeddedFirstNode")
    public Node getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "SECOND_NODE_ID", updatable = false)
    @JsonProperty("embeddedSecondNode")
    public Node getSecondNode() {
        return secondNode;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "EXPERIMENT_ID", updatable = false)
    @JsonIgnore
    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
    }

    @Column(name = "CONNECTION_TIME", nullable = false)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Date connectionTime) {
        this.connectionTime = connectionTime;
    }

    @Column(name = "DISCONNECTION_TIME")
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDisconnectionTime() {
        return disconnectionTime;
    }

    public void setDisconnectionTime(Date disconnectionTime) {
        this.disconnectionTime = disconnectionTime;
    }
}
