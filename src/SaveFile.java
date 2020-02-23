import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFile {
    public static void saveFile() throws IOException {
        Hangman.loadSaveObject= null;
        Hangman.loadSaveObject = new LoadSaveInformation(Hangman.randomWord,Hangman.guessCount.getValue(),Hangman.correctGuessedWords,Hangman.wrongGuessedWords);
        //save the HashedLibrary object into the library.obj file
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Save Curren Game");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HangMan Files","*.hng"));
        File selectedFile = fileChooser.showSaveDialog(Hangman.myStage);
        if(selectedFile == null){
            return;
        }
        FileOutputStream file = null;
        if(!selectedFile.toString().endsWith(".hng")){
            file = new FileOutputStream(selectedFile+".hng");
        }
        else {
            file = new FileOutputStream(selectedFile);
        }

        ObjectOutputStream oos = new ObjectOutputStream(file);
        oos.writeObject(Hangman.loadSaveObject);
        oos.close();
    }
}
