package cz.muni.fi.cepv.web.to;

import javax.validation.constraints.NotNull;

/**
 * @author xgarcar
 */
public class NewQueryAttributeTO extends QueryAttributeTO {

    @Override
    @NotNull
    public String getKey() {
        return super.getKey();
    }

    @Override
    @NotNull
    public String getValue() {
        return super.getValue();
    }
}
