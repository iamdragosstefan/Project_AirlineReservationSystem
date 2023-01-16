package logic;

import data.FileInfo;

import java.io.*;

public class WriterManager {

    private final BufferedWriter bufferedWriter;

    public WriterManager() {
        bufferedWriter = FileInfo.createWriter();
    }

    public void write(String str) {
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush(){
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}
