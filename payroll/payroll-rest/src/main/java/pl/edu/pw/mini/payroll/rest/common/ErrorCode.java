package pl.edu.pw.mini.payroll.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException PAY_0001 = new BusinessException("PAY_0001", "Bill not exists");
    public static BusinessException PAY_0002 = new BusinessException("PAY_0002", "Bill not exists");
    public static BusinessException PAY_0003 = new BusinessException("PAY_0003", "Invoice not exists");
    public static BusinessException PAY_0004 = new BusinessException("PAY_0004", "Invoice not exists");
    public static BusinessException PAY_0005 = new BusinessException("PAY_0005", "Invalid invoice status");
    public static BusinessException PAY_0006 = new BusinessException("PAY_0006", "Invalid invoice status");
    public static BusinessException PAY_0007 = new BusinessException("PAY_0007", "Invalid bill status");
    public static BusinessException PAY_0008 = new BusinessException("PAY_0008", "Invalid bill status");
    public static BusinessException PAY_0009 = new BusinessException("PAY_0009", "Invoice not found!");
}
