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
    private Date executionDate;
    private String content;
    private List<QueryAttribute> queryAttributes;

    @Transient
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

    @Column(name = "EXECUTION_DATE")
    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    @Column(name = "CONTENT", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(mappedBy = "query")
    public List<QueryAttribute> getQueryAttributes() {
        return queryAttributes;
    }

    public void setQueryAttributes(List<QueryAttribute> queryAttributes) {
        this.queryAttributes = queryAttributes;
    }
}
