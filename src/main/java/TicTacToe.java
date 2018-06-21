import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static Stage firstStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        firstStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/gameStyle.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("img/logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Select Gameplay Method");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}