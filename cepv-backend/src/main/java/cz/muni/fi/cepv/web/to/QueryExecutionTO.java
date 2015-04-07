package cz.muni.fi.cepv.web.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xgarcar
 */
public class QueryExecutionTO implements Serializable{

    private static final long serialVersionUID = -6806239818539033204L;

    private Date executionTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "CET")
    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionDate) {
        this.executionTime = executionDate;
    }
}
