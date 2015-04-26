package cz.muni.fi.cepv.querydsl;

import com.mysema.query.types.expr.BooleanExpression;
import cz.muni.fi.cepv.model.QExperiment2Node;

import java.util.Date;

/**
 * @author xgarcar
 */
public class Experiment2NodeQueryDsl {

    private static final QExperiment2Node experiment2Node = QExperiment2Node.experiment2Node;

    public static BooleanExpression isRelatedToExperiment(final Long experimentId){
        return  experiment2Node.experiment.id.eq(experimentId);
    }

    public static BooleanExpression filter(final Long experimentId, String nodeExternalId, final String nodeName,
                                           final String nodeDescription, final Date gtAdditionTime,
                                           final Date ltAdditionTime, final Date gtRemovalTime,
                                           final Date ltRemovalTime) {

        BooleanExpression filter = isRelatedToExperiment(experimentId);

        if (isExternalIdLike(nodeExternalId) != null) {
            filter = isExternalIdLike(nodeExternalId).and(filter);
        }
        if (isNameLike(nodeName) != null) {
            filter = isNameLike(nodeName).and(filter);
        }
        if (isDescriptionLike(nodeDescription) != null){
            filter = isDescriptionLike(nodeDescription).and(filter);
        }
        if (isAdditionTimeInInterval(gtAdditionTime, ltAdditionTime) != null){
            filter = isAdditionTimeInInterval(gtAdditionTime, ltAdditionTime).and(filter);
        }
        if (isRemovalTimeInInterval(gtRemovalTime, ltRemovalTime) != null){
            filter = isRemovalTimeInInterval(gtRemovalTime, ltRemovalTime).and(filter);
        }

        return filter;
    }

    private static BooleanExpression isExternalIdLike(final String externalId) {
        return externalId != null ? experiment2Node.node.externalId.containsIgnoreCase(externalId) : null;
    }

    private static BooleanExpression isNameLike(final String name) {
        return name != null ? experiment2Node.node.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isDescriptionLike(final String description) {
        return description != null ? experiment2Node.node.description.containsIgnoreCase(description) : null;
    }

    private static BooleanExpression isAdditionTimeInInterval(final Date gtAdditionTime, final Date ltAdditionTime) {
        BooleanExpression afterCondition = gtAdditionTime != null ? experiment2Node.additionTime.after(gtAdditionTime) : null;
        BooleanExpression beforeCondition = ltAdditionTime != null ? experiment2Node.additionTime.before(ltAdditionTime) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }

    private static BooleanExpression isRemovalTimeInInterval(final Date gtRemovalTime, final Date ltRemovalTime) {
        BooleanExpression afterCondition = gtRemovalTime != null ? experiment2Node.removalTime.after(gtRemovalTime) : null;
        BooleanExpression beforeCondition = ltRemovalTime != null ? experiment2Node.removalTime.before(ltRemovalTime) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }
}
