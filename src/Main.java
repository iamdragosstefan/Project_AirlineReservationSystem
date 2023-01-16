import constants.Commands;
import logic.AirLineManager;
import logic.ReaderManager;
import logic.WriterManager;

import java.io.*;

import static constants.Commands.LOGIN;
import static constants.Commands.SIGNUP;

public class Main {

    public static void main(String[] args) {

        AirLineManager airLineManager = new AirLineManager();
        ReaderManager readerManager = new ReaderManager();

            String line = readerManager.readLine();

            while (line != null){

                String[] arguments = line.split(" ");
                Commands command;
                try{
                    command = Commands.valueOf(arguments[0]);
                } catch (IllegalArgumentException e){
                    command = Commands.DEFAULT_COMMAND;
                }

                switch (command) {
                    case SIGNUP: {
                        airLineManager.signUp(arguments);
                        break;
                    }
                    case LOGIN: {
                        airLineManager.login(arguments);
                        break;
                    }
                    case LOGOUT: {
                        airLineManager.logout(arguments);
                        break;
                    }
                    case DISPLAY_MY_FLIGHTS: {
                        airLineManager.displayMyFlights(arguments);
                        break;
                    }
                    case ADD_FLIGHT: {
                        airLineManager.addFlightId(arguments);
                        break;
                    }
                    case CANCEL_FLIGHT: {
                        airLineManager.cancelFlight(arguments);
                        break;
                    }
                    case ADD_FLIGHT_DETAILS: {
                        airLineManager.addFlightDetails(arguments);
                        break;
                    }
                    case DELETE_FLIGHT: {
                        airLineManager.deleteFlight(arguments);
                        break;
                    }
                    case DISPLAY_FLIGHTS: {
                        airLineManager.displayFlights(arguments);
                        break;
                    }
                    case PERSIST_FLIGHTS: {
                        airLineManager.persistFlights(arguments);
                        break;
                    }
                    case PERSIST_USERS: {
                        airLineManager.persistUsers(arguments);
                        break;
                    }
                    case DEFAULT_COMMAND: {
                        airLineManager.defaultCommand(arguments);
                        break;
                    }

                }

                line = readerManager.readLine();
            }
        airLineManager.getWriterManager().flush();
    }
}
