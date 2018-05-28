package sample;

import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class LocalConfigController {

    @FXML
    private TextField player1;

    @FXML
    private TextField player2;

    @FXML
    private RadioButton x1;

    @FXML
    private RadioButton o1;

    @FXML
    private RadioButton x2;

    @FXML
    private RadioButton o2;

    @FXML
    private void initialize() {
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        x1.setToggleGroup(group1);
        o1.setToggleGroup(group1);
        x2.setToggleGroup(group2);
        o2.setToggleGroup(group2);
        group1.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                 Toggle new_toggle) -> {
                    if (new_toggle.toString().charAt(15) == 'x' &&
                            new_toggle.toString().charAt(16) == '1') {
                        o2.setSelected(true);
                    }
                    else {
                        x2.setSelected(true);
                    }
                });
        group2.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                 Toggle new_toggle) -> {
                    if (new_toggle.toString().charAt(15) == 'x' &&
                            new_toggle.toString().charAt(16) == '2') {
                        o1.setSelected(true);
                    }
                    else {
                        x1.setSelected(true);
                    }
                });
    }
}