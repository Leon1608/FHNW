package SE.J2018_JAVAFX.J2018_Incrementer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;

public class Complete_08_2018 extends Application implements EventHandler<javafx.event.ActionEvent> {
    private Integer value = 0;
    Label contacts = new Label("0");
    Stage window;
    Scene scene1;
    Scene scene2;


    //Adding music mediaplayer
    MediaPlayer mediaPlayer;
    Media media;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        BorderPane root = new BorderPane();

        //Creating FX Lighting
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0f);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0f);

        //Creating FX Shadow
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(10.0);
        shadow.setOffsetY(7.0);
        shadow.setColor(Color.DARKRED);
        shadow.setInput(lighting);

        DropShadow shadow2 = new DropShadow();
        shadow2.setOffsetX(5.0);
        shadow2.setOffsetY(5.0);
        shadow2.setColor(Color.SNOW);


        //Creating Reflection
        Reflection reflection = new Reflection();

        //Left and Right side of BorderPane
        HBox leftSide = new HBox();
        leftSide.setStyle("-fx-background-color:#964848;");
        leftSide.setPrefHeight(800);
        leftSide.setPrefWidth(75);
        HBox rightSide = new HBox();
        rightSide.setStyle("-fx-background-color:#964848;");
        rightSide.setPrefHeight(800);
        rightSide.setPrefWidth(75);

        //Contact box
        VBox contactsBox = new VBox();
        contactsBox.setAlignment(Pos.CENTER);
        contactsBox.setPrefHeight(200.0);
        contactsBox.setPadding(new Insets(10, 50, 50, 50));
        contactsBox.setStyle("-fx-background-color:#964848;");
        
        /**/
        contacts.setFont(Font.font("Amble CN", FontWeight.BOLD, 150));
        contacts.setTextFill(Color.DARKRED);
        contacts.setEffect(shadow);
        contactsBox.getChildren().add(contacts);

        //Incrementer Box
        VBox buttonBox = new VBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPrefHeight(100.0);
        buttonBox.setStyle("-fx-background-color: #964848;");
        ToggleButton incrementerButton = new ToggleButton("Note Progress");
        incrementerButton.setFont(Font.font("Amble CN", FontWeight.EXTRA_BOLD, 22));
        incrementerButton.setStyle("-fx-border-color: #7a1111; -fx-border-width: 5.0; -fx-text-fill: #8e0e0e; " +
                "-fx-border-radius: 30; -fx-background-radius: 35;");
        incrementerButton.setPrefSize(300, 100);
        
        /******Controller***/
        incrementerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(scene2);
            }
        });
        incrementerButton.setTranslateY(-40);
        buttonBox.getChildren().add(incrementerButton);

        //Textarea box
        VBox textBox = new VBox();
        textBox.setAlignment(Pos.CENTER);
        textBox.setPrefHeight(250.0);
        textBox.setStyle("-fx-background-color: #964848;");
        TextArea notes = new TextArea();
        notes.setPrefSize(100, 150);
        notes.setTranslateY(-50);
        notes.setFont(Font.font("Amble CN", FontWeight.THIN, 18));
        Label notesLabel = new Label("Please take your notes:");
        notesLabel.setFont(Font.font("Amble CN", FontWeight.THIN, 20));
        notesLabel.setTranslateY(-60);
        notesLabel.setTranslateX(-24);
        textBox.getChildren().add(notesLabel);
        textBox.getChildren().add(notes);

        //Music box
        VBox musicBox = new VBox();
        musicBox.setAlignment(Pos.CENTER);
        musicBox.setPrefHeight(50.0);
        musicBox.setStyle("-fx-background-color: #964848;");
        URL resource = getClass().getResource("beaster.mp3");

        media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.05);


        //
        VBox middle = new VBox(contactsBox, buttonBox, musicBox, textBox);
        root.setCenter(middle);
        root.setRight(rightSide);
        root.setLeft(leftSide);
        scene1 = new Scene(root, 400, 600);


        //Creating scene 2 design
        BorderPane root2 = new BorderPane();

        //Textbox which "note was saved"
        VBox textBox2 = new VBox();
        textBox2.setAlignment(Pos.CENTER);
        textBox2.setPrefHeight(100.0);
        textBox2.setStyle("-fx-background-color:#964848;");
        Label notification = new Label("Save your note");
        notification.setFont(Font.font("Amble CN", FontWeight.BOLD, 28));
        notification.setTextFill(Color.SNOW);
        notification.setTranslateX(0);
        notification.setTranslateY(35);
        textBox2.getChildren().add(notification);


        //Save and Date box created
        HBox saveBox = new HBox();
        saveBox.setSpacing(15.0);
        saveBox.setStyle("-fx-background-color: #964848;");
        saveBox.setPrefHeight(150.00);
        DatePicker datePicker = new DatePicker();
        datePicker.setTranslateY(50.0);
        datePicker.setTranslateX(25.0);
        datePicker.setValue(LocalDate.now());
        datePicker.setShowWeekNumbers(true);
        datePicker.setPromptText("Choose date");


        Button saveButton = new Button("Save");
        saveButton.setTranslateX(25.0);
        saveButton.setTranslateY(40.0);
        saveButton.setPrefSize(70, 30);
        saveButton.setFont(Font.font("Amble CN", FontWeight.EXTRA_BOLD, 15));
        saveButton.setStyle("-fx-border-color: #7a1111; -fx-border-width: 5.0; -fx-text-fill: #8e0e0e; " +
                "-fx-border-radius: 15; -fx-background-radius: 20;");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(scene1);
                value++;
                contacts.setText(Integer.toString(value));
            }
        });
        saveBox.getChildren().addAll(datePicker, saveButton);

        // Empty space box
        VBox emptyBox = new VBox();
        emptyBox.setStyle("-fx-background-color: #964848;");
        emptyBox.setPrefHeight(50.0);

        root2.setTop(textBox2);
        root2.setCenter(saveBox);
        root2.setBottom(emptyBox);
        scene2 = new Scene(root2, 300, 300);


        window.setOnCloseRequest(event -> {
            if (value > -1) {
                Platform.exit();
            } else {
                event.consume();
            }
        });
        window.setTitle("Locmelis incrementer");
        window.setScene(scene1);
        window.show();




    }

    @Override
    public void handle(ActionEvent event) {

    }
}
