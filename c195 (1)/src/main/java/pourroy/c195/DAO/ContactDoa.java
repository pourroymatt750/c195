package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Contacts;;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDoa {

    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Contact_Name, Contact_ID FROM contacts";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Contact_Name = rs.getString("Contact_Name");
                int Contact_ID = rs.getInt("Contact_ID");

                Contacts c = new Contacts(Contact_Name, Contact_ID);
                contactsList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactsList;
    }

    public static Contacts getContactId(String Contact_Name) {
        Contacts contact = null;

        try {
            String sql = "SELECT Contact_ID, Contact_Name FROM contacts WHERE Contact_Name = ?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

            pst.setString(1, Contact_Name);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String ContactName = rs.getString("Contact_Name");
                int ContactId = rs.getInt("Contact_ID");

                contact = new Contacts(Contact_Name, ContactId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }
}
