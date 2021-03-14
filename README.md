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

Import all needed packages and classes.

```Java
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

```

Create "<ins>Main</ins>" class which extends Application. Create Pane, create "turn" variable to designate as turn, create "playable"
variable to check if game is over or not, create two "clicked" arrays to mark pressed "<ins>Tiles</ins>" and create two "
coordinate" arrays to get coordinates of combo "<ins>Tiles</ins>" to draw lines between them.

```Java
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
                Tile tile = new Tile(); // We will create Tile class later.
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

    public static void main(String[] args) {
        launch(args);
    }
}
```

Create Scene, set title and icon.

```Java
public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/sample/x.png"));
        primaryStage.setTitle("Tic Tac Toe");
    }
}
```

Create "<ins>Tile</ins>" class which will create rectangle tiles to play. Create mouse event handler to create "✖" or "⚫" when
mouse clicked. Do not forget to mark clicked "<ins>Tiles</ins>" in "clicked" array which we created before. Stop the program if user
clicked more than 9 times or if we have winner. Of course create "✖" and "⚫" text setters.

```Java
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
```

