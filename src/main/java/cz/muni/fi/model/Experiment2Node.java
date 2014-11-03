package cz.muni.fi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "EXPERIMENT_2_NODE")
public class Experiment2Node extends CommonEntity {

    private Experiment experiment;
    private Node node;
    private Date additionTime;
    private Date removalTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "EXPERIMENT_ID")
    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "NODE_ID")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Column(name = "ADDITION_TIME")
    public Date getAdditionTime() {
        return additionTime;
    }

    public void setAdditionTime(Date additionTime) {
        this.additionTime = additionTime;
    }

    @Column(name = "REMOVAL_TIME")
    public Date getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(Date removalTime) {
        this.removalTime = removalTime;
    }
}
