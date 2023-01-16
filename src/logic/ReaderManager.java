package logic;

import data.FileInfo;

import java.io.*;

public class ReaderManager {

    private final BufferedReader bufferedReader;

    public ReaderManager() {
        bufferedReader = FileInfo.createReader();
    }

        public String readLine() {
            try {
               return bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
                //de facut o exceptie sa ne dam seama ce se intampla
            }
        }

}
