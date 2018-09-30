package SE.J2018_JAVAFX.Application_StoringDifferentObjects;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by Frenk
 */

public class Workmap_Application extends Application {

    double screenWidth = 500.0;
    double screenHeight = 700.0;
    String gathering;

    BorderPane root = new BorderPane();
    VBox boxButtonText = new VBox();
    Scene scena = new Scene(root, screenWidth, screenHeight);

    Ellipse ellipse = new Ellipse();
    TextField textField = new TextField();
    ToggleButton button = new ToggleButton("Submit");
    Text text = new Text();

    ListView<String> listView = new ListView<>();
    
    
    ArrayList<String> texts = new ArrayList<>();
    ObservableList<String> listItems;


    Font frenkStyle_Write = Font.font("Courier New", FontWeight.EXTRA_BOLD, 30);
    Font frenkStyle_Read = Font.font("Courier New", FontWeight.BOLD, 20);

    ScaleTransition scaleTransition_01 = new ScaleTransition();
    ScaleTransition scaleTransition_02 = new ScaleTransition();
    ScaleTransition scaleTransition_03 = new ScaleTransition();
    ScaleTransition scaleTransition_04 = new ScaleTransition();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;

        //Creating blank leftBox
        HBox leftSide = new HBox();
        leftSide.setPrefHeight(screenHeight);
        leftSide.setPrefWidth(20);
        leftSide.setStyle("-fx-background-color: #2f4563;");
        //Creating blank rightBox
        HBox rightSide = new HBox();
        rightSide.setPrefHeight(screenHeight);
        rightSide.setPrefWidth(20);
        rightSide.setStyle("-fx-background-color: #2f4563;");

        //Creating blank topBox
        VBox topBox = new VBox();
        topBox.setPrefHeight(20);
        topBox.setPrefWidth(screenWidth);
        topBox.setStyle("-fx-background-color: #2f4563;");
        //Creating blank bottomBox
        VBox bottomBox = new VBox();
        bottomBox.setPrefHeight(20);
        bottomBox.setPrefWidth(screenWidth);
        bottomBox.setStyle("-fx-background-color: #2f4563;");


        //Creating huge center Part
        // Stackpane for ellipse and button + textfield
        VBox center = new VBox();
        StackPane firstPartofCenter = new StackPane();
        ellipse.setFill(Color.rgb(232, 175, 102));
        ellipse.setStroke(Color.rgb(44, 70, 140));
        ellipse.setStrokeWidth(5.0);
        ellipse.setRadiusX(200);
        ellipse.setRadiusY(175);
        ellipse.setTranslateY(20);
        firstPartofCenter.getChildren().add(ellipse);
        boxButtonText.setTranslateY(140);
        boxButtonText.setTranslateX(130);
        textField.setMinHeight(60);
        textField.setMaxHeight(60);
        textField.setMinWidth(200);
        textField.setMaxWidth(200);
        textField.setFont(frenkStyle_Write);
        textField.setStyle("-fx-background-color: #18a060; -fx-text-fill: #ffffff;");
        button.setMinHeight(50);
        button.setMaxHeight(50);
        button.setMinWidth(125);
        button.setMaxWidth(125);
        button.setTranslateX(40);
        button.setTranslateY(20);
        button.setFont(frenkStyle_Read);
        button.setStyle("-fx-background-color: #9b0707; -fx-border-color: #ffffff; -fx-border-width: 2.0; -fx-text-fill: #ffffff; " +
                "-fx-border-radius: 10; -fx-background-radius: 13;");
        button.setOnAction(e->{
            if (textField.getText() != null && !textField.getText().isEmpty()){
                gathering = textField.getText();
                listItems.add(gathering); //Listitems = observable
                textField.setText("");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NO INPUT");
                alert.setHeaderText("Please type in a note");
                alert.setContentText("To save an empty note is not necessary, please type in a note");
                alert.showAndWait();
            }
        });

        boxButtonText.getChildren().add(textField);
        boxButtonText.getChildren().add(button);
        firstPartofCenter.getChildren().add(boxButtonText);


        //Text and ListView
        VBox secondPartofCenter = new VBox();
        Text savedInformationText = new Text();
        savedInformationText.setFont(frenkStyle_Read);
        savedInformationText.setText("List of Notes: ");
        savedInformationText.setFill(Color.rgb(255, 255, 255));
        savedInformationText.setTranslateX(86);
        savedInformationText.setTranslateY(40);

        //texts ArrayList of Strings, ListItems observable
        listItems = FXCollections.observableArrayList(texts); //Responsible for observation of listItems
        double listH = 210;
        double listW = 300;
        listView.setMinWidth(listW);
        listView.setMaxWidth(listW);
        listView.setMinHeight(listH);
        listView.setMaxHeight(listH);
        listView.setTranslateX(85);
        listView.setTranslateY(50);
        listView.setEditable(true);
        listView.setStyle("-fx-font-size: 20;");
        //Changing font for ListView elements



        listView.setPadding(new Insets(10, 10, 10, 10));
        listView.setItems(listItems);
        listView.setOrientation(Orientation.VERTICAL);


        secondPartofCenter.getChildren().add(savedInformationText);
        secondPartofCenter.getChildren().add(listView);
        center.getChildren().add(firstPartofCenter);
        center.getChildren().add(secondPartofCenter);


        //Scale transitions
        scaleTransition_01.setNode(leftSide);
        scaleTransition_01.setCycleCount(Animation.INDEFINITE);
        scaleTransition_01.setDuration(Duration.seconds(4));
        scaleTransition_01.setAutoReverse(true);
        scaleTransition_01.setToX(2);
        scaleTransition_01.play();
        scaleTransition_02.setNode(rightSide);
        scaleTransition_02.setCycleCount(Animation.INDEFINITE);
        scaleTransition_02.setDuration(Duration.seconds(4));
        scaleTransition_02.setAutoReverse(true);
        scaleTransition_02.setToX(2);
        scaleTransition_02.play();
        scaleTransition_03.setNode(topBox);
        scaleTransition_03.setCycleCount(Animation.INDEFINITE);
        scaleTransition_03.setDuration(Duration.seconds(4));
        scaleTransition_03.setAutoReverse(true);
        scaleTransition_03.setToY(1.5);
        scaleTransition_03.play();
        scaleTransition_04.setNode(bottomBox);
        scaleTransition_04.setCycleCount(Animation.INDEFINITE);
        scaleTransition_04.setDuration(Duration.seconds(4));
        scaleTransition_04.setAutoReverse(true);
        scaleTransition_04.setToY(1.5);
        scaleTransition_04.play();



        root.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER){
                if (textField.getText() != null && !textField.getText().isEmpty()){
                    gathering = textField.getText();
                    listItems.add(gathering);
                    textField.setText("");
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("NO INPUT");
                    alert.setHeaderText("Please type in a note");
                    alert.setContentText("To save an empty note is not necessary, please type in a note");
                    alert.showAndWait();
                }
            }
        });
        root.setLeft(leftSide);
        root.setRight(rightSide);
        root.setTop(topBox);
        root.setBottom(bottomBox);
        root.setCenter(center);
        root.setStyle("-fx-background-color: #41638e;");
        window.setScene(scena);
        window.setTitle("Application_StoringDifferentObjects Ver. 01");
        window.setResizable(false);
        window.show();




    }
}
