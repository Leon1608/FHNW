package SE.J2018_JAVAFX.Incrementer_App_2_0;

/**
 * Created by Frenk
 */


//Business logic
public class Model {
    protected int notes;

    protected Model(){
        notes = 0;
    }


    public int getValue(){
        return notes;
    }



    // Incrementing value
    public int incrementValue(){
        notes++;
        return notes;
    }
}
