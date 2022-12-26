package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Customers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
}
