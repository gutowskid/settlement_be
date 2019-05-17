package pl.edu.pw.mini.employee.rest.common;

import pl.edu.pw.mini.core.exceptions.BusinessException;

public class ErrorCode {
    public static BusinessException EMP_0001 = new BusinessException("DAL_0001", "Salary is not defined");
}
