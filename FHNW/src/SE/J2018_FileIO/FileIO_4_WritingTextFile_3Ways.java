package SE.J2018_FileIO;

import SE.J2018_NanoTimer.NanoTimer;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Powerknowledge
 */

public class FileIO_4_WritingTextFile_3Ways {
    public static void main(String[] args) throws IOException {
        NanoTimer timer = new NanoTimer();
        timer.startTimer();

        try (FileWriter fileWriter = new FileWriter("myFile1.txt")){
            fileWriter.write("Hello world! This file was written by Filewriter");
        }

        try (PrintWriter printWriter = new PrintWriter("myFile2.txt")){
            for(int i=0; i<200; i++){
                printWriter.print("Hello world again! This file was written by Printwriter");
            }

        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("myFile3.txt"))){
            for (int i=0; i<=105; i++){
                bufferedWriter.write(i + ". Process complete\n");
            }
        }

        long nanoTime = timer.stopTimer();
        long milliSec = TimeUnit.NANOSECONDS.toMillis(nanoTime);
        System.out.println(milliSec);



    }
}
