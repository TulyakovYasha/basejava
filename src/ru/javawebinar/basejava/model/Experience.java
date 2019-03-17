package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Experience extends AbstractSection {
    private final Link homePage;
    private final List<ExperienceInfo> list;

    public Experience(String name, String url, List<ExperienceInfo> list) {
        Objects.requireNonNull(list, "list must not be null");
        this.homePage = new Link(name, url);
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(list, that.list) &&
                Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, homePage);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "homePage=" + homePage +
                ", list=" + list +
                '}';
    }
}
