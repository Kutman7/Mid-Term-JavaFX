# Tic Tac Toe game

My project for Midterm

Author: **Kutman Eshenkulov**

___
[Video Tutorial](https://www.youtube.com/watch?v=dQw4w9WgXcQ)

## Description

* Screenshots:

![Game window](https://user-images.githubusercontent.com/73386100/111062011-8dae3380-84d0-11eb-9ee3-af9a84fdeeb6.png)
![Game in work](https://user-images.githubusercontent.com/73386100/111061997-75d6af80-84d0-11eb-9ff5-da9a1ec0d805.png)
* Code Description:

```
package sample;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    private static final Pane root = new Pane();
    private int turn = 0;
    private final ArrayList<Integer> coordinates1 = new ArrayList<>();
    private final ArrayList<Integer> coordinates2 = new ArrayList<>();
    private static final ArrayList<Integer> untouchable = new ArrayList<>();
    private final ArrayList<Integer> clicked_x = new ArrayList<>();
    private final ArrayList<Integer> clicked_y = new ArrayList<>();
    private static boolean playable = true;

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
                untouchable.add(j * 50);
                untouchable.add(i * 50);
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/sample/x.png"));
        primaryStage.setTitle("Tic Tac Toe");
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
                if (turn % 2 == 0 && playable) {

                    clicked_x.add((int) Math.round(translateXProperty().getValue()));
                    clicked_x.add((int) Math.round(translateYProperty().getValue()));
                    if (playable(clicked_x, coordinates1))
                        drawX();
                } else if (playable) {

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
                if (coordinates1.get(i).equals(clicked1.get(j)) &&
                        coordinates1.get(i + 1).equals(clicked1.get(j + 1))) {
                    coordinates1.set(i, 999);
                    coordinates1.set(i + 1, 999);
                }
            }

        }
        for (int t = 0; t < coordinates1.size(); t += 2) {
            try {
                if ((t == 4 && coordinates1.get(t) == 999 && coordinates1.get(t + 4) == 999 &&
                        coordinates1.get(t + 8) == 999)) {
                    winLine(untouchable.get(t), untouchable.get(t + 1), untouchable.get(t + 8),
                            untouchable.get(t + 9), 40, 14, 10, 44);
                    playable = false;
                } else if ((coordinates1.get(t) == 999 && coordinates1.get(t + 8) == 999 &&
                        coordinates1.get(t + 16) == 999)) {
                    winLine(untouchable.get(t), untouchable.get(t + 1), untouchable.get(t + 16),
                            untouchable.get(t + 17), 10, 14, 40, 44);
                    playable = false;
                } else if (coordinates1.get(t) == 999 && coordinates1.get(t + 2) == 999 &&
                        coordinates1.get(t + 4) == 999 && (t == 0 || t == 6 || t == 12)) {
                    winLine(untouchable.get(t), untouchable.get(t + 1), untouchable.get(t + 4),
                            untouchable.get(t + 5), 10, 28, 40, 28);
                    playable = false;
                } else if ((coordinates1.get(t) == 999 && coordinates1.get(t + 6) == 999 &&
                        coordinates1.get(t + 12) == 999)) {
                    winLine(untouchable.get(t), untouchable.get(t + 1), untouchable.get(t + 12),
                            untouchable.get(t + 13), 26, 12, 26, 45);
                    playable = false;
                }

            } catch (IndexOutOfBoundsException ignore) {
            }
        }
        return true;
    }

    public static void winLine(int startX, int startY, int endX, int endY, int x, int y, int x1, int y1) {
        Line line = new Line();
        line.setStroke(Color.CORNFLOWERBLUE);
        line.setStrokeWidth(5);
        line.setStartX(startX + x);
        line.setStartY(startY + y);
        line.setEndX(endX + x1);
        line.setEndY(endY + y1);

        root.getChildren().add(line);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```