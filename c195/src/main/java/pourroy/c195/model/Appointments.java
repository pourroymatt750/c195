package pourroy.c195.model;

import java.security.Timestamp;
import java.util.Date;

/**
 * Serves as a Model of Appointments made by Users and Customers
 *
 * @author matthewpourroy
 */
public class Appointments {
    /**
     * INT(10) Appointment ID
     * Primary Key
     */
    private int appointment_id;

    /**
     * VARCHAR(50) Title
     */
    private String title;

    /**
     * VARCHAR(50) Description
     */
    private String description;

    /**
     * VARCHAR(50) Location
     */
    private String appointment_location;

    /**
     * VARCHAR(50) Type
     */
     private String type;

     /**
      * DATETIME Start
      */
     private Date start;

     /**
      * DATETIME End
      */
     private Date end;

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
      * INT(10) Customer ID
      * Foreign Key to Customers Model
      */
     private int customer_id;

     /**
      * INT(10) User ID
      * Foreign Key to Users Model
      */
     private int user_id;

    /**
     * INT(10) Contact ID
     * Foreign Key Contacts Model
     */
    private int contact_id;

    /**
     * VARCHAR(50) Contact Name
     * Comes from Countries Model
     */
    private String contact_name;

    /**
     * Constructor
     *
     * @param appointment_id is Appointment ID
     * @param title is Title
     * @param description is Description
     * @param appointment_location is Location
     * @param type is Type
     * @param start is Start
     * @param end is End
     * @param create_date is Create Date
     * @param created_by is Created By
     * @param last_update is Last Update
     * @param last_updated_by is Last Updated By
     * @param customer_id is Customer ID
     * @param user_id is User ID
     * @param contact_id is Contact ID
     */
    public Appointments(int appointment_id, String title, String description, String appointment_location, String type, Date start, Date end, Date create_date, String created_by, Timestamp last_update, String last_updated_by, int customer_id, int user_id, int contact_id) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.appointment_location = appointment_location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact_id = contact_id;
    }

    /**
     * Constructor for AppointmentsDoa
     *
     * @param appointment_id is Appointment ID
     * @param title is Title
     * @param description is Description
     * @param appointment_location is Location
     * @param contact_name is Contact Name
     * @param type is Type
     * @param start is Start
     * @param end is End
     * @param customer_id is Customer ID
     * @param user_id is User ID
     */
    public Appointments(int appointment_id, String title, String description, String appointment_location, String contact_name, String type, Date start, Date end,int customer_id, int user_id) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.appointment_location = appointment_location;
        this.contact_name = contact_name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_id = customer_id;
        this.user_id = user_id;
    }

    /**
     * Gets Contact Name
     * Comes from Contacts Model
     *
     * @return contact_name
     */
    public String getContact_name() { return contact_name; }

    /**
     * Sets Contact Name
     * Comes from Contacts Model
     *
     * @param contact_name is Contact Name
     */
    public void setContact_name(String contact_name) { this.contact_name = contact_name; }

    /**
     * Gets Appointment ID
     * Primary Key
     *
     * @return appointment_id
     */
    public int getAppointment_id() {
        return appointment_id;
    }

    /**
     * Sets Appointment ID
     * Primary Key
     *
     * @param appointment_id
     */
    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    /**
     * Gets Title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets Title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets Description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets Description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets Location
     *
     * @return appointment_location
     */
    public String getAppointment_location() { return appointment_location; }

    /**
     * Sets Location
     *
     * @param appointment_location
     */
    public void setAppointment_location(String appointment_location) {
        this.appointment_location = appointment_location;
    }

    /**
     * Gets Type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets Type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets Start
     *
     * @return start
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets Start
     *
     * @param start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Gets End
     *
     * @return end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Sets End
     *
     * @param end
     */
    public void setEnd(Date end) {
        this.end = end;
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
     * Gets Customer ID
     * Foreign Key to Customers Model
     *
     * @return customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * Sets Customer ID
     * Foreign Key to Customers Model
     *
     * @param customer_id
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * Gets User ID
     * Foreign Key to Users Model
     *
     * @return user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets User ID
     * Foreign Key to Users Model
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets Contract ID
     * Foreign Key to Contacts Model
     *
     * @return contact_id
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * Sets Contact ID
     * Foreign Key to Contacts Model
     *
     * @param contact_id
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
