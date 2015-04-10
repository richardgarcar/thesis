package cz.muni.fi.cepv.web.to;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xgarcar
 */
public class NewQueryTO extends QueryTO {

    @Override
    @NotNull
    public String getContent() {
        return super.getContent();
    }

    @Override
    @NotNull
    public Date getDeploymentTime() {
        return super.getDeploymentTime();
    }
}
