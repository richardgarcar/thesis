package cz.muni.fi.model.projection;

import cz.muni.fi.model.Node;
import cz.muni.fi.model.Node2Node;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * @author xgarcar
 */
@Projection(name = "embedded", types = Node2Node.class)
public interface Node2NodeProjection {

    Node getFirstNode();
    Node getSecondNode();
    Date getConnectionTime();
    Date getDisconnectionTime();
}
