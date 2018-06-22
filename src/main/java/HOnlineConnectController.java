import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HOnlineConnectController {

    private GameStyleController parent;

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
            try {
                manager.setPartnerPort(Integer.parseInt(partnerPort.getText()));
            }
            catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR, INT_ERROR, ButtonType.OK).showAndWait();
            }
            manager.setPartnerHost(partnerHost.getText());
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.setTitle("X");
            stage.close();
            manager.iGotYouFirst();
        });
    }
}
