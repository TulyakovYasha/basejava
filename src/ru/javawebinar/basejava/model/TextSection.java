package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private String text;

    public TextSection() {
    }

    public TextSection(String text) {
        Objects.requireNonNull(text, "Text section must not be null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TextSection that = (TextSection) obj;

        return text.equals(that.text);
    }

    @Override
    public String toString() {
        return text;
    }
}
