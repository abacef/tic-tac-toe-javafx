import ip_address.GetHost;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class HOnlineConnectController {

    private ConnectionManager manager;

    @FXML
    private Label yourHost;

    @FXML
    private TextField partnerHost;

    @FXML
    private Label yourName;

    @FXML
    private Button cancel;

    @FXML
    private Button connect;

    private final String NO_HOST_ENTERED = "Please Enter a Host";

    private final String NO_LOCALHOST = "Select 'hLocal' in the main menu in " +
            "order to play against yourself";

    public void setInitializingPlayerName(String player) {
        yourName.setText(player);
    }

    public void setManager(ConnectionManager manager) {
        this.manager = manager;
        yourHost.setText(manager.getMyHost());
    }

    @FXML
    private void initialize() {
        yourHost.setText(TicTacToeP2P.LOCALHOST);
        cancel.setOnAction(event -> {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(TicTacToeP2P.LOCALHOST,
                        TicTacToeP2P.PORT_80));
                socket.setTcpNoDelay(true);
                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());
                out.writeByte('Q');
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        connect.setOnAction(event -> {
            System.out.println("Connecting... has not been implemented yet");
            String partnerHostGotten = partnerHost.getText().trim();
            if (partnerHostGotten.equals("")) {
                new Alert(Alert.AlertType.ERROR, NO_HOST_ENTERED, ButtonType
                        .OK).showAndWait();
                return;
            }

            if (partnerHostGotten.equals(TicTacToeP2P.LOCALHOST) ||
                    partnerHostGotten.equals(yourHost.getText())) {
                new Alert(Alert.AlertType.ERROR, NO_LOCALHOST, ButtonType
                        .OK).showAndWait();
                return;
            }

            new TicTacToeP2P(partnerHostGotten, true).run();
            /*
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(TicTacToeP2P.LOCALHOST,
                        TicTacToeP2P.PORT_80));
                socket.setTcpNoDelay(true);
                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());
                out.writeByte('C');
                out.writeUTF(partnerHostGotten);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            */

            manager.setPartnerHost(partnerHost.getText());
            Stage stage = (Stage)cancel.getScene().getWindow();
            stage.close();
        });
    }
}
