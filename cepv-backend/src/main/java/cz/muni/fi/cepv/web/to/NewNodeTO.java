package cz.muni.fi.cepv.web.to;

import javax.validation.constraints.NotNull;

/**
 * @author xgarcar
 */
public class NewNodeTO extends NodeTO {

    @Override
    @NotNull
    public String getExternalId() {
        return super.getExternalId();
    }

    @Override
    @NotNull
    public String getName() {
        return super.getName();
    }
}
