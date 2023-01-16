package data;

import java.time.LocalDate;
import java.util.Objects;

public class Flight {

    private String id;
    private String from;
    private String to;
    private String date;
    private String duration;

    public Flight(String id, String from, String to, String date, String duration) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Flight" +
                " from " + from +
                ", to " + to +
                ", date " + date +
                ", duration " + duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(from, flight.from) && Objects.equals(to, flight.to) && Objects.equals(date, flight.date) && Objects.equals(duration, flight.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, date, duration);
    }
}
