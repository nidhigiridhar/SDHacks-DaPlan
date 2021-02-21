import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AboutController
{
    @FXML
    private AnchorPane aboutPage;

    @FXML
    void goBack(ActionEvent event) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("WelcomePage.fxml"));
        aboutPage.getChildren().setAll(panel);
    }


}

