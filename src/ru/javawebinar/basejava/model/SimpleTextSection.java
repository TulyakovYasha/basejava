package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SimpleTextSection extends AbstractSection {
    private String someInfo;

    public SimpleTextSection(String someInfo) {
        Objects.requireNonNull(someInfo, "Text sction must not be null");
        this.someInfo = someInfo;
    }

    public String getSomeInfo() {
        return someInfo;
    }

    @Override
    public int hashCode() {
        return someInfo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SimpleTextSection that = (SimpleTextSection) obj;

        return someInfo.equals(that.someInfo);
    }

    @Override
    public String toString() {
        return someInfo;
    }
}
