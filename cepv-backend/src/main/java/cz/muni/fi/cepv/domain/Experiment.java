package cz.muni.fi.cepv.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.cepv.web.CustomDateSerializer;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author xgarcar
 */
@Entity
@Table(name = "EXPERIMENT")
@Relation(collectionRelation = "experiments")
public class Experiment extends CommonEntity {

    private String name;
    private String description;
    private Date start;
    private Date end;

    public Experiment() {
    }

    public Experiment(String name, String description, Date start, Date end) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
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

    @Column(name = "START_TIME", nullable = false)
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Column(name = "END_TIME")
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
