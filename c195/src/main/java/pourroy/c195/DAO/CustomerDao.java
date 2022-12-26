package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pourroy.c195.model.Countries;
import pourroy.c195.model.Customers;
import pourroy.c195.model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {
    /**
     * Gets list of all customers
     * @throws SQLException from Prepared Statement
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            //SQL statement
            String sql = "SELECT Customer_Name, Address, Division, Country, Postal_Code, Phone " +
                         "FROM customers, first_level_divisions, countries " +
                         "WHERE customers.Division_ID=first_level_divisions.Division_ID AND " +
                         "first_level_divisions.Country_ID=countries.Country_ID";


            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //get results data
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Division = rs.getString("Division");
                String Country = rs.getString("Country");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");

                Customers c = new Customers(Customer_Name, Address, Division, Country, Postal_Code, Phone);
                customersList.add(c);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return customersList;
    }

    //Add new customer helper function
    public static void createCustomer(String customer_name, String address, String postal_code,
                                      String phone, FirstLevelDivisions division, Countries country) {

        try {
            //SQL Statement Countries Table
            String sql = "INSERT INTO countries VALUES (null, ?, null, null, null, null)";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, String.valueOf(country));

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int Country_ID = rs.getInt(1);

            //SQL Statement First-Level Divisions Table
            String sql1 = "INSERT INTO first_level_divisions VALUES(null, ?, null, null, null, null, ?)";

            PreparedStatement ps1 = DBConnection.connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

            ps1.setString(1, String.valueOf(division));
            ps1.setString(2, String.valueOf(Country_ID));

            ps1.execute();

            ResultSet rs1 = ps1.getGeneratedKeys();
            rs1.next();
            int Division_ID = rs1.getInt(1);


            //SQL Statement Customers Table
            String sql2 = "INSERT INTO customers VALUES(null, ?, ?, ?, ?, null, null, null, null, ?)";

            PreparedStatement ps2 = DBConnection.connection.prepareStatement(sql2);

            ps2.setString(1, String.valueOf(customer_name));
            ps2.setString(2, String.valueOf(address));
            ps2.setString(3, String.valueOf(postal_code));
            ps2.setString(4, String.valueOf(phone));
            ps2.setString(5, String.valueOf(Division_ID));

            ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
