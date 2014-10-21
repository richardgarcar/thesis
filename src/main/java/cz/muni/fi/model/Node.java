package cz.muni.fi.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "NODE")
public class Node extends CommonEntity {

    private String name;
    private String description;
    private List<Experiment> experimentList;

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

    @ManyToMany(mappedBy = "nodeList", fetch = FetchType.LAZY)
    public List<Experiment> getExperimentList() {
        return experimentList;
    }

    public void setExperimentList(List<Experiment> experimentList) {
        this.experimentList = experimentList;
    }
}
