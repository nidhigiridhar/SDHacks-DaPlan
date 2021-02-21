import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import finalDAPLAN.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLength;
    @FXML
    private DatePicker dpDueDate;
    @FXML
    private Slider sliderDifficulty;
    @FXML
    private Label errorToast;

    @FXML
    private TableView tableTasks;

    private ObservableList observableList;

    @FXML
    void initialize() {
        setSliderColors();

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

    private void setSliderColors() {
        sliderDifficulty.setStyle("-fx-control-inner-background: #80E58A;");
        sliderDifficulty.valueProperty().addListener((observable, oldValue, newValue) -> {
            switch ((Integer) newValue.intValue())
            {
                case 1:
                    sliderDifficulty.setStyle("-fx-control-inner-background: #80E58A;");
                    break;
                case 2:
                    sliderDifficulty.setStyle("-fx-control-inner-background: #C7E941;");
                    break;
                case 3:
                    sliderDifficulty.setStyle("-fx-control-inner-background: #F4FF74;");
                    break;
                case 4:
                    sliderDifficulty.setStyle("-fx-control-inner-background: #FF9E44;");
                    break;
                case 5:
                    sliderDifficulty.setStyle("-fx-control-inner-background: #F07070;");
                    break;
                default:
                    sliderDifficulty.setStyle("-fx-control-inner-background: #80E58A;");
            }
        });
    }

    @FXML
    void createTask(ActionEvent event)
    {
        try
        {
            if (tfName.getText() == null || tfName.getText().isEmpty())
            {
                Toast.show("Enter a task name!", errorToast);
            }
            else if (tfLength.getText() == null || tfLength.getText().isEmpty())
            {
                Toast.show("Enter a task length!", errorToast);
            }
            else if (dpDueDate.getValue() == null)
            {
                Toast.show("Choose a due date!", errorToast);
            }
            else if (dpDueDate.getValue().isBefore(LocalDate.now()))
            {
                Toast.show("Due date cannot be before today's date.", errorToast);
            }
            else
            {
                // raises exception if user didn't input a number for task length
                double lengthDouble = Double.valueOf(tfLength.getText());

                System.out.println(tfName.getText());
                System.out.println(lengthDouble);
                System.out.println(dateToString(dpDueDate.getValue()));
                System.out.println(sliderDifficulty.getValue());

                // TODO: add task to the backend task list
                observableList.add(new Task(tfName.getText(), lengthDouble, (int) sliderDifficulty.getValue(), dateToString(dpDueDate.getValue())));
                tableTasks.setItems(observableList);

                // TODO: notify ListView that tasks list has changed

                clearFields();
            }
        } catch (NumberFormatException e) {
            Toast.show("Task length must be a number.", errorToast);
        }
    }



    private String dateToString(LocalDate date) {
        String dateStr = String.valueOf(date);
        String year = dateStr.substring(2,4);
        String month = dateStr.substring(5,7);
        String day = dateStr.substring(8,10);
        return month + day + year;
    }

    private void clearFields() {
        tfName.setText("");
        tfLength.setText("");
        dpDueDate.setValue(null);
        sliderDifficulty.setValue(1);
    }

    @FXML
    void genSchedule(ActionEvent event) throws IOException {
        // TODO: call sort tasks function in backend

        // TODO: change destination fxml file to sorted schedule screen
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("addTask.fxml"));
        mainPanel.getChildren().setAll(panel);
    }

}
