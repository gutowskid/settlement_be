package pl.edu.pw.mini.auth.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException AUT_0001 = new BusinessException("AUT_0001", "User not exists");
    public static BusinessException AUT_0002 = new BusinessException("AUT_0002", "Bad password");
}
