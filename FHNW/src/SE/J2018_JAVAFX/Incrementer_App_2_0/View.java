package SE.J2018_JAVAFX.Incrementer_App_2_0;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * Created by Frenk
 */



public class View {
    private Model model;
    protected Stage window;
    protected Scene scene1;
    protected Scene scene2;
    protected BorderPane root = new BorderPane();
    protected Label contacts = new Label("0");
    protected HBox leftSide;
    protected HBox rightSide;
    protected VBox contactsBox;
    protected VBox buttonBox;
    protected ToggleButton incrementerButton;
    protected VBox textBox;
    protected TextArea notes;
    protected Label notesLabel;
    protected VBox middle;




    protected View(Stage stage, Model model){
        this.window = stage;
        this.model = model;

        //Left and Right side of BorderPane
        leftSide = new HBox();
        leftSide.setStyle("-fx-background-color:#964848;");
        leftSide.setPrefHeight(800);
        leftSide.setPrefWidth(75);
        rightSide = new HBox();
        rightSide.setStyle("-fx-background-color:#964848;");
        rightSide.setPrefHeight(800);
        rightSide.setPrefWidth(75);

        //Contact Box
        contactsBox = new VBox();
        contactsBox.setAlignment(Pos.CENTER);
        contactsBox.setPrefHeight(200.0);
        contactsBox.setPadding(new Insets(10, 50, 50, 50));
        contactsBox.setStyle("-fx-background-color:#964848;");
        contacts.setFont(Font.font("Amble CN", FontWeight.BOLD, 150));
        contacts.setTextFill(Color.DARKRED);
        contactsBox.getChildren().add(contacts);

        //Incrementer Box
        buttonBox = new VBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPrefHeight(100.0);
        buttonBox.setStyle("-fx-background-color: #964848;");
        incrementerButton = new ToggleButton("Note progress");
        incrementerButton.setFont(Font.font("Amble CN", FontWeight.EXTRA_BOLD, 22));
        incrementerButton.setStyle("-fx-border-color: #7a1111; -fx-border-width: 5.0; -fx-text-fill: #8e0e0e; " +
                "-fx-border-radius: 30; -fx-background-radius: 35;");
        incrementerButton.setPrefSize(300, 100);
        incrementerButton.setTranslateY(-40);
        buttonBox.getChildren().add(incrementerButton);

        //Textarea box
        textBox = new VBox();
        textBox.setAlignment(Pos.CENTER);
        textBox.setPrefHeight(250.0);
        textBox.setStyle("-fx-background-color: #964848;");
        notes = new TextArea();
        notes.setPrefSize(100, 150);
        notes.setTranslateY(-50);
        notes.setFont(Font.font("Amble CN", FontWeight.THIN, 18));
        notesLabel = new Label("Please take your notes");
        notesLabel.setFont(Font.font("Amble CN", FontWeight.THIN, 20));
        notesLabel.setTranslateY(-60);
        notesLabel.setTranslateX(-24);
        textBox.getChildren().add(notesLabel);
        textBox.getChildren().add(notes);


        //Creating middle
        middle = new VBox(contactsBox, buttonBox, textBox);
        root.setCenter(middle);
        root.setRight(rightSide);
        root.setLeft(leftSide);
        scene1 = new Scene(root, 400, 600);
        window.setScene(scene1);
        window.setTitle("Testing MVC...");


    }

    public void start(){
        window.show();
    }

    public void stop(){
        window.hide();
    }

    public Stage getStage(){
        return window;
    }

}
