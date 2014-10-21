package cz.muni.fi.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "EXPERIMENT")
public class Experiment extends CommonEntity {

    private String name;
    private String description;
    private List<Node> nodeList;

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    @JoinTable(name = "EXPERIMENT_2_NODE", joinColumns = {
            @JoinColumn(name = "EXPERIMENT_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "NODE_ID", nullable = false, updatable = false)})
    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
