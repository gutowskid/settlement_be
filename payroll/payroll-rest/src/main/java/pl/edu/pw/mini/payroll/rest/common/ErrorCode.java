package pl.edu.pw.mini.payroll.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException PAY_0001 = new BusinessException("PAY_0001", "Bill not exists");
    public static BusinessException PAY_0002 = new BusinessException("PAY_0002", "Bill not exists");
}
