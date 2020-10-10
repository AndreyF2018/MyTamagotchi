package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
// Controller that realizing scene transition methods
public class ChangeSceneController {

    public void changeScene(Stage stage, String sceneName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + sceneName));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void changeScene(Stage stage, String sceneName, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + sceneName));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();

    }
    public void changeScene(Parent root, Stage stage, int width, int height) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();

    }

}
