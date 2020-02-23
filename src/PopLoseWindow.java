import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class PopLoseWindow {
    public static void popLoseWindow() throws URISyntaxException {
        Stage loseWindow = new Stage();
        loseWindow.setTitle("Game Over");

        VBox pane = new VBox();
        ImageView loseImage = new ImageView(new Image(String.valueOf(Hangman.class.getResource("lost.png").toURI())));
        loseImage.fitWidthProperty().bind(pane.widthProperty());

        Label label = new Label("The word was \""+ Hangman.randomWord.toLowerCase() +"\"");
        label.prefWidthProperty().bind(pane.widthProperty());
        label.setAlignment(Pos.CENTER);

        Button close = new Button("Close");
        close.setStyle("-fx-border-color: LightBlue");
        VBox.setMargin(close, new Insets(10,170,10,170));
        close.prefWidthProperty().bind(pane.widthProperty());
        close.setOnAction(e -> loseWindow.close());

        pane.getChildren().addAll(loseImage,label,close);
        Scene loseScene = new Scene(pane,400,300);
        loseWindow.setScene(loseScene);
        loseWindow.show();
    }
}
