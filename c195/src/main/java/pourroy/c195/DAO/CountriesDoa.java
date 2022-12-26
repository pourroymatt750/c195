package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountriesDoa {
    public static ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Country FROM countries WHERE Country_ID<4";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Country = rs.getString("Country");

                Countries c = new Countries(Country);
                countriesList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;
    }
}
