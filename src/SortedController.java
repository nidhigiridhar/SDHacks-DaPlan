import finalDAPLAN.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SortedController
{
    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TableView tableTasks;

    private ObservableList observableList;

    @FXML
    void initialize() {
        setUpColumns();

        observableList = FXCollections.observableArrayList();
        for (Task t: Main.schedule.getPlan()) {
            observableList.add(t);
        }

        tableTasks.setItems(observableList);
        tableTasks.setStyle("-fx-font-size: 18px");
        colorCodeRows();
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

        tableTasks.getColumns().addAll( nameCol, lengthCol, dueDateCol);
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

    public void back(ActionEvent actionEvent) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("unsortedSchedule.fxml"));
        mainPanel.getChildren().setAll(panel);
    }

    @FXML
    public void goToAbout(ActionEvent actionEvent) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("aboutPage.fxml"));
        mainPanel.getChildren().setAll(panel);
    }


}
