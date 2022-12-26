package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Countries;
import pourroy.c195.model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstLevelDivisionsDoa {

    public static ObservableList<FirstLevelDivisions> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID<105";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Division = rs.getString("Division");

                FirstLevelDivisions fld = new FirstLevelDivisions(Division);
                firstLevelDivisionsList.add(fld);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstLevelDivisionsList;
    }

}
