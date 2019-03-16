package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListTextSection extends AbstractSection {
    private final List<String> info;

    public ListTextSection(List<String> info) {
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
        ListTextSection that = (ListTextSection) obj;
        return info.equals(that.info);
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
