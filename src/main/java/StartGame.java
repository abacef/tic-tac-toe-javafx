import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class StartGame {

    private String player1;

    private String player2;

    private final String USED_ADDRESS = "Address already in use";

    public StartGame(boolean isNetworked, String player1, String player2) {
        this.player1 = player1;
        if (isNetworked) {
            setupNetworkedGame(player1);
        }
        else {
            this.player2 = player2;
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
            stage.setScene(new Scene(root, 400, 305));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(Main.firstStage);
            stage.centerOnScreen();

            HOnlineConnectController controller = loader.getController();
            controller.setInitializingPlayerName(player1);

            // Set up UDP connection
            ConnectionManager manager = new ConnectionManager();
            manager.setMyHost(InetAddress.getLocalHost().getHostName());
            manager.setMyPort(ConnectionManager.STARTING_PORT);
            controller.setManager(manager);
            DatagramSocket mailbox = null;
            boolean success = false;

            while (!success) {
                try {
                    mailbox = new DatagramSocket(new InetSocketAddress
                            (manager.getMyHost(), manager.getMyPort()));
                    success = true;
                }
                catch (SocketException se) {
                    manager.setMyPort(manager.getMyPort() + 1);
                }
            }
            Thread reader = new Thread(new UDPReaderThread(mailbox, manager));
            reader.start();

            stage.showAndWait();

            if (stage.getTitle().equals("X")) {
                // TODO connection succeeded?
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
