import java.io.Serializable;
import java.util.List;

public class LoadSaveInformation implements Serializable {

    private String randomWord;
    private int guessCount;
    private List<String> correctGuessedWords;
    private List<String> wrongGuessedWords;

    public String getRandomWord() {
        return randomWord;
    }

    public LoadSaveInformation(String randomWord, int guessCount, List<String> correctGuessedWords, List<String> wrongGuessedWords){
        this.randomWord = randomWord;
        this.guessCount = guessCount;
        this.correctGuessedWords= correctGuessedWords;
        this.wrongGuessedWords = wrongGuessedWords;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public List<String> getCorrectGuessedWords() {
        return correctGuessedWords;
    }

    public List<String> getWrongGuessedWords() {
        return wrongGuessedWords;
    }
}
