package controllers;

import com.github.cliftonlabs.json_simple.JsonException;
import dao.DaoInterface;
import dao.DaoTama;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Tamagotchi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

public class PetController {

    private DaoInterface daoTama = new DaoTama();
    private ChangeSceneController changeSceneController = new ChangeSceneController();
    @FXML
    private Button btn_back;
    @FXML
    private TextField textField_food;
    @FXML
    private TextField textField_age;
    @FXML
    private TextField textField_mood;
    @FXML
    private TextField textField_health;
    @FXML
    private ImageView imageView_petImage;



    public void setPetImage(Image image){

        imageView_petImage.setImage(image);
    }

    // Method for checking and updating pet status
    public <T extends Tamagotchi> void petStatusCheck (T tamagotchi) throws IOException {
        // If the pet has lived all its years
        if (tamagotchi.getAge() > tamagotchi.getMaxAge()){
            daoTama.deleteTama();
            tamagotchi.delete();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("F");
            alert.setHeaderText("F!");
            alert.setContentText("You took good care of " + tamagotchi.getName() + " and he lived a decent life, but his time has come. F!");
            alert.show();
        }
        // If the pet is out of health and mood
        else if (tamagotchi.getHealth() < tamagotchi.getMinHealth()) {
            daoTama.deleteTama();
            tamagotchi.delete();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(":(");
            alert.setHeaderText("Well, where have you been?");
            alert.setContentText("You haven't checked your pet for a long time. He took offense and left you.");
            alert.show();

        }
        // If the pet is only out of mood
        else if (tamagotchi.getMood() < tamagotchi.getMinMood()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(":|");
            alert.setHeaderText("Hurry up!");
            alert.setContentText("You did not feed your pet for a long time, and he became sad. Feed him now!");
            alert.show();
        }
        daoTama.updateTamaState(tamagotchi.getClass().getSimpleName(), tamagotchi); // update status pet
        // Displays pet characteristics from 0 to 100:
        int displayedMood = tamagotchi.getMood() * 100 / tamagotchi.getMaxMood();
        int displayedHealth = tamagotchi.getHealth() * 100 / tamagotchi.getMaxHealth();
        textField_age.setText(Integer.toString(tamagotchi.getAge()) + " " + "(days)");
        textField_health.setText(Integer.toString(displayedHealth));
        textField_mood.setText(Integer.toString(displayedMood));
        textField_food.setText(tamagotchi.getFeed());


    }

    // Feeding pet by pressing the button
    @FXML
    public void toFeed(ActionEvent actionEvent) throws IllegalAccessException, InvocationTargetException, JsonException, InstantiationException, IOException, NoSuchMethodException, ClassNotFoundException {
        Tamagotchi tamagotchi = daoTama.getTama();
        tamagotchi.toFeed(LocalDateTime.now());
        textField_health.setText(Integer.toString(tamagotchi.getHealth()));
        textField_mood.setText(Integer.toString(tamagotchi.getMood()));
        daoTama.updateTamaState(tamagotchi.getClass().getSimpleName(), tamagotchi);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(":)");
        alert.setHeaderText("Om, Yum, yum!");
        alert.setContentText(tamagotchi.getName() + " " + "is very grateful to you!");
        alert.show();
        petStatusCheck(tamagotchi);
    }

    // Updating pet stats by pressing the button
    @FXML
    public void toUpdPetStatus(ActionEvent actionEvent) throws IllegalAccessException, InvocationTargetException, JsonException, InstantiationException, IOException, NoSuchMethodException, ClassNotFoundException {
        Tamagotchi tamagotchi = daoTama.getTama();
        petStatusCheck(tamagotchi);
    }

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
        changeSceneController.changeScene(stage, "Main.fxml");
    }

    // Method for setting animation of the cyclic movement of the pet
    public void setAnimation(){
        TranslateTransition trans = new TranslateTransition(Duration.seconds(10), imageView_petImage);
        final int[] petX = {1}; // scaling factor
        final int[] fromX = {-750};
        final int[] toX = {750};
        trans.setFromX(fromX[0]);
        trans.setToX(toX[0]);
        trans.play(); // play animation
        // Method that starts when animation ends:
        trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Changing the scaling factor and coordinates of the beginning and end of movement to the opposite:
                petX[0] = petX[0] * -1;
                fromX[0] = fromX[0] * -1;
                toX[0] = toX[0] * -1;
                imageView_petImage.setScaleX(petX[0]); // "Image mirroring"
                trans.setFromX(fromX[0]);
                trans.setToX(toX[0]);
                trans.play(); // play animation again
            }
        });
    }
    @FXML
    public void initialize()  {
        // Setting of animation of the cyclic movement of the pet:
        setAnimation();

    }
}
