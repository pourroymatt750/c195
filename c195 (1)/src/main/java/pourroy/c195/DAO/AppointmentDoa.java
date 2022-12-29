package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;
import pourroy.c195.model.Customers;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;

public class AppointmentDoa {

    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            //SQL statement
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, Type, Start, End, Customer_ID, User_ID "
                       + "FROM appointments, contacts "
                       + "WHERE appointments.Contact_ID=contacts.Contact_ID";


            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results data
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Contact_Name = rs.getString("Contact_Name");
                String Type = rs.getString("Type");
                Date Start = rs.getDate("Start");
                Date End = rs.getDate("End");
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");

                Appointments a = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID);
                appointmentsList.add(a);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return appointmentsList;
    }

    public static void createAppointment(String title, String description, String appointment_location,
                                         Contacts contact_name, String type, String start,
                                         String end, String customer_id, String user_id) {
        try {
            //SQL Statement Contacts Table
            String sql = "INSERT INTO contacts VALUES(null, ?, null)";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, String.valueOf(contact_name));

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int Contact_ID = rs.getInt(1);

            //SQL Statement in Appointments Table
            String sql1 = "INSERT INTO appointments VALUES(null, ?, ?, ?, ?, ?, ?, null, null, null, null, ?, ?, ?)";

            PreparedStatement ps1 = DBConnection.connection.prepareStatement(sql1);

            ps1.setString(1, String.valueOf(title));
            ps1.setString(2, String.valueOf(description));
            ps1.setString(3, String.valueOf(appointment_location));
            ps1.setString(4, String.valueOf(type));
            ps1.setString(5, String.valueOf(start));
            ps1.setString(6, String.valueOf(end));
            ps1.setString(7, String.valueOf(customer_id));
            ps1.setString(8, String.valueOf(user_id));
            ps1.setString(9, String.valueOf(Contact_ID));

            ps1.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
