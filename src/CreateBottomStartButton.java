import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class CreateBottomStartButton {
    public static void setBottomStartButton(){
        Hangman.innerBorderPane.prefHeightProperty().bind(Hangman.outerborderPane.heightProperty());
        Hangman.innerBorderPane.prefWidthProperty().bind(Hangman.outerborderPane.widthProperty());

        //create the start button bar at the bottom
        HBox bottomHbox = new HBox();
        bottomHbox.prefWidthProperty().bind(Hangman.innerBorderPane.widthProperty());
        Hangman.bottomStartButton = new Button("Start Playing");
        Hangman.bottomStartButton.setStyle("-fx-background-color: LIGHTGRAY;"+"-fx-border-color: Gray;");
        bottomHbox.setAlignment(Pos.CENTER);

        bottomHbox.setMinSize(800,35);
        bottomHbox.setStyle("-fx-background-color: #DCDCDC");
        bottomHbox.getChildren().addAll(Hangman.bottomStartButton);

        //set the bar in the innerBorderPane
        Hangman.innerBorderPane.setBottom(bottomHbox);
        //set innerBorder panes center. so that it looks full now
        Pane pane1 = new Pane();
        pane1.prefHeightProperty().bind(Hangman.innerBorderPane.heightProperty());
        pane1.prefWidthProperty().bind(Hangman.innerBorderPane.widthProperty().divide(2));
        Hangman.innerBorderPane.setRight(pane1);
        Pane pane2 = new Pane();
        pane2.prefHeightProperty().bind(Hangman.innerBorderPane.heightProperty());
        pane2.prefWidthProperty().bind(Hangman.innerBorderPane.widthProperty().divide(2));
        Hangman.innerBorderPane.setLeft(pane2);
        Hangman.bottomStartButton.setOnAction(e ->{
            try {
                Hangman.bottomStartButton.setDisable(true);
                CreatePlayingPage.setPlayingPage(Hangman.loadFlag);
            } catch (FileNotFoundException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
    }
}
