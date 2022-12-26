package pourroy.c195.model;

/**
 * Serves as a Model of Contacts
 *
 * @author matthewpourroy
 */
public class Contacts {
    /**
     * INT(10) Contact ID
     * Primary Key
     */
    private int contact_id;

    /**
     * VARCHAR(50) Contact Name
     */
    private String contact_name;

    /**
     * VARCHAR(50) Email
     */
    private String email;

    /**
     * Constructor
     *
     * @param contact_id is Contact ID
     * @param contact_name is Contact Name
     * @param email is Email
     */
    public Contacts(int contact_id, String contact_name, String email) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.email = email;
    }

    /**
     * Overrides the ComboBox default setting that automatically converts strings
     * to hash codes of the pointer reference.
     * @return contact_name is Contact Name
     */
    @Override
    public String toString() { return (contact_name); }

    /**
     * Constructor for CountriesDoa
     * @param contact_name is Contact Name in Add/Modify Appointment ComboBox
     */
    public Contacts(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * Gets Contract ID
     * Primary Key
     *
     * @return contact_id
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * Sets Contact ID
     * Primary Key
     *
     * @param contact_id
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * Gets Contact Name
     *
     * @return contact_name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * Sets Contact Name
     *
     * @param contact_name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * Gets Email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets Email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
