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

    @Override
    public void start(Stage primaryStage) throws Exception{
        schedule = new listPlan();

        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        primaryStage.setTitle("Da Plan");
        Scene scene = new Scene(root, 900, 550);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
