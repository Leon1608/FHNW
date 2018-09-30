package SE.J2018_FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Powerknowledge
 */

public class FileIO_5_Read_Write_Prog {

        public static void main(String[] args) throws IOException {

            Scanner scan = new Scanner(System.in);
            SE.J2018_FileIO.FileManager fileManager = new SE.J2018_FileIO.FileManager();

            fileManager.getFiles();
            ArrayList<String> fileLines = fileManager.splitIntoLines();

            if (fileLines.size() == 0) {
                System.out.println("Program finished.");
                return;
            }

            System.out.println("What line would you like to Read? 1-" + fileLines.size() );

            while(!scan.hasNextInt()) {
                scan.next();
                System.out.print("Not a valid line number. Try again: ");
            }
            int line = scan.nextInt();
            if (line > 0 && line <= fileLines.size()) {
                fileManager.writeTo("line-" + line + ".txt", fileLines.get(line - 1) );
            }
            else {
                System.out.println("Line doesn't exist.");
            }
        }
        //
    }
    /**
     * Read, Write, and List files
     */
    class FileManager {
        //
        private final static Logger logr = SE.J2018_FileIO.Logr.setup( SE.J2018_FileIO.FileManager.class.getName() );
        //
        ArrayList<String> splitIntoLines() throws IOException {

            Scanner scan = new Scanner(System.in);
            ArrayList<String> lines = new ArrayList<String>();

            String filePath = scan.nextLine();
            if (filePath.equals("q")) {
                return lines;
            }
            else if (filePath.equals("a")) {
                getFiles();
            }
            String nextLine = null;
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                try {
                    while ((nextLine = br.readLine()) != null) {
                        lines.add(nextLine);
                    }
                }
                catch(IOException e) {
                    System.out.print("Error reading file. Try another ('q' to quit, 'a' for available).\n: ");
                    lines = splitIntoLines();
                }
            }
            catch(FileNotFoundException e) {
                System.out.print("File doesn't exist. Try again ('q' to quit, 'a' for available).\n: ");
                lines = splitIntoLines();
            }
            catch(IOException e) {
                System.out.println("Error closing the file. Program shutting down.");
                logr.log(Level.SEVERE, "BufferedReader close error.", e);
                throw e;
            }
            return lines;
        }
        //
        void getFiles() {
            File[] files = new File(".").listFiles();
            System.out.print("What file do you want to read? ('q' to quit). [ " );

            for (File file : files) {
                if (
                        file.isFile() &&
                                !(file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("class")) //ignores files with .class extension
                        ) {
                    System.out.printf("'%s' ", file.getName());
                }
            }
            System.out.print("]\n: ");
        }
        //
        void writeTo(String fileName, String text) {
            try (FileWriter fwriter = new FileWriter(fileName)) {
                fwriter.write(text);
            }
            catch(IOException e) {
                logr.log(Level.SEVERE, "Error writing to file.", e);
            }
        }
        //
    }
    /**
     * Log data out
     */
    class Logr {
        //
        public static Logger setup(String name) {
            Logger logr = Logger.getLogger(name);
            logr.setUseParentHandlers(false);
            logr.setLevel(Level.ALL);

            try {
                FileHandler fh = new FileHandler( name + ".log" );
                fh.setLevel(Level.INFO);
                logr.addHandler(fh);
            }
            catch(IOException e) {
                ConsoleHandler ch = new ConsoleHandler();
                ch.setLevel(Level.INFO);
                logr.addHandler(ch);
                logr.info("Couldn't setup file logger.");
            }

            return logr;
        }
        //
    }

