package dataLeaf;

import java.sql.*;

/**
 * A singleton User class used to represent information and
 * privileges of a user interacting with the program, also
 * used to query the database in order to update the current user
 * information based on log-in information.
 * 
 * @author wtobin
 */
public class User {
    private String firstName;
    private String lastName;
    private String alias;
    private String title;
    private String password;
    private String website;
    private String email;
    private boolean verified = false;
    private String query; //make into a query object later on
    private static final User instance = new User();

    public enum AccessLevel{
    NORMAL, POWER, MOD}

    private AccessLevel access = AccessLevel.NORMAL;

    private User(){}

    /**
     * Used to retrieve the only instance of this class.
     * @return the single class instance
     */
    public static User getInstance() {
        return instance;
    }

    /**
     * Returns a descriptive string containing most of the private data members,
     * formatted for easy display.
     * @return the descriptive string
     */
    @Override
    public String toString() {
        String returnString = (title == null || title.isEmpty() ? "" : title + " " );
        returnString += (firstName == null || firstName.isEmpty() ? "" : firstName + " " );
        returnString += (lastName == null || lastName.isEmpty() ? "" : lastName + " " );
        returnString += (alias == null || alias.isEmpty() ? "" : "(" + alias + ") " );
        returnString += (website == null || website.isEmpty() ? "" : "Website: " + website + " ");
        returnString += (email == null || email.isEmpty() ? "" : "Email: " + email);

        return returnString;
    }

    /**
     * Returns the first name of the currently active user, or an empty string
     * if a user is not logged in or does not have a first name.
     * @return the string containing the name
     */
    public String getFirstName() { return (firstName == null ? "" : firstName); }

    /**
     * Returns the last name of the currently active user, or an empty string
     * if a user is not logged in or does not have a first name.
     * @return the string containing the name
     */
    public String getLastName() { return (lastName == null ? "" : lastName); }

    /**
     * Returns the username of the currently active user, or an empty string
     * if a user is not logged in.
     * @return the string containing the username
     */
    public String getAlias() { return (alias == null ? "" : alias); }

    /**
     * Returns the professional title of the currently active user,
     * or an empty string if a user is not logged in or does not have
     * a professional title.
     * @return the string containing the professional title
     */
    public String getTitle() { return (title == null ? "" : title); }

    /**
     * Returns the URL of a personal website of the currently active user,
     * or an empty string if a user is not logged in or does not have a
     * personal website specified.
     * @return the string containing the personal website URL
     */
    public String getWebsite() { return (website == null ? "" : website); }

    /**
     * Returns the full email address of the currently active user,
     * or an empty string if a user is not currently logged in.
     * @return the string containing the full email address
     */
    public String getEmail() { return (email == null ? "" : email); }

    /**
     * Returns a string describing the level of access of the current user.
     * @return the string containing the access level description
     */
    public String getAccessLevel() { 
        return (access == AccessLevel.NORMAL) ? "Normal" :
               (access == AccessLevel.POWER) ? "Power" :
               (access == AccessLevel.MOD) ? "Moderator" : "Error";
    }

    /**
     * Used to set the username of the current user, if the user is currently
     * logged in, this call will not overwrite the previous username, you must
     * log-out (Reset()) in order to re-set this value.
     * @param inAlias a string containing the username
     * @return true if the value was changed, false if not
     */
    public boolean setAlias(String inAlias) {
        alias = (!verified) ? inAlias : alias;
        return !verified;
    }

    /**
     * Used to set the password of the current user, if the user is currently
     * logged in, this call will not overwrite the previous password, you must
     * log-out (Reset()) in order to re-set this value.
     * @param inPass a string containing the password
     * @return true if the value was changed, false if not
     */
    public boolean setPassword(String inPass) {
        password = (!verified) ? inPass : password;
        return !verified;
    }


    /**
     * Used to query the database to see if the current username and password
     * correspond to a row in the User table, updates the current user information
     * in the case that a single row is returned from the database.
     *
     * In the event of a failed login, any data already present in the object is
     * retained rather than overwritten.  You may wish to make a Reset() call in
     * the event this method returns false.
     * 
     * @param connection A JDBC connection object on which to run the query
     * @return true if a row was found corresponding to the entered data, false otherwise
     */
    public boolean login(Connection connection){
        if(connection == null) return false;
        
        query = "SELECT * FROM User WHERE User.alias = '" + alias + "' and User.password = '" + password + "'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //hacky but does the job
            int count = 0;
            while(rs.next()) {
                count++;
            }

            rs.first();

            if(count != 1) { //if more or less than one row was returned
                return false;
            } else {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                title = rs.getString("title");
                website = rs.getString("website");
                email = rs.getString("email");
                int aL = rs.getInt("access");
                access = aL == 2 ? AccessLevel.POWER : aL == 3 ? AccessLevel.MOD : AccessLevel.NORMAL;
                
                verified = true;
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return false;
    }

    /**
     * Used to reset all private fields of the class to their default state,
     * which equates to logging out of the system.
     */
    public void reset() {
        firstName = lastName = alias = title = password = website = email = "";
        access = AccessLevel.NORMAL;
        verified = false;
    }

    private void updateObservationResults(/*Search aSearch*/) {
        /*
         * Observation[] observations = aSearch.getObservationResults()
         * ...and I don't know where to go from here...
         */
    }
}
