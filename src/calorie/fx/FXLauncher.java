package calorie.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FXLauncher extends Application {
    @Override
    public void start(Stage stage){
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(FXLauncher.class.getResource("launcher.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
