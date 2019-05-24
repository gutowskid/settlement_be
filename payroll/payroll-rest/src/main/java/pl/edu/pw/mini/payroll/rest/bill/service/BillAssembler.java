package pl.edu.pw.mini.payroll.rest.bill.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.bill.BaseBillDtoAssembler;
import pl.edu.pw.mini.payroll.api.bill.BillAccountNumberDto;
import pl.edu.pw.mini.payroll.rest.bill.domain.Bill;

@Component
public class BillAssembler extends BaseBillDtoAssembler<Bill, BillAccountNumberDto> {

    @Override
    public BillAccountNumberDto toDto(Bill input) {
        BillAccountNumberDto dto = new BillAccountNumberDto();
        fill(input, dto);
        dto.setAccountNumber(input.getAccountNumber());
        return dto;
    }
}
