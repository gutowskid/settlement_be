package pl.edu.pw.mini.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception, HttpServletRequest request) {
        return new ResponseEntity<>(constructApiError(exception, request), HttpStatus.BAD_REQUEST);
    }

    private ApiError constructApiError(BusinessException exception, HttpServletRequest request) {
        return ApiError.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorCode(exception.getCode())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
