import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import finalDAPLAN.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class UnsortedController {

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

    /**
     * Method called when the layout is first created.
     * Sets up the color coding by difficulty, TableView rows and columns, and ObservableList.
     */
    @FXML
    void initialize() {
        setSliderColors();

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
     * Configures the slider so that it is color-coded by difficulty.
     * The slider should change color whenever it is moved to a different value.
     */
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

    /**
     * Event listener for when the user is finished creating a new task.
     * Handles erroneous inputs such as empty fields, due dates before the current date,
     * and hours exceeding the number of available time in a day.
     * If all inputs are valid, adds a task to the backend list of tasks and clears the field.
     * The newly created task is immediately displayed onto the task TableView.
     * @param event         standard event listener parameter
     */
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

                // creates the new Task from the input attributes and adds it to the backend
                Task t = new Task(tfName.getText(), lengthDouble, (int) sliderDifficulty.getValue(), dateToString(dpDueDate.getValue()));
                Main.schedule.addTask(t);
                observableList.add(t);

                // gets the current time and time at the end of the day
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String dateStart = new SimpleDateFormat("HH:mm:ss").format(new Date());
                String dateStop = "23:59:59";
                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = format.parse(dateStart);
                    d2 = format.parse(dateStop);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Get msec from each, subtract both times to get difference in hours
                long diff = d2.getTime() - d1.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long differenceInHours = diff / (60 * 60 * 1000);

                // number of hours cannot exceed time between now and the end of the day
                if (Main.schedule.totalTime() > differenceInHours)
                {
                    Main.schedule.removeElement(t);
                    observableList.remove(observableList.size() - 1);
                    Toast.show("Task length exceeds amount of time", errorToast);
                }

                // renders the task list onto the UI TableView
                tableTasks.setItems(observableList);

                // resets input fields
                clearFields();
            }
        } catch (NumberFormatException e) {
            Toast.show("Task length must be a number.", errorToast);
        }
    }

    /**
     * Returns a String representing a LocalDate object, in the form MMDDYYYY
     * @param date      a LocalDate object taken from DatePicker
     * @return          the formatted String version of the LocalDate object
     */
    private String dateToString(LocalDate date) {
        String dateStr = String.valueOf(date);
        String year = dateStr.substring(2,4);
        String month = dateStr.substring(5,7);
        String day = dateStr.substring(8,10);
        return month + day + year;
    }

    /**
     * Resets the input fields of the New Task form, after the user has submitted a new task.
     */
    private void clearFields() {
        tfName.setText("");
        tfLength.setText("");
        dpDueDate.setValue(null);
        sliderDifficulty.setValue(1);
    }

    /**
     * Sorts the list of tasks stored in the backend with a complex algorithm.
     * This method should be executed after the user has finished adding tasks.
     * Navigates to the Sorted Schedule Page of the app, where the user will then see this sorted list.
     * @param event         standard event listener parameter
     * @throws IOException  thrown by FXMLLoader.load()
     */
    @FXML
    void genSchedule(ActionEvent event) throws IOException {

        Main.schedule.sort();
        Main.schedule.listDueToday();
        Main.schedule.buildFinalList();

        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("sortedSchedule.fxml"));
        mainPanel.getChildren().setAll(panel);
    }

    /**
     * Navigates to the About Page of the app.
     * @param actionEvent         standard event listener parameter
     * @throws IOException        thrown by FXMLLoader.load()
     */
    @FXML
    public void toAbout(ActionEvent actionEvent) throws IOException
    {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("aboutPage.fxml"));
        mainPanel.getChildren().setAll(panel);
    }
}
