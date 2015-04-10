package cz.muni.fi.cepv.web.to;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xgarcar
 */
public class NewExperiment2NodeTO extends Experiment2NodeTO {

    @Override
    @NotNull
    public String getNode() {
        return super.getNode();
    }

    @Override
    @NotNull
    public Date getAdditionTime() {
        return super.getAdditionTime();
    }
}
