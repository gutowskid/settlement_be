package pl.edu.pw.mini.employee.rest.bill.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.bill.BaseBillDtoAssembler;
import pl.edu.pw.mini.employee.rest.bill.domain.Bill;
import pl.edu.pw.mini.model.bill.BillDto;

@Component
public class BillDtoAssembler extends BaseBillDtoAssembler<Bill, BillDto> {

    @Override
    public BillDto toDto(Bill input) {
        BillDto dto = new BillDto();
        fill(input, dto);
        return dto;
    }
}
