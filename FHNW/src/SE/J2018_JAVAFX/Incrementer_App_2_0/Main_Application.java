package SE.J2018_JAVAFX.Incrementer_App_2_0;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Frenk
 */

public class Main_Application extends Application {
    private View view;
    private Controller controller;
    private Model model;


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage){
        model = new Model();
        view = new View(primaryStage, model);
        controller = new Controller(model, view);
        view.start();

    }

    public void stop(){
        if(view != null){
            view.stop();
        }
    }
}
