package pl.edu.pw.mini.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private Date timestamp;
    private int status;
    private String errorCode;
    private String message;
    private String path;
}
