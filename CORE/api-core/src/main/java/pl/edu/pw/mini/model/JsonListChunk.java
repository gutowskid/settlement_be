package pl.edu.pw.mini.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonListChunk<T> {
    private List<T> list;
    private Long count;
    private Boolean hasNext;
}
