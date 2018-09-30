package SE.J2018_NanoTimer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Powerknowledge
 */

public class NanoTimer {
    public long elapsed;

    public void startTimer(){
        elapsed = System.nanoTime();
    }

    public long stopTimer(){
        return (System.nanoTime()-elapsed);
    }

    public double convertToSeconds(long nanoTime){
        return TimeUnit.SECONDS.convert(nanoTime, TimeUnit.NANOSECONDS);

    }
}
