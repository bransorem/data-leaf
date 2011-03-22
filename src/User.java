package cs435;

import java.sql.*;

/**
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

    private User() {}

    public static User getInstance() {
        return instance;
    }

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

    public String getFirstName() { return (firstName == null ? "" : firstName); }
    public String getLastName() { return (lastName == null ? "" : lastName); }
    public String getAlias() { return (alias == null ? "" : alias); }
    public String getTitle() { return (title == null ? "" : title); }
    public String getWebsite() { return (website == null ? "" : website); }
    public String getEmail() { return (email == null ? "" : email); }

    //We can only set the alias/pass when we're not logged in, if we are, we have to reset (disconnect) in order to be able to set them again
    public void setAlias(String inAlias) { 
        alias = (!verified) ? inAlias : alias;
    }
    
    public void setPassword(String inPass) { 
        password = (!verified) ? inPass : password;
    }


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
                
                verified = true;
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return false;
    }

    public void reset() {
        firstName = lastName = alias = title = password = website = email = "";
        verified = false;
    }
}
