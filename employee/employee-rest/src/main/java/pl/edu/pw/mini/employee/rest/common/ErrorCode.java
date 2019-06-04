package pl.edu.pw.mini.employee.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException EMP_0001 = new BusinessException("EMP_0001", "Salary is not defined");
    public static BusinessException EMP_0002 = new BusinessException("EMP_0002", "Account number is not set");
    public static BusinessException EMP_0003 = new BusinessException("EMP_0003", "Invalid Account number");
    public static BusinessException EMP_0004 = new BusinessException("EMP_0004", "BaseBill not found");
    public static BusinessException EMP_0005 = new BusinessException("EMP_0005", "Permission denied");
    public static BusinessException EMP_0006 = new BusinessException("EMP_0006", "Incorrect BaseBill status");
    public static BusinessException EMP_0007 = new BusinessException("EMP_0007", "Bill is already created");
    public static BusinessException EMP_0008 = new BusinessException("EMP_0008", "Bill not exists");
    public static BusinessException EMP_0009 = new BusinessException("EMP_0009", "Invoice not exists");
    public static BusinessException EMP_0010 = new BusinessException("EMP_0010", "Permission denied");
    public static BusinessException EMP_0011 = new BusinessException("EMP_0011", "Invoice not exists");
    public static BusinessException EMP_0012 = new BusinessException("EMP_0012", "Permission denied");
    public static BusinessException EMP_0013 = new BusinessException("EMP_0013", "Invalid invoice status");
    public static BusinessException EMP_0014 = new BusinessException("EMP_0014", "Invalid bill status");
}
