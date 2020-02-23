import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CreateKeyBoard {
    public static void setGuessingBoard(boolean loadFlag){
        Hangman.guessingBoardGrid= new GridPane();
        Hangman.guessingBoardGrid.prefWidthProperty().bind(Hangman.guessingBoardSideVbox.widthProperty());
        Hangman.guessingBoardGrid.prefHeightProperty().bind(Hangman.guessingBoardSideVbox.widthProperty());
        Hangman.guessingBoardGrid.setPadding(new Insets(10,10,10,10));
        Hangman.guessingBoardGrid.setVgap(2);
        Hangman.guessingBoardGrid.setHgap(2);
        //create buttons
        int n=0;
        int ascii=65;
        Hangman.buttonList = new ArrayList<>();
        for(int i=0; i<4; i++){
            for(int j=0; j<7;j++){
                int fin = ascii;
                Button b = new Button(Character.toString((char)ascii));
                b.setOnAction( e -> {
                    try {
                        CreateGuessingBoardSide.guessCheck(Character.toString((char)fin),b);
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                });
                GridPane.setConstraints(b,j,i);
                b.prefHeightProperty().bind(Hangman.guessingBoardGrid.heightProperty());
                b.prefWidthProperty().bind(Hangman.guessingBoardGrid.widthProperty());
                b.setTextFill(Color.WHITE);
                b.setStyle("-fx-background-color: GreenYellow");
                b.setMinSize(50,50);
                Hangman.guessingBoardGrid.getChildren().add(b);
                Hangman.buttonList.add(b);
                n++;
                ascii++;
                if(n==26) break;
            }
        }

        if(loadFlag == true){
            for(String guess : Hangman.loadSaveObject.getCorrectGuessedWords()){
                List<Integer> indexList;
                indexList= CreateGuessingBoardSide.getIndexList(Hangman.randomWord,guess);
                for(int i : indexList){
                    Hangman.textFieldsList.get(i).setText(guess.toUpperCase());
                    Hangman.textFieldsList.get(i).setStyle("-fx-text-inner-color: White");
                    Hangman.textFieldsList.get(i).setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY,Insets.EMPTY)));
                    Hangman.textFieldsList.get(i).setDisable(true);
                    Hangman.validLetterTracker[i]=true;
                }
                Hangman.guessingBoardGrid.getChildren().get(Hangman.letterString.indexOf(guess)).setDisable(true);
            }
            int c=10;
            for(String wrong : Hangman.loadSaveObject.getWrongGuessedWords()){
                Hangman.HangManPane.getChildren().add(Hangman.shapeArray[c-1]);
                Hangman.guessingBoardGrid.getChildren().get(Hangman.letterString.indexOf(wrong)).setDisable(true);
                c--;
            }
        }

    }
}
