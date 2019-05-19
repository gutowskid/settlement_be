package pl.edu.pw.mini.manager.rest.bill.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.model.bill.BaseBillDtoAssembler;
import pl.edu.pw.mini.manager.rest.bill.domain.BillManager;
import pl.edu.pw.mini.model.bill.BillDto;

@Component
public class BillDtoAssembler extends BaseBillDtoAssembler<BillManager, BillDto> {

    @Override
    public BillDto toDto(BillManager input) {
        BillDto dto = new BillDto();
        fill(input, dto);
        return dto;
    }
}
