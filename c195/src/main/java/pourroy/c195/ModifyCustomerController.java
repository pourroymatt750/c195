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

import java.io.IOException;
import java.util.Optional;

/**
 * Controller for the Modify Customer Page that provides functionality for the application
 *
 * @author matthewpourroy
 */
public class ModifyCustomerController {
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
    private ComboBox country;

    /**
     * VARCHAR(50) Division
     * Originates in First-Level Divisions Model
     */
    @FXML
    private ComboBox division;

    /**
     * Modifies customer info to the database and displays on the Main Page
     *
     * @param actionEvent Modify button action
     * @throws IOException from FXMLLoader
     */
    public void onModifyBtn(ActionEvent actionEvent) throws IOException {
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
            Scene scene = new Scene(root, 1350, 1400);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
    }
}
