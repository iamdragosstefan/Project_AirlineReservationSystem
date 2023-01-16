package data;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String email;
    private String name;
    private String parola;

    private List<Flight> userFlights = new ArrayList<>();

    public User(String id, String email, String name, String parola) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.parola = parola;
    }

    public void addFlight(Flight flight){
        userFlights.add(flight);
    }

    public void deleteFlight (Flight flight){
        userFlights.remove(flight);
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getParola() {
        return parola;
    }

    public List<Flight> getUserFlights() {
        return userFlights;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", parola='" + parola + '\'' +
                ", userFlights=" + userFlights +
                '}';
    }
}
