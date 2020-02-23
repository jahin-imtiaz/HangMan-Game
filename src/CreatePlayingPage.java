import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreatePlayingPage {
    public static void setPlayingPage(boolean loadFlag) throws FileNotFoundException, URISyntaxException {
        if(loadFlag == true){
            CreateBottomStartButton.setBottomStartButton();
            Hangman.wrongGuessedWords= Hangman.loadSaveObject.getWrongGuessedWords();
            Hangman.correctGuessedWords = Hangman.loadSaveObject.getCorrectGuessedWords();
        }
        else{
            Hangman.wrongGuessedWords= new ArrayList<>();
            Hangman.correctGuessedWords = new ArrayList<>();
        }
        Hangman.keyFlag = true;
        Hangman.bottomStartButton.setDisable(true);
        Hangman.saveButton.setDisable(true);
        if(loadFlag==false){
            //Scanner scanner = new Scanner(new File("words.txt"));
            Scanner scanner = new Scanner(new File(Hangman.class.getResource("words.txt").toURI()));
            List<String> wordList = new ArrayList<>();
            while(scanner.hasNext()){
                wordList.add(scanner.nextLine().trim().replaceAll("[^a-zA-z]",""));
            }
            Random r = new Random();
            Hangman.randomWord = wordList.get(r.nextInt(wordList.size())).toUpperCase();
            Hangman.validLetterTracker = new boolean[Hangman.randomWord.length()];
        }
        else{
            Hangman.randomWord = Hangman.loadSaveObject.getRandomWord();
            Hangman.validLetterTracker = new boolean[Hangman.randomWord.length()];
        }
        //create HangMan label
        Label titleLabel = new Label("Hangman");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        titleLabel.setStyle("-fx-font-size:  40;"+"-fx-text-fill: White;");

        Hangman.innerBorderPane.setTop(titleLabel);
        CreateHangManSide.setHangManSide();
        CreateGuessingBoardSide.setGuessingBoardSide(Hangman.randomWord.length(),loadFlag);
        Hangman.scene1.getRoot().requestFocus();
    }
}
