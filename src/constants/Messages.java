package constants;

import java.time.LocalDateTime;

public class Messages {


    public static String cannotAddUserPasswordDiff(){
        return "Cannot add user! The password are different!";
    }
    public static String passwordLenghtShort(){
        return "Cannot add user! The password to short!";
    }
    public static String userAlreadyExists(){
        return "User already exists!";
    }
    public static String userSuccessfullyAdded(String email){
        return "User with email: " + email + " was successfully added!";
    }


    public static String cannotFindUserWithEmail(String email){
        return "Cannot find user with email: " + email;
    }
    public static String incorrectPassword(){
        return "Incorrect password!";
    }
    public static String anotherUserIsConnected(){
        return "Another user is already connected!";
    }
    public static String currentUser(String email){
        return "User with email " + email + " is the current user started from " + LocalDateTime.now();
    }


    public static String userDisconnected(String email){
        return "User with email " + email + " successfully disconnected at " + LocalDateTime.now();
    }
    public static String userWasNotConnected(String email){
        return "The user with email " + email + " was not connected!";
    }
    public static String noConnectedUser(){
        return "There is no connected user!";
    }
    public static String noFlightBooked(String email){
        return "The user with email " + email + " has no flight booked!";
    }


    public static String flightDoesNotExist(String id){
        return "The flight with id " + id +  " does not exist!";
    }
    public static String alreadyHaveTicket(String email, String id){
        return "The user with email " + email + " already have a ticket for flight with id " + id + " !";
    }
    public static String flightAddedForUser(String id, String email){
        return "The flight with id " + id + " was successfully added for user with email " + email + " !";
    }


    public static String notHaveTicketForThisFlight(String email, String id){
        return "The user with email " + email + " does not have a ticket for the flight with id " + id + " !";
    }
    public static String canceledHisTicketFlight(String email, String id){
        return "The user with email " + email + " has successfully canceled his ticket for flight with id " + id + " !";
    }


    public static String flightIdDeleted(String id){
        return "Flight with id " + id + " successfully deleted";
    }
    public static String userNotifiedCanceledFlight(String email, String id){
        return "The user with email " + email + " was notified that the flight with id " + id + " canceled!";
    }


    public static String flightAdded(String from, String to, String date, String duration){
        return "Flight from " + from + ", to " + to + ", date " + date + ", duration " + duration + "min, successfully added!";
    }
    public static String cannotAddFlight(String id){
        return "Cannot add flight! There is already a flight with id " + id;
    }


    public static String flightsAddInDataBase(String date){
        return "The flights was successfully saved in the database at " + date;
    }
    public static String usersAddInDataBase(String date){
        return "The users was successfully saved in the database at " + date;
    }

    public static String commandIsNotImplemented(){
        return "This command is not implemented!";
    }
}
