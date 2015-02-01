package cz.muni.fi.model.projection;

import cz.muni.fi.model.Experiment2Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * @author xgarcar
 */
@Projection(name = "with_node_data", types = Experiment2Node.class)
public interface Experiment2NodeProjection {

    @Value("#{target.node.name}")
    String getNodeName();
    @Value("#{target.node.description}")
    String getNodeDescription();
    Date getAdditionTime();
    Date getRemovalTime();
}
