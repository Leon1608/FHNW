package SE.J2018_FileIO;

import SE.J2018_NanoTimer.NanoTimer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Powerknowledge
 */

public class FileIO_1_Scanner_FileReader {
    public static void main(String[] args) throws IOException {
        NanoTimer timer = new NanoTimer();
        timer.startTimer();


        // Reads only first character
        try (FileReader fileReader = new FileReader("data.txt")) {
            //(char) transfer ascii code to characters from data.txt
            System.out.println((char) fileReader.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();


        // Another possibility to read
        try (Scanner fileReader2 = new Scanner(new FileReader("data.txt"))) {
            //(char) transfer ascii code to characters from data.txt
                System.out.println(fileReader2.nextLine());
            }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(timer.stopTimer());
        System.out.println(timer.convertToSeconds(timer.stopTimer()));



        }
    }
