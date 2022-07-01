import entity.Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.io.IOException;

import static javafx.application.Application.launch;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //Session session = FactoryConfiguration.getInstance().getSession();
    //Room room = new Room();




    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("Hibernate CW");
        primaryStage.show();

    }
}
