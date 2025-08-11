package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneController.init(stage);
        SceneController.show("login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
