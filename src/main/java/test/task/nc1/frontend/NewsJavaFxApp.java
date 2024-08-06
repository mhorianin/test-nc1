package test.task.nc1.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewsJavaFxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("News");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
