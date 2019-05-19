package pl.edu.pw.mini.employee.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException EMP_0001 = new BusinessException("EMP_0001", "Salary is not defined");
    public static BusinessException EMP_0002 = new BusinessException("EMP_0002", "Account number is not set");
    public static BusinessException EMP_0003 = new BusinessException("EMP_0003", "Invalid Account number");
    public static BusinessException EMP_0004 = new BusinessException("EMP_0004", "Bill not found");
    public static BusinessException EMP_0005 = new BusinessException("EMP_0005", "Permission denied");
    public static BusinessException EMP_0006 = new BusinessException("EMP_0006", "Incorrect Bill status");
}
