package pourroy.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pourroy.c195.DAO.AppointmentDoa;
import pourroy.c195.DAO.DBConnection;
import pourroy.c195.DAO.UserDao;
import pourroy.c195.model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the Login Page that provides functionality for the application
 *
 * @author matthewpourroy
 */
public class LoginPageController implements Initializable {
    ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());

    /**
     * BEGIN User login activity
     * filename is the text file that all info is sent to, located in root directory
     * fileWriter allows all info to be appended to previous login info
     * outputFile prints all info to text file
     */
    String filename = "login_activity.txt", Username, dateAndTime, loginResult;

    FileWriter fileWriter = new FileWriter(filename, true);

    PrintWriter outputFile = new PrintWriter(fileWriter);
    /**
     * END User login activity
     */


    @FXML
    private Label timeZone;
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

    @FXML
    private Label errorMessageBlankFields;

    @FXML
    private Label error;

    @FXML
    private Label errorWrongLogin;

    public LoginPageController() throws IOException, SQLException {}

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

        if (username == null || username.isBlank() && password == null || password.isBlank()) {
            errorBlankField.fieldIsBlank();
        } else if (username == null || username.isBlank()) {
            errorBlankField.fieldIsBlank();
        } else if (password == null || password.isBlank()) {
            errorBlankField.fieldIsBlank();
        }

        /**
         * Determines if login credentials are accurate, if they are, then it logs user in and
         * sends user to main view
         * If login credentials are wrong, it will throw error message
         */
        if (username != null && !username.isBlank() && password != null && !password.isBlank()) {
            if (validLogin(username, password)) {

                //Appends Login info to "login_activity.txt" file
                Username = username;
                dateAndTime = String.valueOf(LocalDateTime.now());
                loginResult = "Success";
                outputFile.println("Username: " + Username + ", Date and Time: " + dateAndTime + ", Login Success/Failure: " + loginResult);
                outputFile.close();

                //Sends user to home page
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1350, 700);
                stage.setTitle("Home");
                stage.setScene(scene);
                stage.show();

                //Gets User ID
                int userId = UserDao.getLoggedInUserId(username);

                /**
                 * Gives alert about whether or not the user has an appointment in the next
                 * 15 minutes or not
                */
                if (AppointmentDoa.upcomingAppt(userId)) {
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upcoming Appointment");
                    alert.setContentText("You do NOT have an appointment in the next 15 minutes");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } else {
                wrongLoginEntered.wrongLogin();

                //Appends Login info to "login_activity.txt" file
                Username = username;
                dateAndTime = String.valueOf(LocalDateTime.now());
                loginResult = "Failure";
                outputFile.println("Username: " + Username + ", Date and Time: " + dateAndTime + ", Login Success/Failure: " + loginResult);
                outputFile.close();
            }
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
    public boolean validLogin(String user_name, String password) throws SQLException {
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
     * LAMBDA - This lambda serves the benefit of re-usability so that the same error
     * message can be shown for the various error checks. It also makes the code in the
     * error checks cleaner because the only method that needs to be called is this
     * lambda as opposed to having to write all the code in the same area.
     *
     * Belong to BlankField Interface located in "../src/main/java/pourroy.c195"
     *
     * This lambda method is called whenever the user hits the "Login" button when either the
     * username, password or both are blank or null.
     */
    BlankField errorBlankField = () -> {
        if (Locale.getDefault().getLanguage().equals("fr") ||
                Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("error"));
            alert.setContentText(rb.getString("errorMessageBlankFields"));
            alert.showAndWait();
        }
        return;
    };

    /**
     * LAMBDA - This lambda serves the benefit of re-usability so that the same error
     * message can be shown for the various error checks. It also makes the code in the
     * error checks cleaner because the only method that needs to be called is this
     * lambda as opposed to having to write all the code in the same area.
     *
     * Belong to WrongLogin Interface located in "../src/main/java/pourroy.c195"
     *
     * This lambda method is called whenever the user enters a username and password
     * combo that does NOT match any login combo in the database in the "Users" table.
     */
    WrongLogin wrongLoginEntered = () -> {
        if (Locale.getDefault().getLanguage().equals("fr") ||
                Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("error"));
            alert.setContentText(rb.getString("errorWrongLogin"));
            alert.showAndWait();
        }
        return;
    };

    /**
     * Exits the program
     *
     * @param actionEvent Exit Button
     */
    public void onExitButton(ActionEvent actionEvent) { System.exit(0); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        errorMessageBlankFields.setText("Username and/or Password cannot be left blank");
        timeZone.setText(String.valueOf(ZoneId.systemDefault()));

        if (Locale.getDefault().getLanguage().equals("fr") ||
            Locale.getDefault().getLanguage().equals("en")) {
            usernameLabel.setText(rb.getString("usernameLabel"));
            passwordLabel.setText(rb.getString("passwordLabel"));
            loginLabel.setText(rb.getString("loginLabel"));
            loginBtn.setText(rb.getString("loginBtn"));
            exitBtn.setText(rb.getString("exitBtn"));
            errorMessageBlankFields.setText(rb.getString("errorMessageBlankFields"));
            error.setText(rb.getString("error"));
            errorWrongLogin.setText(rb.getString("errorWrongLogin"));
        }

        //Hides all error labels upon initialization and only shows when error checks return "True"
        errorMessageBlankFields.setVisible(false);
        error.setVisible(false);
        errorWrongLogin.setVisible(false);
    }
}
