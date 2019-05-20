package pl.edu.pw.mini.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonListRequest<T> {
    private int pageNumber = 0;
    private int pageSize = 10;
    private T searchCriteria;
}
