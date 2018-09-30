package SE.J2018_JAVAFX.Incrementer_App_2_0;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by Frenk
 */

public class Controller {
    final private Model model;
    final private View view;


    protected Controller(Model model, View view){
        this.model = model;
        this.view = view;


        view.incrementerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.contacts.setText(Integer.toString(model.incrementValue()));

            }
        });





        view.getStage().setOnCloseRequest(e->{
            view.stop();
            Platform.exit();
        });
    }
}
