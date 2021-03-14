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

Create main class which extends Application. Create Pane, create "turn" variable to designate as turn, create "playable"
variable to check if game is over or not, create two "clicked" arrays to mark pressed "Tiles" and create two "
coordinate" arrays to get coordinates of combo "Tiles" to draw lines between them.

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

    public static void main(String[] args) {
        launch(args);
    }
}
```