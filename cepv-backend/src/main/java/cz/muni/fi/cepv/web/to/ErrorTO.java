package cz.muni.fi.cepv.web.to;

import java.io.Serializable;

/**
 * @author xgarcar
 */
public class ErrorTO implements Serializable {

    private static final long serialVersionUID = -3868958883026188533L;

    public ErrorTO(String requestUri, String status, String message) {
        this.requestUri = requestUri;
        this.status = status;
        this.message = message;
    }

    private String requestUri;
    private String status;
    private String message;

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
