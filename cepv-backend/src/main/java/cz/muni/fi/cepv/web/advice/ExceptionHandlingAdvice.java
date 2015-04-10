package cz.muni.fi.cepv.web.advice;

import cz.muni.fi.cepv.web.exception.ResourceNotFoundException;
import cz.muni.fi.cepv.web.to.ErrorTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xgarcar
 */
@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpEntity<ErrorTO> handleResourceNotFound(HttpServletRequest req, Exception e) {
        final ErrorTO exceptionInfo = new ErrorTO(req.getRequestURI(), "404 Not Found",  e.getMessage());

        return new ResponseEntity<>(exceptionInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public HttpEntity<ErrorTO> handleBadRequest(HttpServletRequest req, Exception e) {
        final ErrorTO exceptionInfo = new ErrorTO(req.getRequestURI(), "400 Bad Request",  e.getMessage());

        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

}
