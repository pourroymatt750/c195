package pourroy.c195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pourroy.c195.DAO.DBConnection;


import java.io.IOException;

/**
 * Main Application that runs the program
 *
 * @author matthewpourroy
 */
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DBConnection.openConnection();
        launch();
        DBConnection.closeConnection();
    }
}