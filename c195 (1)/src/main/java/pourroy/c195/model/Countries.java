package pourroy.c195.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.Timestamp;
import java.util.Date;

/**
 * Serves as a Model of Countries
 *
 * @author matthewpourroy
 */
public class Countries {
    /**
     * INT(10) Country ID
     * Primary Key
     */
    private int country_id;

    /**
     * VARCHAR(50) Country
     */
    private String country;

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
     * Constructor
     *
     * @param country_id is the Country ID
     * @param country is the Country
     * @param create_date is Create Date
     * @param created_by is Created By
     * @param last_update is Last Update
     * @param last_updated_by is Last Updated By
     */
    public Countries(int country_id, String country, Date create_date, String created_by, Timestamp last_update, String last_updated_by) {
        this.country_id = country_id;
        this.country = country;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    /**
     * Overrides the ComboBox default setting that automatically converts strings
     * to hash codes of the pointer reference.
     * @return country is Country
     */
    @Override
    public String toString() { return (country); }

    /**
     * Constructor for CountriesDoa
     * @param country is Country in Add/Modify Customer ComboBoxes
     */
    public Countries(String country, int country_id) {
        this.country_id = country_id;
        this.country = country;
    }


    /**
     * Gets the Country ID
     *
     * @return country_id
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * Sets the Country ID
     *
     * @param country_id
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * Gets the Country
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the Country
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the Create Date function
     *
     * @return create_date
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * Sets the Create Date function
     *
     * @param create_date
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * Gets the user who created the country
     *
     * @return created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Sets the function to who created the country
     *
     * @param created_by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Gets Last Update that user made
     *
     * @return last_update
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Sets the Last Update that user made
     *
     * @param last_update
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * Gets what user last updated the country
     *
     * @return last_updated_by
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Sets what user last updated the country
     *
     * @param last_updated_by
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }
}
