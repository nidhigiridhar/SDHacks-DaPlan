import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Da Plan");
        primaryStage.setScene(new Scene(root, 900, 500));

        Task t = new Task("Study for finals", (long) 2.0, 10, "2/23/2021");
        System.out.println(t);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
