package pourroy.c195.model;

import java.security.Timestamp;
import java.util.Date;

/**
 * Serves as a Model of Users
 *
 * @author matthewpourroy
 */
public class Users {
    /**
     * INT(10) User ID
     */
    private static int user_id;

    /**
     * VARCHAR(50) UNIQUE User Name
     */
    private static String user_name;

    /**
     * TEXT Password
     */
    private static String password;

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
     * @param user_id is User ID
     * @param user_name is User Name
     * @param password is Password
     * @param create_date is Create Date
     * @param created_by is Created By
     * @param last_update is Last Update
     * @param last_updated_by is Last Updated By
     */
    public Users(int user_id, String user_name, String password, Date create_date, String created_by, Timestamp last_update, String last_updated_by) {
        super();
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    /**
     * UserDoa Constructor
     */
    public Users(int user_id) {
    }

    /**
     * Gets User ID
     *
     * @return User ID
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets User ID
     *
     * @param user_id
     */
    public static void setUser_id(int user_id) {
        Users.user_id = user_id;
    }

    /**
     * Gets User Name
     *
     * @return user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Sets User Name
     *
     * @param user_name
     */
    public static void setUser_name(String user_name) {
        Users.user_name = user_name;
    }

    /**
     * Gets Password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
