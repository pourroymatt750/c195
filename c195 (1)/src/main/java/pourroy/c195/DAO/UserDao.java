package pourroy.c195.DAO;


import javafx.collections.ObservableList;
import pourroy.c195.model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static int getLoggedInUserId(String user_name) throws SQLException {
        int user_id = 0;

        try {
            String sql = "SELECT User_ID FROM users WHERE User_Name=?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setString(1, user_name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { user_id = rs.getInt("User_ID"); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }
}
