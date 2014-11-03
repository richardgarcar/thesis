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

    @OneToMany(mappedBy = "firstNode")
    public List<Node2Node> getNode2NodeList() {
        return node2NodeList;
    }

    public void setNode2NodeList(List<Node2Node> node2NodeList) {
        this.node2NodeList = node2NodeList;
    }
}
