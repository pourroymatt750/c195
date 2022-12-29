package pourroy.c195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import pourroy.c195.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {

    private static int customer_id;

    private static int division_id;

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

        int divisionID = division.getDivision_id();
        System.out.println(divisionID);

        try {
            //SQL Statement Customers Table
            String sql2 = "INSERT INTO customers VALUES(null, ?, ?, ?, ?, null, null, null, null, ?)";

            PreparedStatement ps2 = DBConnection.connection.prepareStatement(sql2);

            ps2.setString(1, String.valueOf(customer_name));
            ps2.setString(2, String.valueOf(address));
            ps2.setString(3, String.valueOf(postal_code));
            ps2.setString(4, String.valueOf(phone));
            ps2.setInt(5, divisionID);

            ps2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Modify customer helper function
    public static void modifyCustomer(int country_id, int division_id, int customer_id, String customer_name, String address,
                                      String postal_code, String phone, FirstLevelDivisions division, Countries country) {

        try {
            //SQL Statement for Customers Table
            String sql = "UPDATE customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);
            pst.setString(1, customer_name);
            pst.setString(2, address);
            pst.setString(3, postal_code);
            pst.setString(4, phone);
            pst.setInt(5, division_id);
            pst.setInt(6, customer_id);

            pst.execute();

            //SQL Statement First-Level Divisions Table
//            String sql1 = "UPDATE first_level_divisions set Division = ? WHERE Division_ID = ? ";
//
//            PreparedStatement pst1 = DBConnection.connection.prepareStatement(sql1);
//
//            pst1.setString(1, String.valueOf(division));
//            System.out.println(division);
//            pst1.setInt(2, division_id);
//            System.out.println(division_id);
//
//            pst1.execute();

            //SQL Statement for Country Table
//            String sql2 = "UPDATE countries set Country = ? WHERE Country_ID = ?";
//
//            PreparedStatement pst2 = DBConnection.connection.prepareStatement(sql2);
//
//            pst2.setString(1, String.valueOf(country));
//            pst2.setInt(2, country_id);
//
//            pst2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCustomerId(String customer) throws SQLException{
        int customer_id = 0;

        try {
            String sql = "SELECT * FROM customers WHERE Customer_Name=?";

            PreparedStatement pst = DBConnection.connection.prepareStatement(sql);

            pst.setString(1, String.valueOf(customer));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) { customer_id = rs.getInt("Customer_ID"); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_id;
    }

}
