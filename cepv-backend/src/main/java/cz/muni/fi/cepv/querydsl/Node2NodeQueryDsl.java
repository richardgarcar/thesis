package cz.muni.fi.cepv.querydsl;

import com.mysema.query.types.expr.BooleanExpression;
import cz.muni.fi.cepv.model.QNode2Node;

import java.util.Date;

/**
 * @author xgarcar
 */
public class Node2NodeQueryDsl {

    public static BooleanExpression isRelatedToExperiment(final Long experimentId){
        return  QNode2Node.node2Node.experiment.id.eq(experimentId);
    }
    
    public static BooleanExpression filter(final Long experimentId,
                                           final String firstNodeExternalId, final String secondNodeExternalId,
                                           final String firstNodeName, final String secondNodeName,
                                           final Date gtConnectionTime, final Date ltConnectionTime,
                                           final Date gtDisconnectionTime, final Date ltDisconnectionTime) {

        BooleanExpression filter = isRelatedToExperiment(experimentId);

        if (isFirstNodeNameLike(firstNodeName) != null) {
            filter = isFirstNodeNameLike(firstNodeName).and(filter);
        }
        if (isSecondNodeNameLike(secondNodeName) != null) {
            filter = isSecondNodeNameLike(secondNodeName).and(filter);
        }
        if (isFirstNodeExternalIdLike(firstNodeExternalId) != null){
            filter = isFirstNodeExternalIdLike(firstNodeExternalId).and(filter);
        }
        if (isSecondNodeExternalIdLike(secondNodeExternalId) != null){
            filter = isSecondNodeExternalIdLike(secondNodeExternalId).and(filter);
        }
        if (isConnectionTimeInInterval(gtConnectionTime, ltConnectionTime) != null){
            filter = isConnectionTimeInInterval(gtConnectionTime, ltConnectionTime).and(filter);
        }
        if (isDisconnectionTimeInInterval(gtDisconnectionTime, ltDisconnectionTime) != null){
            filter = isDisconnectionTimeInInterval(gtDisconnectionTime, ltDisconnectionTime).and(filter);
        }

        return filter;
    }

    private static BooleanExpression isFirstNodeNameLike(final String name) {
        return name != null ? QNode2Node.node2Node.firstNode.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isSecondNodeNameLike(final String name) {
        return name != null ? QNode2Node.node2Node.secondNode.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isFirstNodeExternalIdLike(final String externalId) {
        return externalId != null ? QNode2Node.node2Node.firstNode.externalId.containsIgnoreCase(externalId) : null;
    }

    private static BooleanExpression isSecondNodeExternalIdLike(final String externalId) {
        return externalId != null ? QNode2Node.node2Node.secondNode.externalId.containsIgnoreCase(externalId) : null;
    }

    private static BooleanExpression isConnectionTimeInInterval(final Date gtConnectionTime, final Date ltConnectionTime) {
        BooleanExpression afterCondition = gtConnectionTime != null ? QNode2Node.node2Node.connectionTime.after(gtConnectionTime) : null;
        BooleanExpression beforeCondition = ltConnectionTime != null ? QNode2Node.node2Node.connectionTime.before(ltConnectionTime) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }

    private static BooleanExpression isDisconnectionTimeInInterval(final Date gtDisconnectionTime, final Date ltDisconnectionTime) {
        BooleanExpression afterCondition = gtDisconnectionTime != null ? (QNode2Node.node2Node.disconnectionTime.after(gtDisconnectionTime)).or(QNode2Node.node2Node.disconnectionTime.isNull()) : null;
        BooleanExpression beforeCondition = ltDisconnectionTime != null ? (QNode2Node.node2Node.disconnectionTime.before(ltDisconnectionTime)).or(QNode2Node.node2Node.disconnectionTime.isNull()) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }
}
