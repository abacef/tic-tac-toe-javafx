import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStyleController {

    /* The button to select a human local 2 player game */
    @FXML
    private Button hLocal;

    /* The button to select an online game with another human */
    @FXML
    private Button hOnline;

    /* The button to select a local game with an AI player */
    @FXML
    private Button computer;

    private String player1;

    private String player2;

    private boolean player1IsX;

    public static String PLAYER_1 = "\nPlayer 1: ";

    public static String PLAYER_2 = "\nPlayer 2: ";

    @FXML
    private void initialize() {
        hLocal.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource
                            ("fxml/hLocal.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("2 Player Configuration");
                    Parent root = loader.load();
                    stage.setScene(new Scene(root, 300, 300));
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
            public void handle(MouseEvent event) {
                System.out.println("honl");
            }
        });

        computer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("comp");
            }
        });
    }

    public void startLocalGame(String player1, String player2, boolean
            player1IsX) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1IsX = player1IsX;
        System.out.println("Local 2 player Human Game Starting!" +
                PLAYER_1 + player1 + " - " + (player1IsX ? 'X' : 'O') +
                PLAYER_2 + player2 + " - " + (player1IsX ? 'O' : 'X'));
    }

    public void startComputerGame() {

    }

    public void startNetworkedGame() {

    }
}