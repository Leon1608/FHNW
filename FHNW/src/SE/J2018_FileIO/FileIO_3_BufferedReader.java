package SE.J2018_FileIO;

import SE.J2018_NanoTimer.NanoTimer;

import java.io.*;

/**
 * Created by Powerknowledge
 */

public class FileIO_3_BufferedReader {
    public static void main(String[] args) throws IOException{
        NanoTimer timer = new NanoTimer();
        timer.startTimer();

        try{
            File file = new File("Scientific.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while (bufferedReader.readLine() != null){
                data = bufferedReader.readLine();
                System.out.println(data);
            }
        } catch (IOException e){
            System.out.println("Bad code man!");
        }
        long nanoTime = timer.stopTimer();
        System.out.println(nanoTime);

    }
}
