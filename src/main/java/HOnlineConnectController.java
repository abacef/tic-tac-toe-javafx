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
    private Label yourPort;

    @FXML
    private TextField partnerHost;

    @FXML
    private TextField partnerPort;

    @FXML
    private Label yourName;

    @FXML
    private Button cancel;

    @FXML
    private Button connect;

    private final int LOWEST_PORT = 4569;

    private final int HIGHEST_PORT = 9999;

    private final String NO_HOST_ENTERED = "Please Enter a Host";

    private final String NO_PORT_ENTERED = "Please Enter a Port";

    private final String INT_ERROR = "Your port must be a java integer " +
            "between " + LOWEST_PORT + " and " + HIGHEST_PORT;

    public void setInitializingPlayerName(String player) {
        yourName.setText(player);
    }

    public void setManager(ConnectionManager manager) {
        this.manager = manager;
        yourHost.setText(manager.getMyHost());
        yourPort.setText(Integer.toString(manager.getMyPort()));
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

            if (partnerPort.getText().equals("")) {
                new Alert(Alert.AlertType.ERROR, NO_PORT_ENTERED, ButtonType
                        .OK).showAndWait();
                return;
            }

            int partnerPortInt;
            try {
                 partnerPortInt = Integer.parseInt(partnerPort.getText());
            }
            catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR, INT_ERROR, ButtonType.OK)
                        .showAndWait();
                return;
            }

            if (partnerPortInt < LOWEST_PORT || partnerPortInt > HIGHEST_PORT) {
                new Alert(Alert.AlertType.ERROR, INT_ERROR, ButtonType.OK)
                        .showAndWait();
                return;
            }

            manager.setPartnerPort(partnerPortInt);

            manager.setPartnerHost(partnerHost.getText());
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
            System.out.println(manager);
        });
    }
}
