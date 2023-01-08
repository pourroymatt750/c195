package pourroy.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pourroy.c195.DAO.*;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the Add Appointment Page that provides functionality for the application
 *
 * @author matthewpourroy
 */
public class AddAppointmentController {
    /**
     * INT(10) Appointment ID
     * Primary Key
     */
    @FXML
    private TextField appointment_id;

    /**
     * VARCHAR(50) Title
     */
    @FXML
    private TextField title;

    /**
     * VARCHAR(50) Description
     */
    @FXML
    private TextField description;

    /**
     * VARCHAR(50) Appointment Location
     */
    @FXML
    private TextField appointment_location;

    /**
     * VARCHAR(50) Contact Name
     */
    @FXML
    private ComboBox<Contacts> contact_name;

    /**
     * VARCHAR(50) Type
     */
    @FXML
    private TextField type;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private ComboBox<LocalTime> startTime;

    @FXML
    private ComboBox<LocalTime> endTime;

    /**
     * INT(10) Customer ID
     */
    @FXML
    private TextField customer_id;

    /**
     * INT(10) User ID
     */
    @FXML
    private TextField user_id;

    /**
     * Adds new appointment to the database and displays on the Main Page
     *
     * @param actionEvent Add button action
     * @throws IOException from FXMLLoader
     */
    public void onAddBtn(ActionEvent actionEvent) throws IOException, SQLException {
        String apptTitle = title.getText();
        String apptDescript = description.getText();
        String apptLocation = appointment_location.getText();
        Contacts apptContact = contact_name.getValue();
        String apptType = type.getText();
        LocalDateTime apptStart = LocalDateTime.of(startDate.getValue(), startTime.getValue());
        LocalDateTime apptEnd = LocalDateTime.of(endDate.getValue(), endTime.getValue());
        int apptCustId = Integer.parseInt(customer_id.getText());
        int apptUserId = Integer.parseInt(user_id.getText());

        LocalDate apptStartDate = startDate.getValue();
        LocalTime apptStartTime = startTime.getValue();
        LocalDate appEndDate = endDate.getValue();
        LocalTime appEndTime = endTime.getValue();

        LocalTime startBizHrs = LocalTime.of(8, 0);
        LocalTime endBizHrs = LocalTime.of(22, 0);
        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZoneId userZoneId = ZoneId.of(String.valueOf(ZoneId.systemDefault()));

        ZonedDateTime startBizHrsEst = ZonedDateTime.of(apptStartDate, startBizHrs, estZoneId);
        ZonedDateTime endBizHrsEst = ZonedDateTime.of(appEndDate, endBizHrs, estZoneId);

        ZonedDateTime startAppt = ZonedDateTime.of(apptStartDate, apptStartTime, userZoneId);
        ZonedDateTime endAppt = ZonedDateTime.of(appEndDate, appEndTime, userZoneId);


        if (apptTitle == null || apptDescript == null || apptLocation == null || apptContact == null
                || apptType == null || apptStart == null || apptEnd == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("All fields must be filled in, no field can be left blank. Please try again");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (startAppt.isBefore(startBizHrsEst) || endAppt.isAfter(endBizHrsEst)) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("WARNING");
            alert2.setContentText("Appointment must be scheduled during business hours, which are 8am-10pm daily");
            Optional<ButtonType> result = alert2.showAndWait();
        } else if (AppointmentDoa.getAllCustomerAppointments(apptStart, apptEnd, apptCustId)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Cannot schedule conflicting appointments for the same customer. Please try again.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            //Creates new appointment
            AppointmentDoa.createAppointment(apptTitle, apptDescript, apptLocation, apptContact,
                    apptType, apptStart, apptEnd, apptCustId, apptUserId);

            //Sends user back to Home Page
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1350, 750);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Shows Confirmation dialog and if user hits "OK" then it cancels any data user entered and returns to
     * main page
     *
     * @param actionEvent Cancel Button
     * @throws IOException from FXMLLoader
     */
    public void onCancelBtn(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Are you sure you want to cancel? All data currently entered will be lost");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1350, 750);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void initialize() throws SQLException {
       LocalTime start = LocalTime.of(5, 0);
       LocalTime end = LocalTime.of(23, 0);

       while (start.isBefore(end.plusSeconds(1))) {
           startTime.getItems().add(start);
           endTime.getItems().add(start);
           start = start.plusMinutes(10);
       }
       startTime.getSelectionModel().select(LocalTime.of(8,0));
       endTime.getSelectionModel().select(LocalTime.of(22,0));

       contact_name.setItems(ContactDoa.getAllContacts());

    }
}
