package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "NODE")
@Relation(collectionRelation = "nodes")
public class Node extends CommonEntity {

    private String externalId;
    private String name;
    private String description;
    private List<Experiment2Node> experiment2NodeList;
    private List<Query> queryList;

    public Node() {
    }

    public Node(String externalId, String name, String description) {
        this.externalId = externalId;
        this.name = name;
        this.description = description;
    }

    @Column(name = "EXTERNAL_ID", nullable = false, unique = true)
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

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

    @OneToMany(mappedBy = "node")
    @JsonIgnore
    public List<Experiment2Node> getExperiment2NodeList() {
        return experiment2NodeList;
    }

    public void setExperiment2NodeList(List<Experiment2Node> experiment2NodeList) {
        this.experiment2NodeList = experiment2NodeList;
    }

    @OneToMany(mappedBy = "node")
    @JsonIgnore
    public List<Query> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<Query> queryList) {
        this.queryList = queryList;
    }
}
