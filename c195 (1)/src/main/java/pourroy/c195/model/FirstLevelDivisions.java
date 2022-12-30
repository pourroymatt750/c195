package pourroy.c195.model;

import java.security.Timestamp;
import java.util.Date;

/**
 * Serves as a Model of First-Level Divisions
 *
 * @author matthewpourroy
 */
public class FirstLevelDivisions {
    /**
     * INT(10) Division ID
     * Primary Key
     */
    private int division_id;

    /**
     * VARCHAR(50) Division
     */
    private String division;

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
     * INT(10) Country ID
     * Foreign Key to Countries Model
     */
    private int country_id;

    /**
     * Constructor
     *
     * @param division_id is Division ID
     * @param division is Division
     * @param create_date is Create Date
     * @param created_by is Created By
     * @param last_update is Last Update
     * @param last_updated_by is Last Updated By
     * @param country_id is Country ID
     */
    public FirstLevelDivisions(int division_id, String division, Date create_date, String created_by,
                               Timestamp last_update, String last_updated_by, int country_id) {
        this.division_id = division_id;
        this.division = division;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.country_id = country_id;
    }

    /**
     * Overrides the ComboBox default setting that automatically converts strings
     * to hash codes of the pointer reference.
     * @return division is Division
     */
    @Override
    public String toString() { return (division); }

    /**
     * Constructor for FirstLevelDivisionsDoa
     * @param division is Division in Add/Modify Customer ComboBoxes
     */
    public FirstLevelDivisions(String division, int division_id) {
        this.division = division;
        this.division_id = division_id;
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

    /**
     * Gets Division
     *
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets Division
     *
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
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

    /**
     * Gets Country ID
     * Foreign Key to Countries Model
     *
     * @return country_id
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * Sets Country ID
     * Foreign Key to Countries Model
     *
     * @param country_id
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
