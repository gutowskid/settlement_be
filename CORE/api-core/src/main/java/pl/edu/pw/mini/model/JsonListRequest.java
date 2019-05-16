package pl.edu.pw.mini.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonListRequest<T> {
    private Long pageNumber;
    private Long pageSize;
    private T searchCriteria;
}
