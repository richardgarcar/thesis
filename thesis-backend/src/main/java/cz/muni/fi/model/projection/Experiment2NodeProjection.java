package cz.muni.fi.model.projection;

import cz.muni.fi.model.Experiment2Node;
import cz.muni.fi.model.Node;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * @author xgarcar
 */
@Projection(name = "embedded",types = Experiment2Node.class)
public interface Experiment2NodeProjection {

    Node getNode();
    Date getAdditionTime();
    Date getRemovalTime();
}
