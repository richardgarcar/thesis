package cz.muni.fi.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "EXPERIMENT")
public class Experiment extends CommonEntity {

    private String name;
    private String description;
    private Date start;
    private Date end;
    private List<Experiment2Node> experiment2NodeList;
    private List<Node2Node> node2NodeList;

    @Column(name = "NAME", nullable = false)
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

    @Column(name = "START_TIME")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Column(name = "END_TIME")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @RestResource(path = "experiment2NodeList{?projection}")
    @OneToMany(mappedBy = "experiment")
    public List<Experiment2Node> getExperiment2NodeList() {
        return experiment2NodeList;
    }

    public void setExperiment2NodeList(List<Experiment2Node> experiment2NodeList) {
        this.experiment2NodeList = experiment2NodeList;
    }

    @RestResource(path = "node2NodeList{?projection}")
    @OneToMany(mappedBy = "firstNode")
    public List<Node2Node> getNode2NodeList() {
        return node2NodeList;
    }

    public void setNode2NodeList(List<Node2Node> node2NodeList) {
        this.node2NodeList = node2NodeList;
    }
}
