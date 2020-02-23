import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class PopNewButtonExitWindow {
    public static void popNewButtonExitWindow(){
        Stage closeWindow = new Stage();
        closeWindow.setTitle("EXIT");
        VBox pane2 = new VBox();
        pane2.setSpacing(10);
        HBox pane = new HBox();
        pane.setSpacing(20);

        Label label = new Label("Would you like to save the game? ");
        label.prefWidthProperty().bind(pane.widthProperty());
        label.setAlignment(Pos.CENTER);

        Button yes = new Button("YES");
        yes.setStyle("-fx-border-color: LightBlue");
        yes.prefWidthProperty().bind(pane.widthProperty());
        yes.setOnAction(e -> {
            closeWindow.close();
            Hangman.startButton.setDisable(true);
            Hangman.saveButton.setDisable(true);
            try {
                SaveFile.saveFile();
                CreatePlayingPage.setPlayingPage(Hangman.loadFlag);
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }

        });

        Button no = new Button("NO");
        no.setStyle("-fx-border-color: LightBlue");
        no.prefWidthProperty().bind(pane.widthProperty());
        no.setOnAction(e -> {
            closeWindow.close();
            Hangman.startButton.setDisable(true);
            Hangman.saveButton.setDisable(true);
            try {
                CreatePlayingPage.setPlayingPage(Hangman.loadFlag);
            } catch (FileNotFoundException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        Button cancel = new Button("CANCEL");
        cancel.setStyle("-fx-border-color: LightBlue");
        cancel.prefWidthProperty().bind(pane.widthProperty());
        cancel.setOnAction(e -> closeWindow.close());

        pane.getChildren().addAll(yes,no,cancel);
        pane2.getChildren().addAll(label,pane);
        Scene closeScene = new Scene(pane2,400,300);
        closeWindow.setScene(closeScene);
        closeWindow.show();
    }
}
