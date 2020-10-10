package controllers;

import com.github.cliftonlabs.json_simple.JsonException;
import dao.DaoInterface;
import dao.DaoTama;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Tamagotchi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

public class CreatePetController {

    private ChangeSceneController changeSceneController = new ChangeSceneController();
    @FXML
    private TextField textFieldName;
    @FXML
    private Button btn_back;

    private DaoInterface daoTama = new DaoTama();

    @FXML
    public void createTama(ActionEvent actionEvent) throws IOException, JsonException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Button pressedButton = (Button) actionEvent.getSource();
        if (textFieldName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(":)");
            alert.setHeaderText("I almost forgot!");
            alert.setContentText("You have not given a name to your pet");
            alert.show();
        }
        else {
            if (daoTama.isTamaExists()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(":)");
                alert.setHeaderText("How many?");
                alert.setContentText("You already have a pet");
                alert.show();

             }
            // If less than 1 hour has passed since the death of pet
            else if (daoTama.getTimeAfterDeath() != null && daoTama.getTimeAfterDeath().toHours()< 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(":(");
                alert.setHeaderText("Not so fast!");
                alert.setContentText("You can create a pet only 1 hour after the previous one left");
                alert.show();
            }
            else if (daoTama.isPossibleCreateTama()) {
                daoTama.createTama(pressedButton.getText(), textFieldName.getText(), LocalDateTime.now());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(":)");
                alert.setHeaderText("Congratulations!!");
                alert.setContentText("You have created your pet! Don't forget to look after him!");
                alert.show();

            }
        }

    }

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
        changeSceneController.changeScene(stage, "Main.fxml");
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Main.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
        */
    }

}
