package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setTitle("Project 4");
        Canvas canvas = new Canvas(675,300);
        GraphicsContext gx = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Label label1 = new Label("N = ");
        TextField textField = new TextField(" ");
        HBox hb = new HBox();
        hb.getChildren().addAll(label1,textField);
        hb.setSpacing(10);
        root.getChildren().add(hb);
        Button draw = new Button("Draw");
        hb.getChildren().add(draw);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        double canvaswidth = canvas.getWidth();
        double canvasheight = canvas.getHeight();
        double radius;
        if (canvasheight > canvaswidth) {
            radius = canvaswidth / 2;
        } else radius = canvasheight / 2;

    MyPieChart P = new MyPieChart(canvaswidth/2, canvasheight/2, radius,radius);
    HistogramAlphabet H = new HistogramAlphabet();
    H.count_chars(P);
        draw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                assert (Integer.parseInt(textField.getText()) < 26 && Integer.parseInt(textField.getText()) > 0);
                if (Integer.parseInt(textField.getText()) > 26)
                    P.setN(26);
                else if (Integer.parseInt(textField.getText()) < 0)
                    P.setN(0);
                else {
                    P.setN(Integer.parseInt(textField.getText()));
                }
                gx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                P.draw(gx);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
