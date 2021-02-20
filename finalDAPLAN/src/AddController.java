import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddController
{
    @FXML
    private AnchorPane addPanel;

    @FXML
    private Button btnFinish;

    @FXML
    void createTask(ActionEvent event) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        addPanel.getChildren().setAll(panel);
    }

}
