package cz.muni.fi.model.projection;

import cz.muni.fi.model.Query;
import cz.muni.fi.model.QueryAttribute;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * @author xgarcar
 */
@Projection(name = "with_attributes", types = Query.class)
public interface QueryProjection {

    String getContent();
    Date getExecutionDate();
    List<QueryAttribute> getQueryAttributes();
}
