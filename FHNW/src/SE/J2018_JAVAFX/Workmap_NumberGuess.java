package SE.J2018_JAVAFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Workmap_NumberGuess extends Application {

    private static double lifeReduction = 0.15;
    private static double progressAddition = 0.25;

    private Integer guess = 0;
    private static Integer limit = 100;
    static Integer number = (int) (Math.random()*limit);
    static Integer gatheredNum;

    Label numberlbl = new Label("?");
    Label enterYourGuess = new Label("Please enter your guess");
    static Label infoField = new Label();

    static TextField textField = new TextField();

    static double successProg = 0;
    static ProgressBar lifeBar = new ProgressBar();
    static ProgressBar progressBar = new ProgressBar();
    static double lifeStatus = 1.0;
    int width = 400;
    int height = 700;
    static Stage window;
    Scene scene1;
    Scene scene2;

    static MediaPlayer mediaPlayer;
    static Media failToneMedia;
    static Media winToneMedia;
    static Media roundToneMedia;
    static Media gameLostMedia;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        BorderPane root = new BorderPane();


        //Preparing sources of audio support
        File failFile = new File("F:\\Directory\\Java_Projects\\FHNW\\Files\\Ouch.mp3");
        File winToneFile = new File("F:\\Directory\\Java_Projects\\FHNW\\Files\\Win.mp3");
        File roundToneFile = new File("F:\\Directory\\Java_Projects\\FHNW\\Files\\Dixie.mp3");
        File gameLostFile = new File("F:\\Directory\\Java_Projects\\FHNW\\Files\\Gamelost.mp3");
             
        //Implementing sources of the audio files
        failToneMedia = new Media(failFile.toURI().toString());
        winToneMedia = new Media(winToneFile.toURI().toString());
        roundToneMedia = new Media(roundToneFile.toURI().toString());
        gameLostMedia = new Media(gameLostFile.toURI().toString());

        //FX Creating Lighting
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0f);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0f);

        //FX Creating Shadow
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(1.0);
        shadow.setOffsetY(-5.0);
        shadow.setColor(Color.GRAY);
        shadow.setInput(lighting);

        //Create left and right side of borderpane
        HBox leftSide = new HBox();
        leftSide.setPrefHeight(height);
        leftSide.setPrefWidth(50);      
        HBox rightSide = new HBox();
        rightSide.setPrefHeight(height);
        rightSide.setPrefWidth(50);

        //Creating top section (Progressbar)
        VBox topBox = new VBox();
        topBox.setSpacing(15.0);
        topBox.setPrefSize(width, 150);

        Label lifelbl = new Label("LIFE:");
        lifelbl.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 25));
        lifelbl.setTranslateY(53);
        lifelbl.setTranslateX(50);
        lifeBar.getStyleClass().add("lifeBar");
        lifeBar.setProgress(lifeStatus);
        lifeBar.setTranslateY(-34);
        lifeBar.setTranslateX(190);
        lifeBar.setPrefSize(155, 100);
        lifeBar.progressProperty().addListener(new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> property, Number oldValue, Number newValue) {
        		System.out.println("Life left: " + newValue);
        	}
        });


        Label success = new Label("PROGRESS:");
        success.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 25));
        success.setTranslateY(53);
        success.setTranslateX(50);

        progressBar.setTranslateY(-25);
        progressBar.setTranslateX(190);
        progressBar.getStyleClass().add("progressBar");
        progressBar.setPrefSize(155, 100);
        progressBar.setProgress(-0.1);
        progressBar.progressProperty().addListener(new ChangeListener<Number>(){
        	@Override
        	public void changed(ObservableValue<? extends Number> property, Number oldvalue, Number newValue) {
        		System.out.println("Progress sofar: " + newValue);
        	}
        });
        topBox.getChildren().add(lifelbl);
        topBox.getChildren().add(success);
        topBox.getChildren().add(lifeBar);
        topBox.getChildren().add(progressBar);


        //Creating middle section with Guess
        VBox middleBox = new VBox();
        middleBox.setAlignment(Pos.CENTER);
        middleBox.setPrefHeight(100.0);
        middleBox.getStyleClass().add("middleBox");
        numberlbl.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 150));
        numberlbl.setEffect(shadow);
        numberlbl.textProperty().bind(textField.textProperty());
        infoField.setFont(Font.font("Courier New", FontWeight.LIGHT, 18));

        middleBox.getChildren().add(numberlbl);
        middleBox.getChildren().add(infoField);


        //Creating bottom section
        VBox bottomBox = new VBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getStyleClass().add("bottomBox");
        bottomBox.setPrefHeight(300.0);
        enterYourGuess.setFont(Font.font("Courier New", FontWeight.BOLD, 25));
        enterYourGuess.setTranslateY(-50.0);
        textField.setMinHeight(50);
        textField.setMaxHeight(50);
        textField.setMinWidth(80);
        textField.setMaxWidth(80);
        textField.setTranslateY(-20);
        textField.setPromptText("?");
        textField.setFont(Font.font("Courier New", FontPosture.REGULAR, 25));
        ToggleButton buttonSubmit = new ToggleButton("Submit");
        buttonSubmit.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 40));
        buttonSubmit.getStyleClass().add("buttonSubmit");
        buttonSubmit.setTranslateY(20);
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
        	public void handle(ActionEvent event) {
                if(textField.getText() != null && !textField.getText().isEmpty()){
                    String gathered = textField.getText();
                    gatheredNum = Integer.parseInt(gathered);
                    if (gatheredNum == number){
                        if(successProg > 0){
                            mediaPlayer = new MediaPlayer(roundToneMedia);
                            mediaPlayer.setVolume(0.1);
                            mediaPlayer.play();
                            successProg += progressAddition;
                            progressBar.setProgress(successProg);
                            number = (int) (Math.random()*limit);
                            lifeStatus = 1.0;
                            lifeBar.setProgress(lifeStatus);
                            infoField.setText("VERY NICE KEEP GOING!");
                            infoField.setStyle("-fx-text-fill: #ffffff;");
                        } else {
                            mediaPlayer = new MediaPlayer(roundToneMedia);
                            mediaPlayer.setVolume(0.1);
                            mediaPlayer.play();
                            successProg += progressAddition;
                            progressBar.setProgress(successProg);
                            number = (int) (Math.random()*limit);
                            lifeStatus = 1.0;
                            lifeBar.setProgress(lifeStatus);
                            infoField.setText("VERY NICE KEEP GOING!");
                            infoField.setStyle("-fx-text-fill: #ffffff;");
                        }
                    }
                    else if(gatheredNum > number){
                        mediaPlayer = new MediaPlayer(failToneMedia);
                        mediaPlayer.setVolume(0.2);
                        mediaPlayer.play();
                        infoField.setText("Your number is too big");
                        infoField.setStyle("-fx-text-fill: #d8280d;");
                        lifeStatus -= lifeReduction;
                        lifeBar.setProgress(lifeStatus);
                    }

                    else if(gatheredNum < number){
                        mediaPlayer = new MediaPlayer(failToneMedia);
                        mediaPlayer.setVolume(0.2);
                        mediaPlayer.play();
                        infoField.setText("Your number is too small");
                        infoField.setStyle("-fx-text-fill: #098257;");
                        lifeStatus -= lifeReduction;
                        lifeBar.setProgress(lifeStatus);

                    }


                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No content");
                    alert.setHeaderText("\"Guess number\" is missing!");
                    alert.setContentText("Please enter your guess number and then press submit");
                    alert.showAndWait();

                }

                if(successProg >= 1.0){
                    Alert alertWon = new Alert(Alert.AlertType.CONFIRMATION);
                    alertWon.setTitle("Congratulation");
                    alertWon.setHeaderText("You've won");
                    alertWon.setContentText("We congratulate you in winning this game");
                    alertWon.show();
                    mediaPlayer = new MediaPlayer(winToneMedia);
                    mediaPlayer.setVolume(0.3);
                    mediaPlayer.play();
                    window.close();

                }

                if(lifeStatus <= 0) {
                    mediaPlayer = new MediaPlayer(gameLostMedia);
                    mediaPlayer.setVolume(0.2);
                    mediaPlayer.play();
                    Alert alertLost = new Alert(Alert.AlertType.CONFIRMATION);
                    alertLost.setTitle("Game over");
                    alertLost.setHeaderText("You've lost");
                    alertLost.show();
                    window.close();
                }
            }
        });

        bottomBox.getChildren().add(enterYourGuess);
        bottomBox.getChildren().add(textField);
        bottomBox.getChildren().add(buttonSubmit);



        root.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER){
                {
                    if(textField.getText() != null && !textField.getText().isEmpty()){
                        String gathered = textField.getText();
                        gatheredNum = Integer.parseInt(gathered);
                        if (gatheredNum == number){
                            if(successProg > 0){
                                mediaPlayer = new MediaPlayer(roundToneMedia);
                                mediaPlayer.setVolume(0.1);
                                mediaPlayer.play();
                                successProg += progressAddition;
                                progressBar.setProgress(successProg);
                                number = (int) (Math.random()*limit);
                                lifeStatus = 1.0;
                                lifeBar.setProgress(lifeStatus);
                                infoField.setText("VERY NICE KEEP GOING!");
                                infoField.setStyle("-fx-text-fill: #ffffff;");
                            } else {
                                mediaPlayer = new MediaPlayer(roundToneMedia);
                                mediaPlayer.setVolume(0.1);
                                mediaPlayer.play();
                                successProg += progressAddition;
                                progressBar.setProgress(successProg);
                                number = (int) (Math.random()*limit);
                                lifeStatus = 1.0;
                                lifeBar.setProgress(lifeStatus);
                                infoField.setText("VERY NICE KEEP GOING!");
                                infoField.setStyle("-fx-text-fill: #ffffff;");
                            }
                        }
                        else if(gatheredNum > number){
                            mediaPlayer = new MediaPlayer(failToneMedia);
                            mediaPlayer.setVolume(0.2);
                            mediaPlayer.play();
                            infoField.setText("Your number is too big");
                            infoField.setStyle("-fx-text-fill: #d8280d;");
                            lifeStatus -= lifeReduction;
                            lifeBar.setProgress(lifeStatus);
                        }

                        else if(gatheredNum < number){
                            mediaPlayer = new MediaPlayer(failToneMedia);
                            mediaPlayer.setVolume(0.2);
                            mediaPlayer.play();
                            infoField.setText("Your number is too small");
                            infoField.setStyle("-fx-text-fill: #098257;");
                            lifeStatus -= lifeReduction;
                            lifeBar.setProgress(lifeStatus);

                        }


                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("No content");
                        alert.setHeaderText("\"Guess number\" is missing!");
                        alert.setContentText("Please enter your guess number and then press submit");
                        alert.showAndWait();

                    }

                    if(successProg >= 1.0){
                        Alert alertWon = new Alert(Alert.AlertType.CONFIRMATION);
                        alertWon.setTitle("Congratulation");
                        alertWon.setHeaderText("You've won");
                        alertWon.setContentText("We congratulate you in winning this game");
                        alertWon.show();
                        mediaPlayer = new MediaPlayer(winToneMedia);
                        mediaPlayer.setVolume(0.3);
                        mediaPlayer.play();
                        window.close();

                    }

                    if(lifeStatus <= 0) {
                        mediaPlayer = new MediaPlayer(gameLostMedia);
                        mediaPlayer.setVolume(0.2);
                        mediaPlayer.play();
                        Alert alertLost = new Alert(Alert.AlertType.CONFIRMATION);
                        alertLost.setTitle("Game over");
                        alertLost.setHeaderText("You've lost");
                        alertLost.show();
                        window.close();
                    }
                }
            }
        });
        root.setLeft(leftSide);
        root.setRight(rightSide);
        root.setBottom(bottomBox);
        root.setCenter(middleBox);
        root.setTop(topBox);
        
        scene1 = new Scene(root, width, height);
        scene1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        window.setTitle("NumberGuess Game");
        window.setScene(scene1);
        window.show();
        Alert alertRules = new Alert(Alert.AlertType.INFORMATION);
        alertRules.setTitle("LET THE GAME BEGIN");
        alertRules.setHeaderText("FIND THE HIDDEN NUMBER, RANGE 0 - " + limit);
        alertRules.setContentText("Find the hidden numbers and win the game having 100% Progress. " +
                "WARNING! Everytime you make mistake, your life will be reducted. It gets fully recovered after you have found current number. We" +
                " wish you good luck :)");
        alertRules.showAndWait();

    }
}
