package pl.edu.pw.mini.core.tools;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ExternalAssembler<T, Z> {

    public abstract Z toExternal(T input);

    public List<Z> toExternalList(List<T> input) {
        return input.stream().map(this::toExternal).collect(Collectors.toList());
    }
}
