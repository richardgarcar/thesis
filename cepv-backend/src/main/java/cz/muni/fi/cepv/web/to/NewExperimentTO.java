package cz.muni.fi.cepv.web.to;

import javax.validation.constraints.NotNull;

/**
 * @author xgarcar
 */
public class NewExperimentTO extends ExperimentTO {

    @Override
    @NotNull
    public String getName() {
        return super.getName();
    }

    @Override
    @NotNull
    public String getDescription() {
        return super.getDescription();
    }
}
