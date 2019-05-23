package pl.edu.pw.mini.core.model.bill;

import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.model.bill.BillDto;

public abstract class BaseBillDtoAssembler<X extends BaseBill, Z extends BillDto> extends DtoAssembler<X, Z> {

    public void fill(X input, Z dto) {
        dto.setEmployeeId(input.getEmployeeId());
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
    }
}
