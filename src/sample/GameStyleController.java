package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStyleController {

    @FXML
    private Button hLocal;

    @FXML
    private Button hOnline;

    @FXML
    private Button computer;

    public void initialize() {
        hLocal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("hloc");
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource
                            ("hLocalConfig.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("2 Player Configuration");
                    stage.setScene(new Scene(root, 300, 300));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(Main.firstStage);
                    stage.show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        hOnline.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("honl");
            }
        });

        computer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("comp");
            }
        });
    }
}