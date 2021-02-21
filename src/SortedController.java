import finalDAPLAN.Task;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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
        for (Task t: Main.schedule.getFinalPlan()) {
            observableList.add(t);
        }

        tableTasks.setItems(observableList);
        tableTasks.setStyle("-fx-font-size: 18px");
        colorCodeRows();
    }

    private void setUpColumns() {
        tableTasks.getColumns().clear();
        tableTasks.setPlaceholder(new Label("No tasks yet :)"));

        TableColumn doneCol = new TableColumn( "Done" );
        doneCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Task, Boolean> param) {
                Task task = param.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(task.getIsCompleted());

                booleanProp.addListener((observable, oldValue, newValue) -> {
                    System.out.println("creatinglistener");
                    task.setIsCompleted();
                    checkOffTask(task);
                });
                return booleanProp;
            }
        });

        doneCol.setCellFactory(new Callback<TableColumn<Task, Boolean>, //
                TableCell<Task, Boolean>>() {
            @Override
            public TableCell<Task, Boolean> call(TableColumn<Task, Boolean> p) {
                CheckBoxTableCell<Task, Boolean> cell = new CheckBoxTableCell<Task, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });



        TableColumn nameCol = new TableColumn("Task Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        nameCol.setMinWidth(200);

        TableColumn lengthCol = new TableColumn("Time Needed");
        lengthCol.setCellValueFactory(new PropertyValueFactory<Task, String>("lengthAsString"));
        lengthCol.setMinWidth(150);

        TableColumn dueDateCol = new TableColumn("Due Date");
        dueDateCol.setCellValueFactory(new PropertyValueFactory<Task, String>("dueDateFormatted"));
        dueDateCol.setMinWidth(150);

        tableTasks.getColumns().addAll(doneCol, nameCol, lengthCol, dueDateCol);
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

    private void checkOffTask(Task task) {
        System.out.println("checked");
        for (int i = 0; i < observableList.size(); i++) {
            if (observableList.get(i) == task) {
                observableList.remove(i);
            }

        }
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
