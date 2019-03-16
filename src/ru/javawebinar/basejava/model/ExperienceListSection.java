package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ExperienceListSection extends AbstractSection {
    private List<AbstractSection> list;

    public ExperienceListSection(List<AbstractSection> list) {
        Objects.requireNonNull(list, "Experience must not be null");
        this.list = list;
    }

    public List<AbstractSection> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceListSection that = (ExperienceListSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }


}
