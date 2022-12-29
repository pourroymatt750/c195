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
            String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID<4";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Contact_Name = rs.getString("Contact_Name");

                Contacts c = new Contacts(Contact_Name);
                contactsList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactsList;
    }

}
