package cz.muni.fi.model;

import org.springframework.data.rest.core.annotation.RestResource;

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
public class Node extends CommonEntity {

    private String name;
    private String description;
    private List<Node2Node> node2NodeList;
    private List<Query> queryList;

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

    @RestResource(path = "queryList{?projection}")
    @OneToMany(mappedBy = "node")
    public List<Query> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<Query> queryList) {
        this.queryList = queryList;
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
