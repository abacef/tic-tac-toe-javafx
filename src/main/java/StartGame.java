import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGame {

    private String player1;

    private String player2;

    private int startingPort = 4567;

    public StartGame(boolean isNetworked, String player1, String player2) {
        if (isNetworked) {
            this.player1 = player1;
            setupNetworkedGame(player1);
        }

    }

    /**
     * What we gunna do
     *
     * - player 1 starts up and clicks networked and has already entered their
     * name. A UDP connection starts up and waits for incoming packets
     * - player 2 starts up and clicks networked and has already entered their
     * name. A UDP connection starts up and waits for incoming packets
     * - player 1 enters valid crudentials for another player and presses the
     * ok button. A UDP packet is sent to that player with player1's host and
     * port info
     *      if player 1 recieves a response from player 2, that means a TCP
     *      connection will be established on player 2's end. On player 1's
     *      end, a TCP connection will be established also and things happen
     *
     *      else an error message comes up with the message of the error thrown
     *
     * if the person closes with valid info, which means a request packet has
     * been sent to the other person and they have recieved it, and sent back
     * their info, then a tcp connection will be established
     *
     *
     *
     * @param name
     */
    private void setupNetworkedGame(String name) {
        // set up UI
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
            stage.setResizable(false);

            HOnlineConnectController controller = loader.getController();
            controller.setInitializingPlayerName(player1);

            stage.showAndWait();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
