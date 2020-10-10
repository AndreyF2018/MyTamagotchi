//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
//import dao.DaoTama;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import models.Cat;
//import models.Tamagotchi;

//import java.time.LocalDateTime;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello");
        //LocalDateTime ld = LocalDateTime.now();
/*
        Tamagotchi cat = new Cat("test", ld);
        System.out.println(cat.getMaxAge());
        System.out.println(cat.getAge());
        cat.setAge(30);
        System.out.println(cat.getAge());
        cat.setAge(100);
        System.out.println(cat.getAge());
        System.out.println(cat.getClass().getName());
        //System.out.println(cat.getClass());
        DaoTama daoTama = new DaoTama();
        //daoTama.createTama("asd", LocalDateTime.now(), Cat.class);
        //daoTama.createCat("Vaska", LocalDateTime.now());
        //Tamagotchi tama = daoTama.getTama("Cat");
        //System.out.println(tama.toString());
        //daoTama.createTama("Cat", "tyq", LocalDateTime.now());
        //daoTama.deleteTama();
        //System.out.println(daoTama.getTama().toString());

*/




        /*
        Label helloWorldLabel = new Label("Hello, world, псина");
        helloWorldLabel.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Hello World");
        Scene primaryScene = new Scene(helloWorldLabel, 300, 275);
        primaryStage.setScene(primaryScene);

         */
        primaryStage.show();
    }


    public static void main(String[] args) {

        Application.launch(args);
    }
}
