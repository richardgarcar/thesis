package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.core.Relation;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "QUERY")
@Relation(collectionRelation = "queries")
public class Query extends CommonEntity {

    private Node node;
    private Experiment experiment;
    private Date deploymentTime;
    private String content;
    private List<QueryAttribute> queryAttributes;
    private List<QueryExecution> queryExecutions;

    public Query() {
    }

    public Query(Node node, Experiment experiment, Date deploymentTime, String content) {
        this.node = node;
        this.experiment = experiment;
        this.deploymentTime = deploymentTime;
        this.content = content;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "NODE_ID")
    @JsonIgnore
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "EXPERIMENT_ID")
    @JsonIgnore
    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    @Column(name = "DEPLOYMENT_TIME", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=CommonEntity.JSON_FORMAT_DATE_PATTERN, timezone = "CET")
    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    @Column(name = "CONTENT", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(mappedBy = "query", fetch = FetchType.EAGER)
    public List<QueryAttribute> getQueryAttributes() {
        return queryAttributes;
    }

    public void setQueryAttributes(List<QueryAttribute> queryAttributes) {
        this.queryAttributes = queryAttributes;
    }

    @OneToMany(mappedBy = "query")
    @JsonIgnore
    public List<QueryExecution> getQueryExecutions() {
        return queryExecutions;
    }

    public void setQueryExecutions(List<QueryExecution> queryExecutions) {
        this.queryExecutions = queryExecutions;
    }
}
