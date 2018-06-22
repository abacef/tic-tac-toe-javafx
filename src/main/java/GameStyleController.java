import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private final String PLAYER_1 = "\nPlayer 1: ";

    private final String PLAYER_2 = "\nPlayer 2: ";

    private static final String COMP_ENTER_NAME = "Enter Your Name";

    private static final String ONLINE_ENTER_NAME = "Enter Your Name to Start" +
            " Connection";

    @FXML
    private void initialize() {
        hLocal.setOnAction(event -> {
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

                stage.showAndWait();

                // signifying the cancel button was not pressed (sssh! it works)
                if (stage.getTitle().equals("X")) {
                    player1 = controller.getPlayer1();
                    player2 = controller.getPlayer2();
                    player1IsX = controller.getPlayer1IsX();
                    System.out.println("Local 2 player Human Game Starting!" +
                            PLAYER_1 + player1 + " - " + (player1IsX ? 'X' : 'O') +
                            PLAYER_2 + player2 + " - " + (player1IsX ? 'O' : 'X'));
                    StartGame game = new StartGame(false, player1, player2);
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });

        hOnline.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource
                        ("fxml/hOnlineOK.fxml"));
                Stage stage = new Stage();
                stage.setTitle(ONLINE_ENTER_NAME);
                Parent root = loader.load();
                stage.setScene(new Scene(root, 300, 110));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(TicTacToe.firstStage);
                stage.centerOnScreen();
                stage.setResizable(false);

                HOnlineOKController controller = loader.getController();

                stage.showAndWait();

                // signifying the cancel button was not pressed (sssh! it works)
                if (stage.getTitle().equals("X")) {
                    player1 = controller.getPlayer1();
                    System.out.println("Networked 2 player game initialized " +
                            "by: " + player1 +"\n\tWaiting for connections...");
                    StartGame game = new StartGame(true, player1, null);
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });

        computer.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource
                        ("fxml/hOnlineOK.fxml"));
                Stage stage = new Stage();
                stage.setTitle(COMP_ENTER_NAME);
                Parent root = loader.load();
                stage.setScene(new Scene(root, 300, 110));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(TicTacToe.firstStage);
                stage.centerOnScreen();
                stage.setResizable(false);

                HOnlineOKController controller = loader.getController();

                stage.showAndWait();

                // signifying the cancel button was not pressed (sssh! it works)
                if (stage.getTitle().equals("X")) {
                    player1 = controller.getPlayer1();

                    System.out.println("Computer 2 Player Game Initialized by: "
                            + player1);
                    StartGame game = new StartGame(false, player1, null);
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
    }

    public void startNetworkedGameNumber1(String player1) {
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("fxml/hOnlineConnect.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Wait or Enter Partners Information");
            Parent root = loader.load();
            stage.setScene(new Scene(root, 300, 305));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(TicTacToe.firstStage);
            stage.centerOnScreen();

            HOnlineConnectController controller = loader.getController();
            controller.setParent(GameStyleController.this);
            controller.setInitializingPlayerName(player1);

            stage.showAndWait();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        */
    }
}