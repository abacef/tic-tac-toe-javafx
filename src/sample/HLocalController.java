package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private void initialize() {
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
            }
        });
        group = new ToggleGroup();

        xRadio.setToggleGroup(group);
        xRadio.setUserData("x");
        xRadio.setSelected(true);

        oRadio.setToggleGroup(group);
        oRadio.setUserData("o");
    }

    public Button getOKButton() {
        return ok;
    }

    public String getPlayer1Name() {
        return player1.getText();
    }

    public String getPlayer2Name() {
        return player2.getText();
    }

    public boolean getPlayer1IsX() {
        return group.getSelectedToggle().getUserData().equals("x");
    }
}