package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ExperienceListSection extends Section {
    List<Section> list;

    public ExperienceListSection(List<Section> list) {
        this.list = list;
    }

    public List<Section> getList() {
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
