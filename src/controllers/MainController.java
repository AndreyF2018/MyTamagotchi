package controllers;

import com.github.cliftonlabs.json_simple.JsonException;
import dao.DaoInterface;
import dao.DaoTama;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Tamagotchi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainController {

    private DaoInterface daoTama = new DaoTama();
    private ChangeSceneController changeSceneController = new ChangeSceneController();
    @FXML
    private Button btn_start;
    @FXML
    private Button btn_create;

    @FXML
    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }

    // Sets the pet's appearance depending on his type and age
    public <T extends Tamagotchi> Image setPetAppearance (T tamagotchi){
        StringBuilder URL = new StringBuilder("/images/");
        String className = tamagotchi.getClass().getSimpleName();
        String petAge = "";
        if (tamagotchi.getAge() < tamagotchi.getMaxAge() / 2) {
            petAge = "little";
        }
        else {
            petAge = "adults";
        }
        URL.append(className + "s/" + petAge + "/" + className + ".gif");
        Image image = new Image(URL.toString());
        return image;
    }
    // Go to the pet control window:
    @FXML
    public void goToPet(ActionEvent actionEvent) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, JsonException, NoSuchMethodException, ClassNotFoundException {
        if (daoTama.isTamaExists()) {
            Tamagotchi tamagotchi = daoTama.getTama(); // Getting pet data from json file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PetView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            // Checking pet status before opening a window:
            PetController petController = fxmlLoader.getController();
            petController.setPetImage(setPetAppearance(tamagotchi)); // Setting image for a pet
            petController.petStatusCheck(tamagotchi);
            // Go to the window with the pet if he is alive
            if (!daoTama.isTamaDied()) {
                Stage stage = (Stage) btn_start.getScene().getWindow();
                stage.close();
                changeSceneController.changeScene(root, stage, 1500, 1000);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(":)");
            alert.setHeaderText("Hi!");
            alert.setContentText("You don't have a pet yet, create one right now!");
            alert.show();
        }
    }
    // Go to the pet creation window by pressing the button:
    @FXML
    public void goToCreate(ActionEvent actionEvent) throws  IOException {
        Stage stage = (Stage) btn_create.getScene().getWindow();
        stage.close();
        changeSceneController.changeScene(stage, "CreatePetView.fxml", 710, 501);
    }
}
