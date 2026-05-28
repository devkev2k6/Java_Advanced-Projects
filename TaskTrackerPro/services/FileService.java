package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {

    public void save(String data) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/tasks.txt", true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("File error");
        }
    }
}
