package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class ExperienceInfo extends AbstractSection {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private String info;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceInfo that = (ExperienceInfo) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(position, that.position) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, position, info);
    }

    public ExperienceInfo(LocalDate startDate, LocalDate endDate, String position) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
    }

    public ExperienceInfo(LocalDate startDate, LocalDate endDate, String position, String info) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.info = info;
    }

    @Override
    public String toString() {
        String now;
        if (endDate == null) {
            now = "Сейчас";
            return "Experience{" +
                    ", startDate=" + startDate.getYear() +
                    "-" + startDate.getMonthValue() +
                    ", endDate=" + now +
                    ", position='" + position + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
        return "Experience{" +
                ", startDate=" + startDate.getYear() +
                "-" + startDate.getMonthValue() +
                ", endDate=" + endDate.getYear() +
                "-" + startDate.getMonthValue() +
                ", position='" + position + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
