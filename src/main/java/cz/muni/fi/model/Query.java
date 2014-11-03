package cz.muni.fi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "QUERY")
public class Query extends CommonEntity {

    private Experiment experiment;
    private Node node;
    private Date addDate;
    private String content;
    private List<QueryAttribute> queryAttributeList;

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

    @Column(name = "ADD_DATE")
    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Column(name = "CONTENT", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUERY_ID")
    public List<QueryAttribute> getQueryAttributeList() {
        return queryAttributeList;
    }

    public void setQueryAttributeList(List<QueryAttribute> queryAttributeList) {
        this.queryAttributeList = queryAttributeList;
    }
}
