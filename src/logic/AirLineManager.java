package logic;

import data.Flight;
import data.User;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static constants.Messages.*;

public class AirLineManager {

    private static final String JDBC_URL="jdbc:mysql://localhost:3306/proiect_itschool";
    private static final String USER="root";
    private static final String PASSWORD="";

    private WriterManager writerManager = new WriterManager();

    private List<User> allUser = new ArrayList<>();
    private User currentUser;

    private List<Flight> allFlights = new ArrayList<>();
    private List<Flight> allUsersFlights = new ArrayList<>();

    public List<Flight> getAllUsersFlights(){
        return allUsersFlights;
    }


    public void signUp(String[] arguments) {
        String id = arguments[1];
        String email = arguments[2];
        String name = arguments[3];
        String password = arguments[4];
        String confirmPassword = arguments[5];

        Optional<User> optionalUserId = allUser.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        Optional<User> optionalUserEmail = allUser.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();

        if(!password.equals(confirmPassword)){
            writerManager.write(cannotAddUserPasswordDiff());
        } else if (password.length() < 8){
            writerManager.write(passwordLenghtShort());
        } else if (optionalUserId.isPresent()) {
            writerManager.write(userAlreadyExists());
        } else if (optionalUserEmail.isPresent()) {
            writerManager.write(userAlreadyExists());
        }else {
            User user = new User(id, email, name, password);
            allUser.add(user);
            writerManager.write(userSuccessfullyAdded(email));
        }

    }

    public void login(String[] arguments) {
        String email = arguments[1];
        String parola = arguments[2];

        Optional<User> optionalUser = allUser.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
        if (optionalUser.isEmpty()){
            writerManager.write(cannotFindUserWithEmail(email));
            return;
        }

        User user = optionalUser.get();
        if(!user.getParola().equals(parola)){
            writerManager.write(incorrectPassword());
            return;
        }

        if(currentUser != null){
            writerManager.write(anotherUserIsConnected());
            return;
        }

        currentUser = user;
        writerManager.write(currentUser(email));

    }

    public void logout(String[] arguments){
        String email = arguments[1];

        if(currentUser.getEmail().equals(email)){
            writerManager.write(userDisconnected(email));
            currentUser = null;
        } else {
            writerManager.write(userWasNotConnected(email));
        }

    }

    public void displayMyFlights(String[] arguments){

        if(currentUser == null){
            writerManager.write(noConnectedUser());
        } else if (currentUser.getUserFlights().isEmpty()) {
            // AM ADAUGAT SI ACEASTA VALIDARE
            writerManager.write(noFlightBooked(currentUser.getEmail()));
        } else {
            if(!allFlights.isEmpty()){
                for (Flight flight : allFlights){
                    writerManager.write(String.valueOf(flight) + "min.");
                }
            }
        }

    }

    public void addFlightId(String[] arguments){
        String id = arguments[1];
        String from = arguments[2];
        String to = arguments[3];
        String date = arguments[4];
        String duration = arguments[5];


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatnou = localDate.format(dateTimeFormatter1);

        if(currentUser == null){
            writerManager.write(noConnectedUser());
            return;
        }


        Optional<Flight> id1 = allFlights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();

        Optional<Flight> id3 = currentUser.getUserFlights().stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();

        if(!id1.isPresent()) {
            writerManager.write(flightDoesNotExist(id));
        } else if(id3.isPresent()){
            writerManager.write(alreadyHaveTicket(currentUser.getEmail(),id));
        } else {
            writerManager.write(flightAddedForUser(id,currentUser.getEmail()));
            currentUser.addFlight(new Flight(id, from, to, String.valueOf(formatnou), duration));
        }

    }

    public void cancelFlight(String[] arguments){
        String id = arguments[1];
        String from = arguments[2];
        String to = arguments[3];
        String date = arguments[4];
        String duration = arguments[5];

        Optional<Flight> id1 = allFlights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();

        Optional<Flight> id3 = currentUser.getUserFlights().stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();

        if(currentUser == null){
           writerManager.write(noConnectedUser());
        } else if(!id1.isPresent()) {
            writerManager.write(flightDoesNotExist(id));
        } else if(currentUser!=null && !id3.isPresent()){
            writerManager.write(notHaveTicketForThisFlight(currentUser.getEmail(),id));
        } else {
            writerManager.write(canceledHisTicketFlight(currentUser.getEmail(),id));
            currentUser.deleteFlight(new Flight(id, from, to, date, duration));
        }

    }

    public void deleteFlight(String[] arguments) {
        String id = arguments[1];

        Optional<Flight> optionalFlight = allFlights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();
        if(optionalFlight.isEmpty()){
            writerManager.write(flightDoesNotExist(id));
            return;
        } else {
            allFlights.remove(optionalFlight.get());
            writerManager.write(flightIdDeleted(id));
        }

        Flight flight = optionalFlight.get();
        for( User user: allUser){
            if(user.getUserFlights().contains(flight));
            user.deleteFlight(flight);
            writerManager.write(userNotifiedCanceledFlight(user.getEmail(),flight.getId()));
        }

    }


    public void addFlightDetails(String[] arguments) {
        String id = arguments[1];
        String from = arguments[2];
        String to = arguments[3];
        String date = arguments[4];
        String duration = arguments[5];

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatnou = localDate.format(dateTimeFormatter1);


        Flight flightnew = new Flight(id, from, to, String.valueOf(formatnou), duration);
        Optional<Flight> optionalFlight = allFlights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst();

        if(!optionalFlight.isPresent()){
            writerManager.write(flightAdded(flightnew.getFrom(),flightnew.getTo(),flightnew.getDate(),flightnew.getDuration()));
            allFlights.add(flightnew);
        } else {
            writerManager.write(cannotAddFlight(flightnew.getId()));
        }

    }

    public void displayFlights(String[] arguments) {

        if(!allFlights.isEmpty()){
            for (Flight flight : allFlights){
                writerManager.write(String.valueOf(flight) + "min.");
            }
        }

    }

    public void persistFlights(String[] arguments){

        if(!allFlights.isEmpty()){
            try(Connection connection = DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
                    Statement statement = connection.createStatement()) {
                for(Flight flight: allFlights){
                    String insertFlights = "INSERT INTO flights VALUES ('"+flight.getId()+"','"+flight.getFrom()+"','"+flight.getTo()+"','"+flight.getDate()+"','"+flight.getDuration()+"')";
                    statement.execute(insertFlights);
                }
                } catch (SQLException throwables){
                    throwables.printStackTrace();
                }
            writerManager.write(flightsAddInDataBase(String.valueOf(LocalDateTime.now())));
        }

    }

    public void persistUsers(String[] arguments){

        if(!allUser.isEmpty()){
            try(Connection connection = DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
                Statement statement = connection.createStatement()) {
                for(User user: allUser){
                    String insertUsers = "INSERT INTO users VALUES ('"+user.getId()+"', '"+user.getEmail()+"', '"+user.getName()+"', '"+user.getParola()+"')";
                    statement.execute(insertUsers);
                }
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
            writerManager.write(usersAddInDataBase(String.valueOf(LocalDateTime.now())));
        }

    }

    public void defaultCommand(String[] arguments){
        writerManager.write(commandIsNotImplemented());
    }

    public WriterManager getWriterManager() {
        return writerManager;
    }
}
