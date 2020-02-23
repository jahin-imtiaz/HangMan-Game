import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URISyntaxException;

public class CreateStartPage {
    public static void setSartPage() throws URISyntaxException {
        //Outer LayOut
        Hangman.outerborderPane = new BorderPane();
        Hangman.innerBorderPane = new BorderPane();
        //set the inner Border Pane in the outerBorder Pane
        Hangman.outerborderPane.setCenter(Hangman.innerBorderPane);
        Hangman.outerborderPane.setStyle("-fx-background-color: LIGHTSTEELBLUE");
        Hangman.outerborderPane.setPadding(new Insets(2,2,2,2));

        //top HBOX for buttons
        Hangman.topHBox = new HBox();
        Hangman.topHBox.prefWidthProperty().bind(Hangman.outerborderPane.widthProperty());
        Hangman.topHBox.setBackground(new Background(new BackgroundFill(Color.valueOf("DCDCDC"), CornerRadii.EMPTY, Insets.EMPTY)));
        //Start Buttons
        Hangman.startButton = new Button();
        Hangman.startButton.setStyle("-fx-border-width: 1; -fx-border-color: A9A9A9; -fx-pref-width: 50; -fx-background-color: LightGray ");
        //startButton.setGraphic(new ImageView(new Image("file:new.png")));
        Hangman.startButton.setGraphic(new ImageView(new Image(String.valueOf(Hangman.class.getResource("new.png").toURI()))));
        Hangman.startButton.setOnAction(e -> {
            e.consume();
            if(!Hangman.saveButton.isDisabled()){
                PopNewButtonExitWindow.popNewButtonExitWindow();
            }
            else {
                Hangman.startButton.setDisable(true);
                CreateBottomStartButton.setBottomStartButton();
            }
        });
        //Load Button
        Hangman.loadButton = new Button();
        Hangman.loadButton.setStyle("-fx-border-width: 1; -fx-border-color: A9A9A9; -fx-pref-width: 50;-fx-background-color: LightGray ");
        Hangman.loadButton.setGraphic(new ImageView(new Image(String.valueOf(Hangman.class.getResource("Load.png").toURI()))));
        Hangman.loadButton.setOnAction(e->{
            try {
                Hangman.loadSaveObject = null;
                Hangman.loadSaveObject= LoadFile.loadFile();
                if(Hangman.loadSaveObject != null){
                    CreatePlayingPage.setPlayingPage(true);
                }
            } catch (IOException | ClassNotFoundException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        //save Buttion
        Hangman.saveButton = new Button();
        Hangman.saveButton.setStyle("-fx-border-width: 1; -fx-border-color: A9A9A9; -fx-pref-width: 50;-fx-background-color: LightGray ");
        Hangman.saveButton.setGraphic(new ImageView(new Image(String.valueOf(Hangman.class.getResource("Save.png").toURI()))));
        Hangman.saveButton.setDisable(true);
        Hangman.saveButton.setOnAction(e->{
            try {
                SaveFile.saveFile();
                Hangman.saveButton.setDisable(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //exit button
        Hangman.exitButton = new Button();
        Hangman.exitButton.setStyle("-fx-border-width: 1; -fx-border-color: A9A9A9; -fx-pref-width: 50;-fx-background-color: LightGray ");
        Hangman.exitButton.setGraphic(new ImageView(new Image(String.valueOf(Hangman.class.getResource("Exit.png").toURI()))));
        Hangman.exitButton.setOnAction(e -> {
            if(!Hangman.saveButton.isDisabled()){
                PopExitWindow.popExitWindow();
            }
            else Hangman.myStage.close();
        });

        Hangman.topHBox.getChildren().addAll(Hangman.startButton,Hangman.loadButton,Hangman.saveButton,Hangman.exitButton);
        //add topHbox to outer Border Pane
        Hangman.outerborderPane.setTop(Hangman.topHBox);
        //add outer Border Pane to scene
        Hangman.outerborderPane.prefWidthProperty().bind(Hangman.scene1.widthProperty());
        Hangman.outerborderPane.prefHeightProperty().bind(Hangman.scene1.heightProperty());
        Hangman.root.getChildren().add(Hangman.outerborderPane);
    }
}
