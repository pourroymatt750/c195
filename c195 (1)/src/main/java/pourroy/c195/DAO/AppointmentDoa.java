package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pourroy.c195.model.Appointments;
import pourroy.c195.model.Contacts;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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
        return appointmentsList;
    }

    public static ObservableList<Appointments> getAppointmentByWeek(LocalDateTime startWeek, LocalDateTime endWeek) throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            //SQL Statement
            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, " +
                    "Type, Start, End, Customer_ID, User_ID" +
                    " FROM appointments, contacts WHERE Start BETWEEN ? AND ? AND appointments.Contact_ID=contacts.Contact_ID";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);
            pst.setTimestamp(1, Timestamp.valueOf(startWeek));
            pst.setTimestamp(2, Timestamp.valueOf(endWeek));

            System.out.println(startWeek);
            System.out.println(endWeek);


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
        return appointmentsList;
    }

public static Boolean getAllCustomerAppointments(LocalDateTime apptStart , LocalDateTime apptEnd, int customer_id) throws SQLException {

        Boolean isBetween = false;

        try {
            String sql = "SELECT Start, End, Customer_ID FROM appointments WHERE Customer_ID=?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

            pst.setInt(1, customer_id);

            pst.executeQuery();

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");

                if (apptStart.isAfter(Start)  && apptEnd.isBefore(End)) {
                    isBetween = true;
                }
                if (apptStart.isEqual(Start) && apptEnd.isEqual(End)) {
                    isBetween = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isBetween;
    }

    public static ObservableList<Appointments> getAppointmentsByContact(int contact_id) throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            //SQL statement
            String sql = "SELECT Appointment_ID, Title, Description, Type, Start, End, Customer_ID "
                    + "FROM appointments WHERE Contact_ID=?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setInt(1, contact_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results data
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");

                Appointments a = new Appointments(Appointment_ID, Title, Description, Type, Start, End, Customer_ID);
                appointmentsList.add(a);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appointmentsList;
    }

    public static ObservableList<Appointments> getApptsByTypeAndMonth() throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            //SQL statement
            String sql = "SELECT Type, Start, COUNT(Appointment_ID) as Total_Appointments " +
                    "FROM appointments GROUP BY Type, Month(Start)";


            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results data
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                int Total_Appointments = rs.getInt("Total_Appointments");

                Appointments a = new Appointments(Type, Start, Total_Appointments);
                appointmentsList.add(a);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appointmentsList;
    }

    public static Boolean upcomingAppt(int user_id) throws SQLException {
        Boolean isBetween=false;

        try {
            String sql = "SELECT Appointment_ID, Start FROM appointments WHERE User_ID=?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

            pst.setInt(1, user_id);
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime currTime = LocalDateTime.now();
                long timeDiff = ChronoUnit.MINUTES.between(currTime, Start);
                LocalDate date = Start.toLocalDate();
                LocalTime time = Start.toLocalTime();

                if (timeDiff >= 0 && timeDiff <= 15) {
                    isBetween = true;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upcoming Appointment");
                    alert.setContentText("You have an appointment in the next 15 minutes. Appointment ID: " + Appointment_ID + " on " + date + " at " + time);
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isBetween;
    }
}
