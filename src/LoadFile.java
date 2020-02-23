import javafx.stage.FileChooser;
import java.io.*;

public class LoadFile {
    public static LoadSaveInformation loadFile() throws IOException, ClassNotFoundException {
        LoadSaveInformation loadSaveObj;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load HangMan Game");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HangMan Files","*.hng"));
        File selectedFile = fileChooser.showOpenDialog(Hangman.myStage);

        if(selectedFile == null){
            return null;
        }
        FileInputStream fis = new FileInputStream(selectedFile);
        ObjectInputStream inStream = new ObjectInputStream(fis);
        loadSaveObj= (LoadSaveInformation) inStream.readObject();
        inStream.close();
        return loadSaveObj;
    }
}
