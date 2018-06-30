import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    private final String NO_HOST_ENTERED = "Please Enter a Host";

    private final String NO_PORT_ENTERED = "Please Enter a Port";

    private final String INT_ERROR = "Your port must be a java integer " +
            "between 4569 and 65535";

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

            try {
                manager.setPartnerPort(Integer.parseInt(partnerPort.getText()));
            }
            catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR, INT_ERROR, ButtonType.OK).showAndWait();
                return;
            }

            manager.setPartnerHost(partnerHost.getText());
            Stage stage = (Stage)cancel.getScene().getWindow();
            stage.close();
            System.out.println(manager);
        });
    }
}
