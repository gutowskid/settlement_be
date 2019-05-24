package pl.edu.pw.mini.manager.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException MAN_0001 = new BusinessException("MAN_0001", "Bill not found");
    public static BusinessException MAN_0002 = new BusinessException("MAN_0002", "Permission denied");
    public static BusinessException MAN_0003 = new BusinessException("MAN_0003", "Incorrect Bill Status");
    public static BusinessException MAN_0004 = new BusinessException("MAN_0004", "Invoice not found");
    public static BusinessException MAN_0005 = new BusinessException("MAN_0005", "Incorrect Invoice Status");
    public static BusinessException MAN_0006 = new BusinessException("MAN_0006", "Permission denied");
}
