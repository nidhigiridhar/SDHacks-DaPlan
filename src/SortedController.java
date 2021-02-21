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

    /**
     * Method called when the layout is first created.
     * Sets up the color coding by difficulty, TableView rows and columns, and ObservableList.
     * Adds tasks from the backend list into the list that will be displayed in the table.
     */
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

    /**
     * Sets up the TableView column names, widths, and values to represent Task attributes.
     * Inserts a placeholder if there are no rows in the table.
     */
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

    /**
     * Configures the colors of rows. The colors correspond to the difficulty of the tasks in each row.
     */
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

    /**
     * Checks off the task that was clicked, removing it from the list of tasks.
     * @param task      the task that whose row checkbox was clicked
     */
    private void checkOffTask(Task task) {
        System.out.println("checked");
        for (int i = 0; i < observableList.size(); i++) {
            if (observableList.get(i) == task) {
                observableList.remove(i);
            }

        }
    }

    /**
     * Navigates back to the Unsorted Schedule page of the app.
     * @param actionEvent         Standard event listener parameter
     * @throws IOException        thrown by FXMLLoader.load()
     */
    public void back(ActionEvent actionEvent) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("unsortedSchedule.fxml"));
        mainPanel.getChildren().setAll(panel);
    }

    /**
     * Navigates to the About Page of the app.
     * @param actionEvent         Standard event listener parameter
     * @throws IOException        thrown by FXMLLoader.load()
     */
    @FXML
    public void goToAbout(ActionEvent actionEvent) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("aboutPage.fxml"));
        mainPanel.getChildren().setAll(panel);
    }


}
