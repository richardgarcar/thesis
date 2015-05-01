package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cz.muni.fi.cepv.web.CustomDateDeserializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xgarcar
 */
public class QueryExecutionTO implements Serializable{

    private static final long serialVersionUID = -6806239818539033204L;

    private Date executionTime;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionDate) {
        this.executionTime = executionDate;
    }
}
