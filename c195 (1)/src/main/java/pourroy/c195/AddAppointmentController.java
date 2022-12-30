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
import pourroy.c195.DAO.CustomerDao;
import pourroy.c195.DAO.UserDao;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;

import java.io.IOException;
import java.sql.SQLException;
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
    public void onAddBtn(ActionEvent actionEvent) throws IOException {
        String apptTitle = title.getText();
        String apptDescript = description.getText();
        String apptLocation = appointment_location.getText();
        Contacts apptContact = contact_name.getValue();
        String apptType = type.getText();
        String apptStart = start.getText();
        String apptEnd = end.getText();
        int apptCustId = Integer.parseInt(customer_id.getText());
        int apptUserId = Integer.parseInt(user_id.getText());

        if (apptTitle == null || apptDescript == null || apptLocation == null || apptContact == null
                || apptType == null || apptStart == null || apptEnd == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("All fields must be filled in, no field can be left blank. Please try again");
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
        contact_name.setItems(ContactDoa.getAllContacts());
    }
}
