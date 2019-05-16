package pl.edu.pw.mini.core.tools;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DtoAssembler<T, Z> {

    public abstract Z toDto(T input);

    public List<Z> toDtoList(List<T> input) {
        return input.stream().map(this::toDto).collect(Collectors.toList());
    }
}
