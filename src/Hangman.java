import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.stage.*;
import javafx.scene.*;;
import java.util.*;

public class Hangman extends Application {
    static Scene scene1;
    static Stage myStage;
    static Group root;
    static BorderPane outerborderPane,innerBorderPane;
    static GridPane guessingBoardGrid;
    static Pane HangManPane;
    static VBox guessingBoardSideVbox;
    static HBox topHBox;
    static Button startButton, loadButton, saveButton, exitButton,bottomStartButton;
    static Label guessCountLabel;
    static IntegerProperty guessCount;
    static List<TextField> textFieldsList;
    static List<String> correctGuessedWords, wrongGuessedWords;
    static List<Button> buttonList;
    static String randomWord="";
    static String letterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static boolean[] validLetterTracker;
    static boolean loadFlag = false, keyFlag= false;
    static Shape[] shapeArray ;
    static LoadSaveInformation loadSaveObject;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        scene1 = new Scene(root,800,600);
        scene1.setOnKeyPressed(e->checkKey(e));
        CreateStartPage.setSartPage();
        myStage = primaryStage;
        myStage.setScene(scene1);
        myStage.getIcons().add( new Image("file:title.png"));
        myStage.setTitle("Hangman");
        myStage.show();
    }

    public void checkKey(KeyEvent e){
        if(keyFlag == true) {
            for (Button b : buttonList) {
                if (e.getCode().toString().equals(b.getText())) {
                    b.fire();
                }
            }
        }
    }
}
