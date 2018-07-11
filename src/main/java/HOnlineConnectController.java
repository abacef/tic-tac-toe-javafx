import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;

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

    public void setInitializingPlayerName(String player) {
        yourName.setText(player);
    }

    public void setManager(ConnectionManager manager) {
        this.manager = manager;
        yourHost.setText(manager.getMyHost());
    }

    @FXML
    private void initialize() {
        cancel.setOnAction(event -> {
            DatagramPacket packet = new DatagramPacket(new byte[]
                    {'Q'}, 1, manager.getMyAddress());
            try {
                manager.getMyMailbox().send(packet);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        connect.setOnAction(event -> {
            System.out.println("Connecting... has not been implemented yet");
            if (partnerHost.getText().equals("")) {
                new Alert(Alert.AlertType.ERROR, NO_HOST_ENTERED, ButtonType
                        .OK).showAndWait();
                return;
            }

            manager.setPartnerHost(partnerHost.getText());
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
    }
}
