package pl.edu.pw.mini.core.tools;

import java.util.HashMap;
import java.util.Map;

public class FluentMapBuilder<T, Z> {
    private Map<T, Z> map;

    private FluentMapBuilder() {
        map = new HashMap<>();
    }

    public static <T, Z> FluentMapBuilder<T, Z> aMap() {
        return new FluentMapBuilder<>();
    }

    public FluentMapBuilder<T, Z> with(T key, Z value) {
        map.put(key, value);
        return this;
    }

    public Map<T, Z> build() {
        return map;
    }
}
