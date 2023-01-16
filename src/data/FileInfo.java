package data;

import java.io.*;

public class FileInfo {
    public static BufferedReader createReader() {

        File input = new File("resources/input.txt");
        try {
            return new BufferedReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static BufferedWriter createWriter(){
        File outup = new File("resources/output.txt");
            try {
                return new BufferedWriter(new FileWriter(outup));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
