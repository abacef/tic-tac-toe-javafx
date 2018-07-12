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

    /* the stage that the next scene will always reside on */
    private Stage stage;

    /* the stage that started it all, and plays the game */
    private Stage primaryStage;

    public static final String COMP = "Computer";

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
                stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(primaryStage);
                stage.setTitle("2 Player Local Configuration");
                Parent root = loader.load();
                stage.setScene(new Scene(root, 300, 300));
                stage.centerOnScreen();
                stage.setResizable(false);

                HLocalController controller = loader.getController();

                stage.showAndWait();

                player1 = controller.getPlayer1();
                player2 = controller.getPlayer2();

                if (player1.length() > 0 && player2.length() > 0) {
                    player1IsX = controller.getPlayer1IsX();
                    System.out.println("Local 2 player Human Game Starting!"
                            + PLAYER_1 + player1 + " - " + (player1IsX ? 'X'
                            : 'O') + PLAYER_2 + player2 + " - " + (player1IsX
                            ? 'O' : 'X'));
                    startGame();
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
                stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(primaryStage);
                stage.setTitle(ONLINE_ENTER_NAME);
                Parent root = loader.load();
                stage.setScene(new Scene(root, 300, 110));
                stage.centerOnScreen();
                stage.setResizable(false);

                OKController controller = loader.getController();

                stage.showAndWait();

                player1 = controller.getPlayer1();

                System.out.println("Networked 2 player game initialized " +
                        "by: " + player1 +"\n\tWaiting for connections...");

                loader = new FXMLLoader(getClass().getResource
                        ("fxml/hOnlineConnect.fxml"));
                stage = new Stage();
                stage.setTitle("Wait or Enter Partners Information");
                root = loader.load();
                stage.setScene(new Scene(root, 400, 305));
                stage.initOwner(primaryStage);
                new TicTacToeP2P(player1).go();


                ConnectionManager manager = new ConnectionManager
                            (primaryStage, player1);
                    // will have been connected at this point
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });

        computer.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource
                        ("fxml/hOnlineOK.fxml"));
                stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(primaryStage);
                stage.setTitle(COMP_ENTER_NAME);
                Parent root = loader.load();
                stage.setScene(new Scene(root, 300, 110));
                stage.centerOnScreen();
                stage.setResizable(false);

                OKController controller = loader.getController();

                stage.showAndWait();

                player1 = controller.getPlayer1();

                // user put nothing (or whitespace) in the text field
                if (player1.length() > 0) {
                    System.out.println("Computer 2 Player Game Initialized by: "
                            + player1);
                    player2 = COMP; // check for computer
                    player1IsX = true;
                    startGame();
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("fxml/gamePlay.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root,300, 400));
            GamePlayController controller = loader.getController();
            GameModel model = new GameModel(player1, player2, player1IsX);
            controller.setModel(model);
            primaryStage.setTitle(model.getPlayer1() + " vs " + model
                    .getPlayer2());
            primaryStage.show();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}