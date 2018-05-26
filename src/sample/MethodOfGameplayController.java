package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MethodOfGameplayController {

    @FXML
    private Button hLocal;

    @FXML
    private Button hOnline;

    @FXML
    private Button computer;

    public void initiliaze() {
        /*
        hLocal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello");
            }
        });
         */
        hLocal.setOnAction((event) -> {
            System.out.println("Hello");
        });
    }
}
