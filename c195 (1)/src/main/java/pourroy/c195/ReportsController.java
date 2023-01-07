package pourroy.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pourroy.c195.DAO.AppointmentDoa;
import pourroy.c195.DAO.ContactDoa;
import pourroy.c195.DAO.CustomerDao;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;
import pourroy.c195.model.Customers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;

public class ReportsController {
    /**
     * BEGIN Appointments
     * Tableview Contact Appointments Table
     */
    @FXML
    private TableView<Appointments> appointmentsTable;

    /**
     * VARCHAR(50) Contact Name
     */
    @FXML
    private ComboBox<Contacts> contact_name;

    public void onContactChosen(ActionEvent actionEvent) throws SQLException {
        int contactId = contact_name.getValue().getContact_id();

        appointmentsTable.setItems(AppointmentDoa.getAppointmentsByContact(contactId));
        appointment_id.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }

    /**
     * INT(10) Appointment ID
     */
    @FXML
    private TableColumn appointment_id;

    /**
     * VARCHAR(50) Title
     */
    @FXML
    private TableColumn title;

    /**
     * VARCHAR(50) Description
     */
    @FXML
    private TableColumn description;

    /**
     * VARCHAR(50) Location
     */
    @FXML
    private TableColumn appointment_location;

    /**
     * VARCHAR(50) Type
     */
    @FXML
    private TableColumn type;

    /**
     * LOCALDATETIME Start
     */
    @FXML
    private TableColumn start;

    /**
     * LOCALDATETIME End
     */
    @FXML
    private TableColumn end;

    /**
     * INT(10) Customer ID
     * Foreign Key from Customers Model
     */
    @FXML
    private TableColumn customer_id;
    /**
     * END Contact Appointments
     */


    /**
     * Tableview total number of customer appointments by type and month
     */
    @FXML
    private TableColumn appointment_month;

    @FXML
    private TableColumn appointment_type;

    @FXML
    private TableColumn total_appointments;
    /**
     * END total number of customer appointments by type and month
     */


    /**
     * Tableview total number of customers in each country
     * Additional reports
     *
     * Number of numbers in each country table
     */
    @FXML
    private TableView<Customers> customersCountryTable;

    /**
     * VARCHAR(50) Country
     */
    @FXML
    private TableColumn country;

    /**
     * int Total Customers in each country
     */
    @FXML
    private TableColumn total_customers;
    /**
     * END total number of customers in each country
     */

    public void onBackBtn(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Are you sure you want to go back?");
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

        customersCountryTable.setItems(CustomerDao.getCustomersInCountries());
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        total_customers.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }
}
