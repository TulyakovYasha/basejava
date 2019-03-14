package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience extends Section {
    private String nameCompany;
    private LocalDate startDate;
    private LocalDate endDate;
    private String position;
    private String info;

    public Experience(String nameCompany, LocalDate startDate, LocalDate endDate, String info) {
        this.nameCompany = nameCompany;
        this.startDate = startDate;
        this.endDate = endDate;
        this.info = info;
    }

    public Experience(String nameCompany, LocalDate startDate, LocalDate endDate, String position, String info) {
        this.nameCompany = nameCompany;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.info = info;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPosition() {
        return position;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(nameCompany, that.nameCompany) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(position, that.position) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCompany, startDate, endDate, position, info);
    }

    @Override
    public String toString() {
        String now;
        if (endDate == null) {
            now = "Сейчас";
            return "Experience{" +
                    "nameCompany='" + nameCompany + '\'' +
                    ", startDate=" + startDate.getYear() +
                    "-" + startDate.getMonthValue() +
                    ", endDate=" + now +
                    ", position='" + position + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
        return "Experience{" +
                "nameCompany='" + nameCompany + '\'' +
                ", startDate=" + startDate.getYear()+
                "-" + startDate.getMonthValue() +
                ", endDate=" + endDate.getYear() +
                "-" + startDate.getMonthValue() +
                ", position='" + position + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
