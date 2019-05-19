package pl.edu.pw.mini.employee.rest.bill.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.employee.rest.bill.domain.Bill;
import pl.edu.pw.mini.employee.api.bill.BillDto;

@Component
public class BillDtoAssembler extends DtoAssembler<Bill, BillDto> {
    @Override
    public BillDto toDto(Bill input) {
        BillDto dto = new BillDto();
        dto.setId(input.getId());
        dto.setSalary(input.getSalary());
        dto.setFrom(input.getFromDate());
        dto.setTo(input.getToDate());
        dto.setSettlementNumber(input.getSettlementNumber());
        dto.setHours(input.getHours());
        dto.setBrutto(dto.getHours() * dto.getSalary());
        dto.setIncomeCosts(dto.getBrutto() * 0.2);
        dto.setTax((dto.getBrutto() - dto.getIncomeCosts()) * 0.18);
        dto.setNetto(dto.getBrutto() - dto.getTax());
        return dto;
    }
}
