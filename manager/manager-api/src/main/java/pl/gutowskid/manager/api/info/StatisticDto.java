package pl.gutowskid.manager.api.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto {
    private Long employeesCount;
    private Long billCount;
    private Long invoicesCount;
}
