package pourroy.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pourroy.c195.DAO.AppointmentDoa;
import pourroy.c195.DAO.ContactDoa;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;

import java.io.IOException;
import java.sql.SQLException;
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

    /**
     * DATETIME Start
     */
    @FXML
    private TextField start;

    /**
     * DATETIME End
     */
    @FXML
    private TextField end;

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
        String apptStart = start.getText();
        String apptEnd = end.getText();
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
        start.setText(String.valueOf(appointment.getStart()));
        end.setText(String.valueOf(appointment.getEnd()));
        customer_id.setText(String.valueOf(appointment.getCustomer_id()));
        user_id.setText(String.valueOf(appointment.getUser_id()));
    }

    public void initialize() throws SQLException {
        contact_name.setItems(ContactDoa.getAllContacts());
    }
}
