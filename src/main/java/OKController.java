import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OKController {

    @FXML
    private TextField name;

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    @FXML
    private void initialize() {
        cancel.setOnAction(event -> {
            Stage stage = (Stage)cancel.getScene().getWindow();
            stage.close();
        });

        ok.setOnAction(event -> {
            if (!(name.getText().trim().length() > 0)) {
                new Alert(Alert.AlertType.ERROR, "Your name must " +
                        "contain" + "characters", ButtonType.OK).showAndWait();
                return;
            }
            Stage stage = (Stage)cancel.getScene().getWindow();
            stage.close();
        });
    }

    public String getPlayer1() {
        return name.getText().trim();
    }
}
