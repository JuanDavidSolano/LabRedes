package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    // Create File
    public void createFile(String name, String extension) throws IOException {
        if (extension == null) {
            extension = ".txt";
        }
        // Create a file
        File f;
        f = new File(name+extension);

        // Create necesary objects
        FileWriter fr = new FileWriter(f, true); // True value means dont will erase file's previous content
        BufferedWriter bw = new BufferedWriter(fr);
        PrintWriter pw = new PrintWriter(bw);

        // Write
        pw.print("Same ");
        pw.print("line!!!");
        bw.newLine();
        pw.print("Other Line!!!");
        pw.println("Hi there!");

        // Close
        pw.close();
        bw.close();
        fr.close();
    }

}
