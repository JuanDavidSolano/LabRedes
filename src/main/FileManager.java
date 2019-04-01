package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
    El fileManager se encarga de la Lectura y Escritura de cualquier archivo
    que sea requerido.
*/

public class FileManager {

    ArrayList<String> files = new ArrayList();
    
    // Get all created files
    public ArrayList<String> getFiles() {
        return files;
    }
    
    // Create File
    public void createFile(String name, String extension, ArrayList<String> data, boolean erase) throws IOException {
        if (extension == null) {
            extension = ".txt";
        }
        // Create a file
        File f;
        f = new File(name + extension);

        // Create necesary objects
        FileWriter fr = new FileWriter(f, erase); // True value means dont will erase file's previous content
        BufferedWriter bw = new BufferedWriter(fr);
        PrintWriter pw = new PrintWriter(bw);

        // Write data on the file
        for (String dato : data) {
            pw.print(dato);
            bw.newLine();
        }
        files.add(name+extension);
        // Close
        pw.close();
        bw.close();
        fr.close();
    }

    // Read file
    public static ArrayList readFile(String name, String extension) throws FileNotFoundException, IOException {
        ArrayList<String> data = new ArrayList();
        if (extension == null) {
            extension = ".txt";
        }
        // Create a file
        File f;
        f = new File(name + extension);

        // Create necesary objects
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // In 'line' we save a file's line and write in console
        String line;
        while (br.ready()) {
            line = br.readLine();
            data.add(line);
        }

        // Close all
        br.close();
        fr.close();
        return data;
    }

}
