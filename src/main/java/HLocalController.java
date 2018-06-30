import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HLocalController {

    @FXML
    private TextField player1;

    @FXML
    private TextField player2;

    @FXML
    private RadioButton xRadio;

    @FXML
    private RadioButton oRadio;

    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    private ToggleGroup group;

    private Alert alert = new Alert(Alert.AlertType.ERROR, "players names " +
            "must contain characters");

    @FXML
    private void initialize() {
        cancel.setOnAction(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });

        group = new ToggleGroup();

        xRadio.setToggleGroup(group);
        xRadio.setUserData("x");
        xRadio.setSelected(true);

        oRadio.setToggleGroup(group);
        oRadio.setUserData("o");

        ok.setOnAction(event -> {
            if (!(player1.getText().trim().length() > 0 || player2.getText()
                    .trim().length() > 0)) {
                alert.showAndWait();
                return;
            }
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
    }

    public String getPlayer1() {
        return player1.getText().trim();
    }

    public String getPlayer2() {
        return player2.getText().trim();
    }

    public boolean getPlayer1IsX() {
        return group.getSelectedToggle().getUserData().equals("x");
    }
}