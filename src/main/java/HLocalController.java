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

    private final Alert player1EmptyAlert = new Alert(Alert.AlertType.ERROR,
            "Please enter a name for player 1",
            ButtonType.OK);

    private final Alert player2EmptyAlert = new Alert(Alert.AlertType.ERROR,
            "Please enter a name for player 2",
            ButtonType.OK);

    private GameStyleController parent;

    @FXML
    private void initialize() {
        cancel.setOnAction(event -> {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.setTitle("X");
            stage.close();
        });

        group = new ToggleGroup();

        xRadio.setToggleGroup(group);
        xRadio.setUserData("x");
        xRadio.setSelected(true);

        oRadio.setToggleGroup(group);
        oRadio.setUserData("o");

        ok.setOnAction(event -> {
            if (player1.getText().equals("")) {
                player1EmptyAlert.showAndWait();
                return;
            }

            if (player2.getText().equals("")) {
                player2EmptyAlert.showAndWait();
                return;
            }

            Stage stage = (Stage)ok.getScene().getWindow();
            stage.close();
        });
    }

    public String getPlayer1() {
        return player1.getText();
    }

    public String getPlayer2() {
        return player2.getText();
    }

    public boolean getPlayer1IsX() {
        return group.getSelectedToggle().getUserData().equals("x");
    }
}