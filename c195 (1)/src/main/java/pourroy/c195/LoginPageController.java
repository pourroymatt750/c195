package pourroy.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pourroy.c195.DAO.DBConnection;

import java.io.IOException;
import java.sql.*;

/**
 * Controller for the Login Page that provides functionality for the application
 *
 * @author matthewpourroy
 */
public class LoginPageController {
    /**
     * Login Label (header)
     */
    @FXML
    private Label loginLabel;

    /**
     * User Name Label
     */
    @FXML
    private Label usernameLabel;

    /**
     * VARCHAR(50) UNIQUE User Name
     */
    @FXML
    private TextField usernameField;

    /**
     * Password Label
     */
    @FXML
    private Label passwordLabel;

    /**
     * TEXT Password
     */
    @FXML
    private TextField passwordField;

    /**
     * Login Button
     */
    @FXML
    private Button loginBtn;

    /**
     * Exit Button
     */
    @FXML
    private Button exitBtn;

    /**
     * User enters User Name and Password.
     *
     * @param actionEvent Login Button
     * @throws IOException from FXMLLoader
     * @throws SQLException from prepareStatement
     */
    public void onLoginButton(ActionEvent actionEvent) throws SQLException, IOException {
        //Gets username and password entered by user
        String username = usernameField.getText();
        String password = passwordField.getText();

        //Checks if username is blank, and throws error message if blank
        if (username == null || username.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Username cannot be left blank");
            alert.showAndWait();
        }

        //Checks if password is blank, and throws error message if blank
        if (password == null || password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Password cannot be left blank");
            alert.showAndWait();
        }

        /**
         * Determines if login credentials are accurate, if they are, then it logs user in and
         * sends user to main view
         * If login credentials are wrong, it will throw error message
         */
        if (validLogin(username, password)) {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1350, 700);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Wrong login credentials. Please try again with a valid login.");
            alert.showAndWait();
        }
    }

    /**
     * Contains a SQL query that searches for a matching username and password for what the user entered
     * @param user_name is Username user entered
     * @param password is Password user entered
     * @return true if username and password user entered is found in the database
     * @return false if username and password user entered is NOT found in the database
     * @throws SQLException from prepareStatement
     */
    public boolean validLogin(String user_name, String password) throws SQLException{
        String sql = "SELECT User_Name, Password FROM users WHERE user_name=User_Name AND password=Password";

        PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getString("User_Name").equals(user_name) && resultSet.getString("Password").equals(password)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Exits the program
     *
     * @param actionEvent Exit Button
     */
    public void onExitButton(ActionEvent actionEvent) { System.exit(0); }
}
