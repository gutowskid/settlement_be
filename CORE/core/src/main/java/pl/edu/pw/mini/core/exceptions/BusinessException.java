package pl.edu.pw.mini.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Builder
@AllArgsConstructor
@ResponseStatus(value= HttpStatus.ACCEPTED)
public class BusinessException extends RuntimeException {
    private String code;
    private String message;
}
