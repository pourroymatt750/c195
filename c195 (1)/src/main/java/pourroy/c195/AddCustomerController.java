package pourroy.c195;

import javafx.collections.ObservableList;
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
import pourroy.c195.DAO.CountriesDoa;
import pourroy.c195.DAO.CustomerDao;
import pourroy.c195.DAO.DBConnection;
import pourroy.c195.DAO.FirstLevelDivisionsDoa;
import pourroy.c195.model.Countries;
import pourroy.c195.model.Customers;
import pourroy.c195.model.FirstLevelDivisions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Controller for the Add Customer Page that provides functionality for the application
 *
 * @author matthewpourroy
 */
public class AddCustomerController {
    /**
     * INT(10) Customer ID
     * Primary Key
     */
    @FXML
    private TextField customer_id;

    /**
     * VARCHAR(50) Customer Name
     */
    @FXML
    private TextField customer_name;

    /**
     * VARCHAR(100) Address
     */
    @FXML
    private TextField address;

    /**
     * VARCHAR(100) Postal Code
     */
    @FXML
    private TextField postal_code;

    /**
     * VARCHAR(50) Phone
     */
    @FXML
    private TextField phone;

    /**
     * DATETIME Create Date
     */
    @FXML
    private TextField create_date;

    /**
     * VARCHAR(50) Created By
     */
    @FXML
    private TextField created_by;

    /**
     * TIMESTAMP Last Update
     */
    @FXML
    private TextField last_update;

    /**
     * VARCHAR(50) Last Updated By
     */
    @FXML
    private TextField last_updated_by;

    /**
     * VARCHAR(50) Country
     * Originates in Countries Model
     */
    @FXML
    private ComboBox<Countries> country;

    /**
     * VARCHAR(50) Division
     * Originates in First-Level Divisions Model
     */
    @FXML
    private ComboBox<FirstLevelDivisions> division;

    /**
     * Adds new customer to the database and displays on the Main Page
     *
     * @param actionEvent Add button action
     * @throws IOException from FXMLLoader
     */
    public void onAddBtn(ActionEvent actionEvent) throws IOException {
        String customerName = customer_name.getText();
        String customerAddress = address.getText();
        String postalCode = postal_code.getText();
        String customerPhone = phone.getText();
        Countries customerCountry = (Countries) country.getValue();
        FirstLevelDivisions customerDivision = (FirstLevelDivisions) division.getValue();

        if (customerName == null || customerAddress == null || postalCode == null ||
                customerPhone == null || customerCountry == null || customerDivision == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("All fields must be filled in, no field can be left blank. Please try again");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            //Creates new customer
            CustomerDao.createCustomer(customerName, customerAddress, postalCode, customerPhone,customerDivision, customerCountry);

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

    public void updateCountryDivisions() throws SQLException {
        Countries customerCountry = (Countries) country.getValue();
        System.out.println(customerCountry);

        if (customerCountry == null) {
            country.setItems(CountriesDoa.getAllCountries());
        }
    }

    public void onCountrySelected(ActionEvent actionEvent) throws SQLException {
        Countries customerCountry = (Countries) country.getValue();
        int countryId = country.getValue().getCountry_id();

        division.setItems(FirstLevelDivisionsDoa.getCountryDivisions(countryId));
    }

    public void initialize() throws SQLException {
        country.setItems(CountriesDoa.getAllCountries());
    }
}
