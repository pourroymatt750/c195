package pourroy.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pourroy.c195.DAO.AppointmentDoa;
import pourroy.c195.DAO.ContactDoa;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ModifyAppointmentController {
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
    public void onModifyBtn(ActionEvent actionEvent) throws IOException, SQLException {

        int appointmentId = Integer.parseInt(appointment_id.getText());
        String apptTitle = title.getText();
        String apptDescript = description.getText();
        String apptLocation = appointment_location.getText();
        Contacts apptContact = contact_name.getValue();
        String apptType = type.getText();
        LocalDateTime apptStart = LocalDateTime.of(startDate.getValue(), startTime.getValue());
        LocalDateTime apptEnd = LocalDateTime.of(endDate.getValue(), endTime.getValue());
        int appCustId = Integer.parseInt(customer_id.getText());
        int appUserId = Integer.parseInt(user_id.getText());

        if (apptTitle == null || apptDescript == null || apptLocation == null || apptContact == null
                || apptType == null || apptStart == null || apptEnd == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("All fields must be filled in, no field can be left blank. Please try again");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            int contactId = apptContact.getContact_id();

            AppointmentDoa.modifyAppointment(appointmentId, apptTitle, apptDescript, apptLocation,
                    apptContact,apptType, apptStart, apptEnd, appCustId, appUserId, contactId);

            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1350, 700);
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
            Scene scene = new Scene(root, 1350, 700);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
    }

    Appointments appointment;

    public void sendAppointment(Appointments appointment) throws SQLException {
        this.appointment = appointment;
        appointment_id.setText(String.valueOf(appointment.getAppointment_id()));
        title.setText(appointment.getTitle());
        description.setText(appointment.getDescription());
        appointment_location.setText(appointment.getAppointment_location());
        contact_name.setValue(ContactDoa.getContactId(appointment.getContact_name()));
        type.setText(appointment.getType());
        startDate.setValue(appointment.getStart().toLocalDate());
        startTime.setValue(appointment.getStart().toLocalTime());
        endDate.setValue(appointment.getEnd().toLocalDate());
        endTime.setValue(appointment.getEnd().toLocalTime());
        customer_id.setText(String.valueOf(appointment.getCustomer_id()));
        user_id.setText(String.valueOf(appointment.getUser_id()));
    }

    public void initialize() throws SQLException {
        LocalTime start = LocalTime.of(6, 0);
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
