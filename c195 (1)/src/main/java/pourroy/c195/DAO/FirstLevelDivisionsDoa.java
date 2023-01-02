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
            String sql = "SELECT Division, Division_ID FROM first_level_divisions";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Division = rs.getString("Division");
                int Division_ID = rs.getInt("Division_ID");

                FirstLevelDivisions fld = new FirstLevelDivisions(Division, Division_ID);
                firstLevelDivisionsList.add(fld);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstLevelDivisionsList;
    }

    public static FirstLevelDivisions getDivisionId(String Division) {
        FirstLevelDivisions division = null;

        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setString(1, Division);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String DivisionName = rs.getString("Division");
                int DivisionId = rs.getInt("Division_ID");

                division = new FirstLevelDivisions(DivisionName, DivisionId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }

    public static ObservableList<FirstLevelDivisions> getCountryDivisions(int Customer_ID) throws SQLException {
        ObservableList<FirstLevelDivisions> countryDivisionsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Division, Division_ID FROM first_level_divisions WHERE Country_ID=?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String Division = rs.getString("Division");
                int Division_ID = rs.getInt("Division_ID");

                FirstLevelDivisions fld = new FirstLevelDivisions(Division, Division_ID);
                countryDivisionsList.add(fld);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(countryDivisionsList);
        return countryDivisionsList;
    }
}
