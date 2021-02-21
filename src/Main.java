import finalDAPLAN.Task;
import finalDAPLAN.listPlan;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static listPlan schedule;

    /**
     * Called when the desktop application first runs
     * Initializes the backend list.
     * Sets up the stage and scene for the Welcome/Landing page.
     * @param primaryStage      the screen that the application first displays
     * @throws Exception        thrown by FXMLLoader.load()
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        schedule = new listPlan();

        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        primaryStage.setTitle("Da Plan");
        Scene scene = new Scene(root, 900, 550);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Runs the desktop application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
