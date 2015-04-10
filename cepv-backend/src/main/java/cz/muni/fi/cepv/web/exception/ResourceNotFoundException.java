package cz.muni.fi.cepv.web.exception;

/**
 * @author xgarcar
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
