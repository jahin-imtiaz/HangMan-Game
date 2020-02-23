import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CreateGuessingBoardSide {
    public static void setGuessingBoardSide(int wordSize, boolean loadFlag){

        if(loadFlag==false){
            Hangman.guessCount = new SimpleIntegerProperty(10);
        }
        else{
            Hangman.guessCount = new SimpleIntegerProperty(Hangman.loadSaveObject.getGuessCount());
        }
        Hangman.guessCount.addListener((v,oldValue,newValue)-> Hangman.guessCountLabel.setText("Remaining Guessues "+Hangman.guessCount.getValue()));
        Hangman.guessCountLabel = new Label("Remaining Guessues "+Hangman.guessCount.getValue());
        Hangman.guessCountLabel.setPadding(new Insets(10,10,10,10));
        Hangman.guessingBoardSideVbox = new VBox();
        Hangman.guessingBoardSideVbox.setMinWidth(400);
        Hangman.guessCountLabel.prefWidthProperty().bind(Hangman.guessingBoardSideVbox.widthProperty());
        Hangman.guessingBoardSideVbox.prefWidthProperty().bind(Hangman.innerBorderPane.widthProperty().divide(2));
        Hangman.guessingBoardSideVbox.prefHeightProperty().bind(Hangman.innerBorderPane.heightProperty());

        Hangman.textFieldsList = new ArrayList<>();
        for(int i=0; i<wordSize;i++){
            TextField textFieldbox = new TextField();
            textFieldbox.setMaxSize(32,25);
            textFieldbox.setStyle("-fx-background-color: Black; -fx-border-width: 1; -fx-border-color: Gray");
            Hangman.textFieldsList.add(textFieldbox);
        }

        int n=0;
        int c=0;
        VBox wordsVbox = new VBox();
        wordsVbox.prefWidthProperty().bind(Hangman.guessingBoardSideVbox.widthProperty());
        wordsVbox.getChildren().add(new HBox());
        ((HBox)wordsVbox.getChildren().get(c)).setSpacing(2);
        ((HBox)wordsVbox.getChildren().get(c)).setPadding(new Insets(10,10,10,10));

        while(n<wordSize){
            if(n%10 == 0){
                c++;
                wordsVbox.getChildren().add(new HBox());
                ((HBox)wordsVbox.getChildren().get(c)).setSpacing(2);
                ((HBox)wordsVbox.getChildren().get(c)).setPadding(new Insets(10,10,10,10));
            }

            ((HBox)wordsVbox.getChildren().get(c)).getChildren().add(Hangman.textFieldsList.get(n));
            n++;
        }

        Pane bottomEmptyPane = new Pane();
        bottomEmptyPane.setMinSize(400,100);
        bottomEmptyPane.prefWidthProperty().bind(Hangman.guessingBoardSideVbox.widthProperty());
        bottomEmptyPane.prefHeightProperty().bind(Hangman.guessingBoardSideVbox.heightProperty());

        CreateKeyBoard.setGuessingBoard(loadFlag);

        Hangman.guessingBoardSideVbox.getChildren().addAll(Hangman.guessCountLabel,wordsVbox,Hangman.guessingBoardGrid,bottomEmptyPane);
        Hangman.innerBorderPane.setRight(Hangman.guessingBoardSideVbox);
    }
    public static void guessCheck(String s, Button a) throws URISyntaxException {
        Hangman.startButton.setDisable(false);
        Hangman.saveButton.setDisable(false);
        if(Hangman.randomWord.contains(s)){
            List<Integer> indexList;
            indexList= getIndexList(Hangman.randomWord,s);
            for(int i : indexList){
                Hangman.textFieldsList.get(i).setText(s.toUpperCase());
                Hangman.textFieldsList.get(i).setStyle("-fx-text-inner-color: White");
                Hangman.textFieldsList.get(i).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY,Insets.EMPTY)));
                Hangman.textFieldsList.get(i).setDisable(true);
                Hangman.validLetterTracker[i]=true;
            }
            Hangman.correctGuessedWords.add(s.toUpperCase());
            a.setDisable(true);
        }
        else{
            Hangman.HangManPane.getChildren().add(Hangman.shapeArray[Hangman.guessCount.getValue()-1]);
            Hangman.guessCount.setValue(Hangman.guessCount.getValue()-1);
            a.setDisable(true);
            Hangman.wrongGuessedWords.add(s.toUpperCase());
        }
        checkWinningBoard();
    }
    public static List<Integer> getIndexList(String word, String s){
        List<Integer> indexList = new ArrayList<>();
        int index = word.indexOf(s);
        while(index >=0){
            indexList.add(index);
            index = word.indexOf(s,index+1);
        }
        return indexList;
    }

    public static void checkWinningBoard() throws URISyntaxException {
        if(Hangman.guessCount.getValue()==0){
            //you lose , Show unguessed letters in red Box
            for(int i=0;i<Hangman.validLetterTracker.length;i++){
                if(Hangman.validLetterTracker[i] == false){
                    Hangman.textFieldsList.get(i).setText(Hangman.randomWord.charAt(i)+"");
                    Hangman.textFieldsList.get(i).setStyle("-fx-text-inner-color: White");
                    Hangman.textFieldsList.get(i).setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY,Insets.EMPTY)));
                    Hangman.textFieldsList.get(i).setDisable(true);
                }
            }
            Hangman.guessingBoardGrid.setDisable(true);
            //you lose window
            PopLoseWindow.popLoseWindow();
            Hangman.saveButton.setDisable(true);
        }
        else if(all_True(Hangman.validLetterTracker)){
            //you win
            Hangman.guessingBoardGrid.setDisable(true);
            PopWinWindow.popWinWindow();
            Hangman.saveButton.setDisable(true);
        }
    }

    public static boolean all_True(boolean[] boolArray){
        boolean flag= false;
        for(boolean b : boolArray){
            if(b == false) {
                return false;
            }
        }
        return true;
    }
}
