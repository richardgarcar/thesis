package cz.muni.fi.cepv.repository.querydsl;

import com.mysema.query.types.expr.BooleanExpression;
import cz.muni.fi.cepv.model.QQuery;

import java.util.Date;

/**
 * @author xgarcar
 */
public class QueryQueryDsl {

    public static BooleanExpression isRelatedToExperimentAndNode(final Long experimentId, final String externalNodeId){
        return QQuery.query.experiment.id.eq(experimentId).and(QQuery.query.node.externalId.eq(externalNodeId));
    }

    public static BooleanExpression filter(final Long experimentId, final String externalNodeId,
                                           final Date gtDeploymentTime, final Date ltDeploymentTime) {

        BooleanExpression filter = isRelatedToExperimentAndNode(experimentId, externalNodeId);

        if (isDeploymentTimeInInterval(gtDeploymentTime, ltDeploymentTime) != null){
            filter = isDeploymentTimeInInterval(gtDeploymentTime, ltDeploymentTime).and(filter);
        }

        return filter;
    }

    private static BooleanExpression isDeploymentTimeInInterval(final Date gtDeploymentTime, final Date ltDeploymentTime) {
        BooleanExpression afterCondition = gtDeploymentTime != null ? (QQuery.query.deploymentTime.after(gtDeploymentTime)) : null;
        BooleanExpression beforeCondition = ltDeploymentTime != null ? (QQuery.query.deploymentTime.before(ltDeploymentTime)) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }
}
