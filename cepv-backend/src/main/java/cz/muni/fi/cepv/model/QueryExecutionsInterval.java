package cz.muni.fi.cepv.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.cepv.web.CustomDateSerializer;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author xgarcar
 */
public class QueryExecutionsInterval extends ResourceSupport {

    private Date intervalEndpoint;
    private BigInteger amount;

    public QueryExecutionsInterval() {
    }

    public QueryExecutionsInterval(Date intervalEndpoint, BigInteger amount) {
        this.intervalEndpoint = intervalEndpoint;
        this.amount = amount;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getIntervalEndpoint() {
        return intervalEndpoint;
    }

    public void setIntervalEndpointe(Date time) {
        this.intervalEndpoint = time;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }
}
