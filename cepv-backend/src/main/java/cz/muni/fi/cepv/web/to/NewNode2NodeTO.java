package cz.muni.fi.cepv.web.to;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xgarcar
 */
public class NewNode2NodeTO extends Node2NodeTO {

    @Override
    @NotNull
    public String getFirstNode() {
        return super.getFirstNode();
    }

    @Override
    @NotNull
    public String getSecondNode() {
        return super.getSecondNode();
    }

    @Override
    @NotNull
    public Date getConnectionTime() {
        return super.getConnectionTime();
    }
}
