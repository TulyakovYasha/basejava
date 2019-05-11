package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<String> info;

    public ListSection() {
    }

    public ListSection(String... info) {
        this(Arrays.asList(info));
    }

    public ListSection(List<String> info) {
        Objects.requireNonNull(info, "List info must not be null");
        this.info = info;
    }

    public List<String> getList() {
        return info;
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListSection that = (ListSection) obj;
        return info.equals(that.info);
    }

    @Override
    public String toString() {
        return String.join("," , info);
    }
}
