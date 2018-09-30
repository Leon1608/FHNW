package SE.J2018_JAVAFX.J2018_Interface_Introduction;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by Powerknowledge
 */

public class J2018_Interface extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();

        Button fighter1 = new Button("Fighter 1");
        Button fighter2 = new Button("Fighter 2");
        Button fighter3 = new Button("Fighter 3");
        Button fighter4 = new Button("Fighter 4");
        Button fighter5 = new Button("Fighter 5");
        Button fighter6 = new Button("Fighter 6");
        VBox leftBox = new VBox(fighter1, fighter2, fighter3, fighter4, fighter5, fighter6);
        leftBox.setSpacing(15); //Set spacing between each button
        leftBox.setPadding(new Insets(15)); //Space within buttons
        leftBox.setAlignment(Pos.CENTER);
        leftBox.setPrefHeight(105.0);
        leftBox.setPrefWidth(150.0);
        leftBox.setStyle("-fx-border-color: black;");

        TextField surname = new TextField();
        TextField lastName = new TextField();
        HBox topBox = new HBox(surname, lastName);
        topBox.setSpacing(10.0);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_CENTER);



        Button fighter7 = new Button("Fighter 7");
        Button fighter8 = new Button("Fighter 8");
        Button fighter9 = new Button("Fighter 9");
        Button fighter10 = new Button("Fighter 10");
        Button fighter11 = new Button("Fighter 11");
        Button fighter12 = new Button("Fighter 12");
        VBox rightBox = new VBox(fighter7, fighter8, fighter9, fighter10, fighter11, fighter12);
        rightBox.setSpacing(15); //Set spacing between each button
        rightBox.setPadding(new Insets(15)); //Space within buttons
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setPrefHeight(105.0);
        rightBox.setPrefWidth(150.0);
        rightBox.setStyle("-fx-border-color: black;");


        Circle oval = new Circle();
        oval.setRadius(200.0);
        oval.setFill(Color.ALICEBLUE);
        StackPane middleBox = new StackPane();
        middleBox.getChildren().add(oval);

        root.setTop(topBox);
        root.setLeft(leftBox);
        root.setRight(rightBox);
        root.setCenter(middleBox);

        Scene scene = new Scene(root, 850, 500);
        primaryStage.setTitle("Arena");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
