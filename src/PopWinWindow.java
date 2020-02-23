import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class PopWinWindow {
    public static void popWinWindow() throws URISyntaxException {
        Stage winWindow = new Stage();
        winWindow.setTitle("Game Over");

        VBox pane = new VBox();
        ImageView winImage = new ImageView(new Image(String.valueOf(Hangman.class.getResource("win.png").toURI())));
        winImage.fitWidthProperty().bind(pane.widthProperty());

        Button close = new Button("Close");
        close.setStyle("-fx-border-color: LightBlue");
        VBox.setMargin(close, new Insets(10,170,10,170));
        close.prefWidthProperty().bind(pane.widthProperty());
        close.setOnAction(e -> winWindow.close());

        pane.getChildren().addAll(winImage,close);
        Scene winScene = new Scene(pane,400,300);
        winWindow.setScene(winScene);
        winWindow.show();
    }
}
