package SE.J2018_JAVAFX.Application_Database;
/**
 * Created by christopher on 11/26/15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentDatabase extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StudentDatabaseFXML.fxml"));
        primaryStage.setTitle("Student Database");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
