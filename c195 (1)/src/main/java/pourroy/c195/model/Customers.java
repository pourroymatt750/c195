package pourroy.c195.model;

import java.security.Timestamp;
import java.util.Date;

/**
 * Serves as a Model of Customers
 *
 * @author matthewpourroy
 */
public class Customers {
    /**
     * INT(10) Customer ID
     * Primary Key
     */
    private int customer_id;

    /**
     * VARCHAR(50) Customer Name
     */
    private String customer_name;

    /**
     * VARCHAR(100) Address
     */
    private String address;

    /**
     * VARCHAR(100) Postal Code
     */
    private String postal_code;

    /**
     * VARCHAR(50) Phone
     */
    private String phone;

    /**
     * DATETIME Create Date
     */
    private Date create_date;

    /**
     * VARCHAR(50) Created By
     */
    private String created_by;

    /**
     * TIMESTAMP Last Update
     */
    private Timestamp last_update;

    /**
     * VARCHAR(50) Last Updated By
     */
    private String last_updated_by;

    /**
     * VARCHAR(50) Division
     * Comes from First-Level Divisions Model
     */
    private String division;

    /**
     * INT(10) Division ID
     * Foreign Key to First-Level Divisions Model
     */
    private int division_id;

    /**
     * VARCHAR Country
     * Comes from Countries Model
     */
    private String country;

    /**
     * Constructor
     *
     * @param customer_id is Customer ID
     * @param customer_name is Customer Name
     * @param address is Address
     * @param phone is Phone
     * @param create_date is Create Date
     * @param created_by is Created By
     * @param last_update is Last Update
     * @param last_updated_by is Last Updated By
     * @param division_id is Division ID
     */
    public Customers(int customer_id, String customer_name, String address, String postal_code, String phone, Date create_date, String created_by, Timestamp last_update, String last_updated_by, int division_id) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
    }

    /**
     * Constructor for Customers Object in CustomerDao
     * @param customer_name is Customer name
     * @param address is Address
     * @param division is Division
     * @param country is Country
     * @param postal_code is Postal Code
     * @param phone is Phone
     */
    public Customers(String customer_name, String address, String division, String country, String postal_code, String phone) {
        this.customer_name = customer_name;
        this.address = address;
        this.division = division;
        this.country = country;
        this.postal_code = postal_code;
        this.phone = phone;
    }

    public Customers(String country, int customer_id) {
        this.customer_id=customer_id;
        this.country = country;
    }

    private int total_customers;
    private int country_id;


    /**
     * Gets Division from First-Level Divisions Model
     *
     * @return division
     */
    public String getDivision() { return division; }

    /**
     * Sets Division from First-Level Divisions Model
     *
     * @param division
     */
    public void setDivision(String division) { this.division = division; }

    /**
     * Gets Country from Countries Model
     *
     * @return country
     */
    public String getCountry() { return country; }

    /**
     * Sets Country from Countries Model
     *
     * @param country
     */
    public void setCountry(String country) { this.country = country; }

    /**
     * Gets Customer ID
     *
     * @return customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * Sets Customer ID
     * @param customer_id
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * Gets Customer Name
     *
     * @return customer_name
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * Sets Customer Name
     *
     * @param customer_name
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * Get Address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets Address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets Postal Code
     *
     * @return postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * Sets Postal Code
     *
     * @param postal_code
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * Gets Phone
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets Phone
     *
     * @param phone
     * */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets Create Date
     *
     * @return create_date
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * Sets Create Date
     *
     * @param create_date
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * Gets Created By
     *
     * @return created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Sets Created By
     *
     * @param created_by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Gets Last Update
     *
     * @return last_update
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Sets Last Update
     *
     * @param last_update
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * Gets Last Updated By
     *
     * @return Last Updated By
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Set Last Updated By
     *
     * @param last_updated_by
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * Gets Division ID
     *
     * @return division_id
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * Sets Division ID
     *
     * @param division_id
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }
}
