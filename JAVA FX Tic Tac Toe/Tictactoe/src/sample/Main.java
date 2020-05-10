package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    private boolean isGameDone = false;
    private Square[][] board = new Square[3][3];
    private Stage astage;

    private class Square extends StackPane {
        Rectangle rect = new Rectangle(200, 200);

        public Square() {
            // Rectangle rect = new Rectangle(200, 200);

            rect.setFill(null);
            rect.setId("test");
            rect.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);

            getChildren().addAll(rect);

            setOnMouseClicked(mouseEvent -> {
                try {

                    if (!isGameDone) {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            Drawimage(rect, "x");
                            check();
                            System.out.println("imhereX");
                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            Drawimage(rect, "o");
                            check();
                            System.out.println("imhereO");
                        }
                    }
                    checkdraw();

                    if (isGameDone) {
                        System.out.println("im here");
                        Button b = new Button("Restart game");
                        b.setPrefWidth(130);
                        b.setPrefHeight(30);
                        getChildren().add(b);
                        b.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                restGame();
                                getChildren().remove(getChildren().size() - 1);
                                b.setOnAction(null);
                            }
                        });
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            });

        }

        private void restGame() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    board[i][j].rect.setId("test");
                    board[i][j].rect.setFill(null);
                }
            }
            isGameDone = false;
            astage.setTitle("TIC TAC TOE- Any Player Can Start");
        }

        private void Drawimage(Rectangle rectangle, String xOro) throws FileNotFoundException {
            Image image = null;
            if (rectangle.getId().equals("test")) {
                if (xOro.equals("x") && (astage.getTitle().equals("It's X's Turn")
                        || astage.getTitle().equals("TIC TAC TOE- Any Player Can Start"))) {
                    // Creating an image
                    image = new Image(new FileInputStream("x.jpg"));
                    rectangle.setFill(new ImagePattern(image));
                    astage.setTitle("It's O's Turn");

                    rectangle.setId("x");
                } else {
                    if (astage.getTitle().equals("It's O's Turn")
                            || astage.getTitle().equals("TIC TAC TOE- Any Player Can Start")) {
                        // Creating an image
                        image = new Image(new FileInputStream("o.jpg"));
                        rectangle.setId("o");
                        astage.setTitle("It's X's Turn");
                        rectangle.setFill(new ImagePattern(image));
                    }
                }

            }

        }

        public Shape getrect() {
            return rect;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TIC TAC TOE- Any Player Can Start"); // set title of application

        astage = primaryStage;
        Pane pn = new Pane(); // this is our main page kinda of

        pn.setPrefSize(600, 600);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Square square = new Square(); // make sqaures for the x and o in a 3x3 structure
                square.setTranslateX(j * 200);
                square.setTranslateY(i * 200);
                // square.rect.setId("test");
                board[i][j] = square; // keep track of the squares made
                pn.getChildren().add(square); // add each sqaure to the main page
            }
        }

        primaryStage.setScene(new Scene(pn)); // set all these elements in display

        primaryStage.show(); // now show them

    }

    private void alert(String msg, String title, String header) {
        // done alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(msg);
        alert.setContentText(header);

        alert.showAndWait();
    }

    public void checkdraw() {
        boolean checkDraw = false;// for checking draw

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].rect.getId().equals("test")) {
                    checkDraw = true; // one cell has not been filled yet
                }

            }
        }

        if (!checkDraw) {// the game is over in a draw

            if (!isGameDone) {
                alert("Game is Draw", "Game Notice", "Draw!");
            }
            isGameDone = true;
        }

    }

    public void check() {

        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0]);
            // row
            if (board[i][0].rect.getId().equals(board[i][1].rect.getId())
                    && board[i][0].rect.getId().equals(board[i][2].rect.getId())
                    && !(board[i][0].rect.getId().equals("test"))) {

                if (!isGameDone) {
                    alert("Congratulations, " + board[i][0].rect.getId() + " win the game", "Game Notice", "Wining!");
                }
                isGameDone = true;

            }

            // check diagonal \
            if (board[0][0].rect.getId().equals(board[1][1].rect.getId())
                    && board[0][0].rect.getId().equals(board[2][2].rect.getId())
                    && !(board[0][0].rect.getId().equals("test"))) {
                if (!isGameDone) {
                    alert("Congratulations, " + board[0][0].rect.getId() + " win the game", "Game Notice", "Wining!");
                }

                isGameDone = true;

            }
            // check diagonal /
            if (board[0][2].rect.getId().equals(board[1][1].rect.getId())
                    && board[0][2].rect.getId().equals(board[2][0].rect.getId())
                    && !(board[0][2].rect.getId().equals("test"))) {
                if (!isGameDone) {

                    alert("Congratulations, " + board[0][2].rect.getId() + " win the game", "Game Notice", "Wining!");
                }

                isGameDone = true;
            }

            // check colmn

            if (board[0][i].rect.getId().equals(board[1][i].rect.getId())
                    && board[0][i].rect.getId().equals(board[2][i].rect.getId())
                    && !(board[0][i].rect.getId().equals("test"))) {

                if (!isGameDone) {

                    alert("Congratulations, " + board[0][i].rect.getId() + " win the game", "Game Notice", "Wining!");
                }

                isGameDone = true;
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
