package SE.J2018_FileIO;

import SE.J2018_NanoTimer.NanoTimer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Powerknowledge
 */

public class FileIO_2_BufferedReader {
    public static void main(String args[]) throws IOException {
        NanoTimer timer = new NanoTimer();
        timer.startTimer();

        try {
//make a 'file' object
            File file = new File("data.txt");
//Get data from this file using a file reader.
            FileReader fr = new FileReader(file);
//To store the contents read via File Reader
            BufferedReader br = new BufferedReader(fr);
//Read br and store a line in 'data', print data
            String data;
            while (br.readLine() != null) {
                data = br.readLine();
                System.out.println(data);
            }
        } catch (IOException e) {
            System.out.println("bad !");
        }
        long nanoTime = timer.stopTimer();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(nanoTime));


    }

}

