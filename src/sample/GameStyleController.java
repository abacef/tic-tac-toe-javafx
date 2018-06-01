package sample;

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

    private final Alert emptyNameAlert = new Alert(Alert.AlertType.ERROR,
            "You put in too long of a name", ButtonType.OK);

    public void initialize() {
        hLocal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("hloc");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource
                            ("hLocal.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("2 Player Configuration");
                    stage.setScene(new Scene(loader.load(), 300, 300));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(Main.firstStage);
                    stage.show();
                    HLocalController lcc = loader.getController();
                    lcc.getOKButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            player1 = lcc.getPlayer1Name();
                            if (player1.equals("")) {
                                new Alert(Alert.AlertType.ERROR,
                                        "Please enter a name for player 1",
                                        ButtonType.OK).showAndWait();
                                return;
                            }
                            player2 = lcc.getPlayer2Name();
                            if (player2.equals("")) {
                                new Alert(Alert.AlertType.ERROR,
                                        "Please enter a name for player 2",
                                        ButtonType.OK).showAndWait();
                                return;
                            }
                            player1IsX = lcc.getPlayer1IsX();

                            stage.close();
                        }
                    });
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
}