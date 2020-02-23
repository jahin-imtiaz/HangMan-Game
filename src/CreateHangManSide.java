import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class CreateHangManSide {
    public static void setHangManSide(){
        Hangman.HangManPane = new Pane();
        Hangman.HangManPane.setMinWidth(400);
        Hangman.HangManPane.prefWidthProperty().bind(Hangman.innerBorderPane.widthProperty().divide(2));
        Hangman.HangManPane.prefHeightProperty().bind(Hangman.innerBorderPane.heightProperty());

        Line bottomLine = new Line(300,300,50,300);
        Line leftLine = new Line(50,300,50,50);
        Line TopLine = new Line(50,50,250,50);
        Line downLine = new Line(250,50,250,100);
        Circle circle = new Circle(250,125,25);
        Line body = new Line(250,150,250,250);
        Line rightHand = new Line(250,190, 320,220);
        Line leftHand = new Line(250,190,190,220);
        Line righLeg = new Line(250,250,320,280);
        Line leftLeg = new Line(250,250,190,280);

        Hangman.shapeArray = new Shape[]{leftLeg,righLeg,leftHand,rightHand,body,circle,downLine,TopLine,leftLine,bottomLine};
        Hangman.innerBorderPane.setLeft(Hangman.HangManPane);
    }
}
