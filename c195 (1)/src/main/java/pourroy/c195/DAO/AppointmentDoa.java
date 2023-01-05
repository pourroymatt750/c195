package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;
import pourroy.c195.model.Customers;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDoa {

    public static int contact_id;

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
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
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
                                         Contacts contact_name, String type,LocalDateTime start,
                                         LocalDateTime end, int customer_id, int user_id) {

        try {
            //SQL Statement in Appointments Table
            String sql1 = "INSERT INTO appointments VALUES(null, ?, ?, ?, ?, ?, ?, null, null, null, null, ?, ?, ?)";

            PreparedStatement ps1 = DBConnection.connection.prepareStatement(sql1);

            ps1.setString(1, String.valueOf(title));
            ps1.setString(2, String.valueOf(description));
            ps1.setString(3, String.valueOf(appointment_location));
            ps1.setString(4, String.valueOf(type));
            ps1.setTimestamp(5, Timestamp.valueOf(start));
            ps1.setTimestamp(6, Timestamp.valueOf(end));
            ps1.setInt(7, customer_id);
            ps1.setInt(8, user_id);
            ps1.setInt(9, contact_name.getContact_id());

            ps1.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyAppointment(int appointment_id, String title, String description,
                                         String appointment_location, Contacts contact_name, String type, LocalDateTime start,
                                         LocalDateTime end, int customer_id, int user_id, int contact_id) {
        try {
            //SQL Statement
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, " +
                    "Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, description);
            pst.setString(3, appointment_location);
            pst.setString(4, type);
            pst.setTimestamp(5, Timestamp.valueOf(start));
            pst.setTimestamp(6, Timestamp.valueOf(end));
            pst.setInt(7, customer_id);
            pst.setInt(8, user_id);
            pst.setInt(9, contact_id);
            pst.setInt(10, appointment_id);

            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(int Appointment_ID) {
        try {
            //SQL Statement
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

            pst.setInt(1, Appointment_ID);

            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Appointments> getAppointmentByMonth(LocalDateTime start) throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        System.out.println(start);

        try {
            //SQL Statement
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, " +
                    "Type, Start, End, Customer_ID, User_ID" +
                    " FROM appointments, contacts WHERE Month(Start) = ? AND appointments.Contact_ID=contacts.Contact_ID";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);
            pst.setInt(1, start.getMonth().getValue());


            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //get results data
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Contact_Name = rs.getString("Contact_Name");
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");

                Appointments a = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID);
                appointmentsList.add(a);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(appointmentsList);
        return appointmentsList;
    }

}
