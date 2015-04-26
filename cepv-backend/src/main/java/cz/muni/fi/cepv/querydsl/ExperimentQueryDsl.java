package cz.muni.fi.cepv.querydsl;

import com.mysema.query.types.expr.BooleanExpression;
import cz.muni.fi.cepv.model.QExperiment;

import java.util.Date;

/**
 * @author xgarcar
 */
public class ExperimentQueryDsl {

    public static BooleanExpression filter(final String name, final String description, final Date gtStart,
                                           final Date ltStart, final Date gtEnd, final Date ltEnd) {
        BooleanExpression filter = null;

        if (isNameLike(name) != null) {
            filter = isNameLike(name);
        }
        if (isDescriptionLike(description) != null){
            filter = isDescriptionLike(description).and(filter);
        }
        if (isStartInInterval(gtStart, ltStart) != null){
            filter = isStartInInterval(gtStart, ltStart).and(filter);
        }
        if (isEndInInterval(gtEnd, ltEnd) != null){
            filter = isEndInInterval(gtEnd, ltEnd).and(filter);
        }

        return filter;
    }

    private static BooleanExpression isNameLike(final String name) {
        return name != null ? QExperiment.experiment.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isDescriptionLike(final String description) {
        return description != null ? QExperiment.experiment.description.containsIgnoreCase(description) : null;
    }

    private static BooleanExpression isStartInInterval(final Date gtStart, final Date ltStart) {
        BooleanExpression afterCondition = gtStart != null ? QExperiment.experiment.start.after(gtStart) : null;
        BooleanExpression beforeCondition = ltStart != null ? QExperiment.experiment.start.before(ltStart) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }

    private static BooleanExpression isEndInInterval(final Date gtEnd, final Date ltEnd) {
        BooleanExpression afterCondition = gtEnd != null ? QExperiment.experiment.end.after(gtEnd) : null;
        BooleanExpression beforeCondition = ltEnd != null ? QExperiment.experiment.end.before(ltEnd) : null;
        return afterCondition != null ? afterCondition.and(beforeCondition) : beforeCondition;
    }
}
