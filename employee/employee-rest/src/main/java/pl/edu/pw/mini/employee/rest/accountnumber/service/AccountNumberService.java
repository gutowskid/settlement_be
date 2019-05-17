package pl.edu.pw.mini.employee.rest.accountnumber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.edu.pw.mini.employee.rest.accountnumber.domain.AccountNumber;
import pl.edu.pw.mini.employee.rest.accountnumber.domain.AccountNumberRepository;

import java.math.BigInteger;

import static pl.edu.pw.mini.employee.rest.common.ErrorCode.*;

@Service
public class AccountNumberService {

    @Autowired
    private AccountNumberRepository accountNumberRepository;

    public StringWrapper getMyAccountNumber(String employeeId) {
        return StringWrapper.fromValue(accountNumberRepository.findById(employeeId).orElseThrow(() -> EMP_0002).getAccountNumber());
    }

    public void setMyAccountNumber(String employeeId, String number) {
        validateAccountNumber(number);
        AccountNumber accountNumber = accountNumberRepository.findById(employeeId).orElse(new AccountNumber());
        accountNumber.setEmployeeId(employeeId);
        accountNumber.setAccountNumber(number);
        accountNumberRepository.save(accountNumber);
    }

    private void validateAccountNumber(String number) {
        if(number.length() != 26) {
            throw EMP_0003;
        }
        number = number + "2521" + number.substring(0, 2);
        number = number.substring(2, number.length());
        BigInteger intNumber = new BigInteger(number);
        BigInteger[] numbers = intNumber.divideAndRemainder(BigInteger.valueOf(97L));
        if(!numbers[1].equals(BigInteger.ONE)) {
            throw EMP_0003;
        }
    }
}
