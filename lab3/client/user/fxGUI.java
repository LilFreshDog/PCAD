package lab3.client.user;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class fxGUI extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Stage stage = new Stage();
        stage.setTitle("Prenotazione Eventi");
        stage.setWidth(600);
        stage.setHeight(600);

        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        Text text = new Text();
        text.setText("ASS");
        text.setX(stage.getWidth() / 2 - text.getBaselineOffset());
        text.setY(30);
        text.setFill(Color.WHITE);
        root.getChildren().add(text);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITE);
        root.getChildren().add(line);

        Rectangle rect = new Rectangle();
        rect.setX(100);
        rect.setY(200);
        rect.setWidth(100);
        rect.setHeight(200);
        rect.setFill(Color.BLUE);
        rect.setStrokeWidth(1);
        rect.setStroke(Color.INDIANRED);
        root.getChildren().add(rect);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                200.0, 200.0,
                500.0, -200.0,
                200.0, 400.0);
        triangle.setFill(Color.CORAL);
        triangle.setOpacity(.5);
        root.getChildren().add(triangle);

        Circle circle = new Circle();
        circle.setCenterX(300);
        circle.setCenterY(300);
        circle.setRadius(50);
        circle.setFill(Color.DARKMAGENTA);
        root.getChildren().add(circle);


        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
