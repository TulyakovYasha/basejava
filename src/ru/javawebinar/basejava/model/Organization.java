package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private List<Position> positions;


    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(positions, that.positions) &&
                Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions, homePage);
    }

    @Override
    public String toString() {
        return "Organization(" + homePage + "," + positions + ')';
    }

    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String tittle;
        private String info;

        public Position(int startYear, Month startMonth, String position, String info) {
            this(of(startYear, startMonth), NOW, position, info);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String tittle, String info) {
            this(of(startYear, startMonth), of(endYear, endMonth), tittle, info);
        }

        public Position(LocalDate startDate, LocalDate endDate, String tittle, String info) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(tittle, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.tittle = tittle;
            this.info = info;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return tittle;
        }

        public String getInfo() {
            return info;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return startDate.equals(that.startDate) &&
                    endDate.equals(that.endDate) &&
                    tittle.equals(that.tittle) &&
                    Objects.equals(info, that.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, tittle, info);
        }

        @Override
        public String toString() {
            return "ExperienceInfo(" + startDate + ',' + endDate + ',' + tittle + ',' + info + ')';
        }
    }
}
