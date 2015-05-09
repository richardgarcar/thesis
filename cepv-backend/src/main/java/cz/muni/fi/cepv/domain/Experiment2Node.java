package cz.muni.fi.cepv.domain;

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
@Table(name = "EXPERIMENT_2_NODE")
@Relation(collectionRelation = "experiment2Nodes")
public class Experiment2Node extends CommonEntity {

    private Experiment experiment;
    private Node node;
    private Date additionTime;
    private Date removalTime;

    public Experiment2Node() {
    }

    public Experiment2Node(Experiment experiment, Node node, Date additionTime, Date removalTime) {
        this.experiment = experiment;
        this.node = node;
        this.additionTime = additionTime;
        this.removalTime = removalTime;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "NODE_ID", updatable = false)
    @JsonProperty("embeddedNode")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Column(name = "ADDITION_TIME", nullable = false)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAdditionTime() {
        return additionTime;
    }

    public void setAdditionTime(Date additionTime) {
        this.additionTime = additionTime;
    }

    @Column(name = "REMOVAL_TIME")
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(Date removalTime) {
        this.removalTime = removalTime;
    }
}
