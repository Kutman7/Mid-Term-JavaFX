package sample;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    private final Pane root = new Pane();
    private int turn = 0;
    private final ArrayList<Integer> coordinates1 = new ArrayList<>();
    private final ArrayList<Integer> coordinates2 = new ArrayList<>();
    private final ArrayList<Integer> clicked_x = new ArrayList<>();
    private final ArrayList<Integer> clicked_y = new ArrayList<>();

    private Parent createContent() {
        root.setPrefSize(151, 151);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 50);
                tile.setTranslateY(i * 50);

                root.getChildren().add(tile);
                coordinates1.add(j * 50);
                coordinates1.add(i * 50);
                coordinates2.add(j * 50);
                coordinates2.add(i * 50);
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private class Tile extends StackPane {
        private final Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(50, 50);
            border.setFill(Color.GRAY);
            border.setStroke(Color.WHITE);
            text.setFont(Font.font(40));
            getChildren().addAll(border, text);
            setOnMouseClicked(event -> {
                turn++;
                if (turn > 9) {
                    System.exit(1);
                }
                if (turn % 2 == 0) {

                    clicked_x.add((int) Math.round(translateXProperty().getValue()));
                    clicked_x.add((int) Math.round(translateYProperty().getValue()));
                    if (playable(clicked_x, coordinates1))
                        drawX();
                } else {

                    clicked_y.add((int) Math.round(translateXProperty().getValue()));
                    clicked_y.add((int) Math.round(translateYProperty().getValue()));
                    if (playable(clicked_y, coordinates2))
                        drawO();
                }
            });
        }

        private void drawX() {
            text.setText("✖");
        }

        private void drawO() {
            text.setText("⚫");
        }
    }

    public static boolean playable(ArrayList<Integer> clicked1, ArrayList<Integer> coordinates1) {
        for (int i = 0; i < coordinates1.size(); i += 2) {
            for (int j = 0; j < clicked1.size(); j += 2) {
                if (coordinates1.get(i).equals(clicked1.get(j)) && coordinates1.get(i + 1).equals(clicked1.get(j + 1))) {
                    coordinates1.set(i, 999);
                    coordinates1.set(i + 1, 999);
                }
            }
        }
        for (int t = 0; t < coordinates1.size(); t += 2) {
            try {
                if (coordinates1.get(t) == 999 && coordinates1.get(t + 2) == 999 && coordinates1.get(t + 4) == 999 ||
                        (coordinates1.get(t) == 999 && coordinates1.get(t + 4) == 999 && coordinates1.get(t + 8) == 999)
                        || (coordinates1.get(t) == 999 && coordinates1.get(t + 8) == 999 && coordinates1.get(t + 16) == 999)
                        || (coordinates1.get(t) == 999 && coordinates1.get(t + 6) == 999 && coordinates1.get(t + 12) == 999)) {
                    System.out.println("lkj");
                    System.exit(1);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("sss");
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
