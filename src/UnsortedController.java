import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

    @FXML
    void initialize() {
        setSliderColors();

        setUpColumns();

        observableList = FXCollections.observableArrayList();
        for (Task t: Main.schedule.getPlan()) {
            observableList.add(t);
        }

        tableTasks.setItems(observableList);
        tableTasks.setStyle("-fx-font-size: 18px");
        colorCodeRows();

        System.out.println(tableTasks.getItems());
    }

    private void setUpColumns() {
        tableTasks.getColumns().clear();


        //TableColumn doneCol = new TableColumn( "Done" );
        //doneCol.setCellValueFactory(new PropertyValueFactory<Task, Checkbox>("checked"));
        /*
        doneCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Task,Boolean>,ObservableValue<Boolean>>(){
                    @Override public
                    ObservableValue<Boolean> call( TableColumn.CellDataFeatures<Task,Boolean> p ){
                        return p.getValue().getIsCompleted();
                    }});
        doneCol.setCellFactory(
                new Callback<TableColumn<Task,Boolean>,TableCell<Task,Boolean>>(){
                    @Override public
                    TableCell<Task,Boolean> call( TableColumn<Task,Boolean> p ){
                        return new CheckBoxTableCell<>(); }});
         */


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

                Task t = new Task(tfName.getText(), lengthDouble, (int) sliderDifficulty.getValue(), dateToString(dpDueDate.getValue()));
                Main.schedule.addTask(t);
                observableList.add(t);
                tableTasks.setItems(observableList);
                Main.schedule.printList();


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

        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("sortedSchedule.fxml"));
        mainPanel.getChildren().setAll(panel);


    }

    @FXML
    public void toAbout(ActionEvent actionEvent) throws IOException
    {
        System.out.println("Button clicked");
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource("aboutPage.fxml"));
        mainPanel.getChildren().setAll(panel);
    }
}
