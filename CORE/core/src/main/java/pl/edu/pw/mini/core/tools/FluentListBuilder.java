package pl.edu.pw.mini.core.tools;

import java.util.ArrayList;
import java.util.List;

public class FluentListBuilder<T> {
    private List<T> list;

    private FluentListBuilder() {
        list = new ArrayList<>();
    }

    public static <T> FluentListBuilder<T> aList() {
        return new FluentListBuilder<>();
    }

    public static <T> FluentListBuilder<T> aListWith(T elem) {
        return FluentListBuilder.<T>aList().with(elem);
    }

    public FluentListBuilder<T> with(T elem) {
        list.add(elem);
        return this;
    }

    public List<T> build() {
        return list;
    }
}
