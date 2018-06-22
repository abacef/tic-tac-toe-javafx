import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HOnlineOKController {

    @FXML
    private TextField name;

    @FXML
    private Button ok;

    @FXML
    private Button cancel;

    @FXML
    private void initialize() {
        cancel.setOnAction(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        ok.setOnAction(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.setTitle("X");
            stage.close();
        });
    }

    public String getPlayer1() {
        return name.getText();
    }
}