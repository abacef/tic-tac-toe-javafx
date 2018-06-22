import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HOnlineConnectController {

    private GameStyleController parent;

    private String player1;

    @FXML
    private Label yourName;

    @FXML
    private Button cancel;

    @FXML
    private Button connect;

    public void setParent(GameStyleController parent) {
        this.parent = parent;
    }

    public void setInitializingPlayerName(String player) {
        this.player1 = player;
        yourName.setText(player1);
    }

    @FXML
    private void initialize() {
        cancel.setOnAction(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        connect.setOnAction(event -> {
            System.out.println("Connecting... has not been implemented yet");
        });
    }
}
