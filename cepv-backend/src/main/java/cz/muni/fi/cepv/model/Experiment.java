package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=CommonEntity.JSON_FORMAT_DATE_PATTERN, timezone = "CET")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Column(name = "END_TIME")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=CommonEntity.JSON_FORMAT_DATE_PATTERN, timezone = "CET")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
