import finalDAPLAN.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;



public class ScheduleController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TableView tableTasks;

    private ObservableList observableList;

    @FXML
    void initialize() {

        setUpColumns();

        observableList = FXCollections.observableArrayList(
                new Task("Study",  3, 5, "022321"),
                new Task("Laundry", 4, 1, "022321")
        );

        tableTasks.setItems(observableList);
        tableTasks.setStyle("-fx-font-size: 18px");
        colorCodeRows();

        System.out.println(tableTasks.getItems());
    }

    private void setUpColumns() {
        tableTasks.getColumns().clear();


        TableColumn nameCol = new TableColumn("Task Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        nameCol.setMinWidth(200);

        TableColumn lengthCol = new TableColumn("Time Needed");
        lengthCol.setCellValueFactory(new PropertyValueFactory<Task, String>("lengthAsString"));
        lengthCol.setMinWidth(150);

        TableColumn dueDateCol = new TableColumn("Due Date");
        dueDateCol.setCellValueFactory(new PropertyValueFactory<Task, String>("dueDateFormatted"));
        dueDateCol.setMinWidth(150);

        tableTasks.getColumns().addAll(nameCol, lengthCol, dueDateCol);
    }

    private void colorCodeRows() {
        tableTasks.setRowFactory(tv -> new TableRow<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || item.getTaskName() == null) {
                    setStyle("");
                    return;
                }

                switch (item.getLevelOfDifficulty())
                {
                    case 1:
                        setStyle("-fx-background-color: #80E58A;");
                        break;
                    case 2:
                        setStyle("-fx-background-color: #C7E941;");
                        break;
                    case 3:
                        setStyle("-fx-background-color: #F4FF74;");
                        break;
                    case 4:
                        setStyle("-fx-background-color: #FF9E44;");
                        break;
                    case 5:
                        setStyle("-fx-background-color: #F07070;");
                        break;
                    default:
                        setStyle("");
                }
            }
        });
    }
}
