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
            String sql = "SELECT Country, Country_ID FROM countries";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String CountryName = rs.getString("Country");
                int CountryId = rs.getInt("Country_ID");

                Countries c = new Countries(CountryName, CountryId);
                countriesList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;
    }

    public static Countries getCountryId(String Country) {
        Countries country = null;

        try {
            String sql = "SELECT Country_ID, Country FROM countries WHERE Country = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setString(1, Country);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String CountryName = rs.getString("Country");
                int CountryId = rs.getInt("Country_ID");

                country = new Countries(CountryName, CountryId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
}
