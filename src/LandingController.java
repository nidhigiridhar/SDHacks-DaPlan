import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LandingController
{
    @FXML
    private AnchorPane welcomePage;

    @FXML
    void runProgram(ActionEvent event) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        welcomePage.getChildren().setAll(panel);
    }


}

