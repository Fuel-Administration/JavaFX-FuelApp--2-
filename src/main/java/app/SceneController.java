package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void show(String name) {
        try {
            String fxmlPath = switch (name) {
                case "login" -> "/fxml/login.fxml";
                case "register" -> "/fxml/register.fxml";
                case "forgot" -> "/fxml/forgot_password.fxml";
                case "request" -> "/fxml/request_fuel.fxml";
                default -> "/fxml/login.fxml";
            };
            Parent root = FXMLLoader.load(SceneController.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(SceneController.class.getResource("/css/styles.css").toExternalForm());
            primaryStage.setTitle("FuelApp - " + name);
            primaryStage.setScene(scene);
            //primaryStage.setWidth(900);
            //primaryStage.setHeight(600);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
