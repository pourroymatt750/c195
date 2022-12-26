package pourroy.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pourroy.c195.DAO.AppointmentDoa;
import pourroy.c195.DAO.CustomerDao;
import pourroy.c195.DAO.DBConnection;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for the Main Page that provides functionality for the application
 *
 * @author matthewpourroy
 */
public class MainController {
    /**
     * BEGIN Customer
     * Tableview Customers Table
     */
    @FXML
    private TableView<Customers> customersTable;

    /**
     * VARCHAR(50) Customer Name
     */
    @FXML
    private TableColumn customer_name;

    /**
     * VARCHAR(100) Address
     */
    @FXML
    private TableColumn address;

    /**
     * VARCHAR(50) Division
     * Originates from First-Level Divisions
     */
    @FXML
    private TableColumn division;

    /**
     * VARCHAR(50) Country
     * Originates from Countries Model
     */
    @FXML
    private TableColumn country;

    /**
     * VARCHAR(50) Postal Code
     */
    @FXML
    private TableColumn postal_code;

    /**
     * VARCHAR(50) Phone
     */
    @FXML
    private TableColumn phone;

    /**
     * Takes user to Add Customer Page
     *
     * @param actionEvent Add Customer Button action
     * @throws IOException from FXMLLoader
     */
    public void onAddCustomerBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Takes user to Modify Customer Page
     *
     * @param actionEvent Modify Customer Button action
     * @throws IOException from FXMLLoader
     */
    public void onModifyCustomerBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modify-customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Modify Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Checks to see if customer has any appointments:
     * If customer has an appointment, then an error is thrown saying
     * "Cannot delete a customer who has an appointment scheduled. Please delete customer appointments
     * before deleting the customer".
     * If customer does NOT have an appointment, then dialog box populates asking to confirm if user
     * wants to delete the customer
     *
     * Deletes customer out of database
     *
     *
     * @param actionEvent Delete Customer Button action
     * @throws IOException from FXMLLoader
     */
    public void onDeleteCustomerBtn(ActionEvent actionEvent) throws IOException {
    }
    /**
     * END Customers
     */

    /**
     * BEGIN Appointments
     * Tableview Appointments Table
     */
    @FXML
    private TableView<Appointments> appointmentsTable;

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
     * VARCHAR(50) Spelled as "Contact" in TableView
     * Originates from Contacts Model
     */
    @FXML
    private TableColumn contact_name;

    /**
     * VARCHAR(50) Type
     */
    @FXML
    private TableColumn type;

    /**
     * DATETIME Start
     */
    @FXML
    private TableColumn start;

    /**
     * DATETIME Start
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
     * INT(10)
     * Foreign Key from Users Model
     */
    @FXML
    private TableColumn user_id;

    /**
     * Takes user to Add Appointment Page
     *
     * @param actionEvent Add Appointment Button action
     * @throws IOException from FXMLLoader
     */
    public void onAddApptBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Takes user to Modify Appointment Page
     *
     * @param actionEvent Modify Appointment Button action
     * @throws IOException from FXMLLoader
     */
    public void onModifyApptBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modify-appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Modify Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes Appointment
     *
     * @param actionEvent Delete Button action
     * @throws IOException from FXMLLoader
     */
    public void onDeleteApptBtn(ActionEvent actionEvent) throws IOException {
    }
    /**
     * END Appointments
     */

    /**
     * Exits the program
     *
     * @param actionEvent Exit Button
     */
    public void onExitBtn(ActionEvent actionEvent) { System.exit(0); }

    public void initialize() throws SQLException {
        customersTable.setItems(CustomerDao.getAllCustomers());
        customer_name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        division.setCellValueFactory(new PropertyValueFactory<>("division"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        postal_code.setCellValueFactory(new PropertyValueFactory<>("postal_code"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        appointmentsTable.setItems(AppointmentDoa.getAllAppointments());
        appointment_id.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointment_location.setCellValueFactory(new PropertyValueFactory<>("appointment_location"));
        contact_name.setCellValueFactory(new PropertyValueFactory<>("contact_name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    }
}