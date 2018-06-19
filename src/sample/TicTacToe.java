package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static Stage firstStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        firstStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameStyle.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 350, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Select Gameplay Method");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}