package ru.javawebinar.basejava.model;

public class SympleTextSection extends Section {
    private String someInfo;

    public SympleTextSection(String someInfo) {
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

        SympleTextSection that = (SympleTextSection) obj;

        return someInfo.equals(that.someInfo);
    }

    @Override
    public String toString() {
        return someInfo;
    }
}
