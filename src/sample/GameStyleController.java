package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStyleController {

    @FXML
    private Button hLocal;

    @FXML
    private Button hOnline;

    @FXML
    private Button computer;

    private String player1;

    private String player2;

    private boolean player1IsX;

    @FXML
    private void initialize() {
        hLocal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hloc");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource
                            ("hLocal.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("2 Player Configuration");
                    stage.setScene(new Scene(loader.load(), 300, 300));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(TicTacToe.firstStage);
                    stage.centerOnScreen();

                    HLocalController controller = loader.getController();
                    controller.setParent(GameStyleController.this);

                    stage.showAndWait();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        hOnline.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("honl");
            }
        });

        computer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("comp");
            }
        });
    }

    public void startGame(String player1, String player2, boolean player1IsX) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1IsX = player1IsX;
    }
}