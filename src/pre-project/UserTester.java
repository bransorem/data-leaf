package cs435;

import java.sql.*;

/**
 *
 * @author wtobin
 */
public class UserTester {

    public static void main(String args[]) {
        //Retrieve the only instance of User
        User test1 = User.getInstance();

        //Tests before setting any values
        String firstName = test1.getFirstName();
        String lastName = test1.getLastName();
        String alias = test1.getAlias();
        String title = test1.getTitle();
        String website = test1.getWebsite();
        String email = test1.getEmail();
        String toString = test1.toString();

        //Check to make sure everything is empty (not null!)
        System.out.println("Uninitialized First Name: " + (firstName.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Last Name: " + (lastName.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Alias: " + (alias.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Title: " + (title.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Website: " + (website.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Email: " + (email.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized toString(): " + (toString.isEmpty() ? "Passed" : "Failed"));
         System.out.println("");

        //Setup a database connection to test the login functionality
        Connection connection = null;

        //Attempt to login with null connection
        System.out.println("Uninitialized connection login: " + (test1.login(connection) ? "Failed" : "Passed") );
        System.out.println("");

        //Connect to the database
        String url = "jdbc:mysql://nrs-projects.humboldt.edu:3306/";
        String dbName = "plants";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "dev435";
        String password = "botany";
        try{
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Database connection established");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        //Attempt to login without setting alias/password
        System.out.println("Uninitialized user/pass login: " + (test1.login(connection) ? "Failed" : "Passed") );
        System.out.println("");

        test1.setAlias("fail");
        test1.setPassword("fail");

        //Test login with incorrect info
        System.out.println("Initialized (incorrect user/pass) login: " + (test1.login(connection) ? "Failed" : "Passed") );
        test1.setAlias("tester");
        
        System.out.println("Initialized (incorrect pass) login: " + (test1.login(connection) ? "Failed" : "Passed") );

        test1.setAlias("fail");
        test1.setPassword("testPass");
        System.out.println("Initialized (incorrect user) login: " + (test1.login(connection) ? "Failed" : "Passed") );
        System.out.println();

        //Set the correct info to login
        test1.setAlias("tester");
        test1.setPassword("testPass");

        //Test that the login info is set
        System.out.println("GetAlias test: " + ( (test1.getAlias() == null ? "tester" == null : test1.getAlias().equals("tester") ) ? "Passed" : "Failed" ) );
        System.out.println("Initialized (correct) login: " + (test1.login(connection) ? "Passed" : "Failed") );
        System.out.println();

        //Test that the member fields have all been loaded with the correct information
        firstName = test1.getFirstName();
        lastName = test1.getLastName();
        alias = test1.getAlias();
        title = test1.getTitle();
        website = test1.getWebsite();
        email = test1.getEmail();
        toString = test1.toString();
        
        System.out.println("Initialized First Name: " + (firstName == null ? "testy" == null : firstName.equals("testy") ? "Passed" : "Failed"));
        System.out.println("Initialized Last Name: " + (lastName == null ? "mctesterson" == null : lastName.equals("mctesterson") ? "Passed" : "Failed"));
        System.out.println("Initialized Alias: " + (alias == null ? "tester" == null : alias.equals("tester") ? "Passed" : "Failed"));
        System.out.println("Initialized Title: " + (title == null ? "Dr" == null : title.equals("Dr") ? "Passed" : "Failed"));
        System.out.println("Initialized Website: " + (website == null ? "www.testymctesterson.com" == null : website.equals("www.testymctesterson.com") ? "Passed" : "Failed"));
        System.out.println("Initialized Email: " + (email == null ? "testy@mctesterson.com" == null : email.equals("testy@mctesterson.com") ? "Passed" : "Failed"));
        System.out.println("Initialized toString(): " + 
                (toString == null ? "Dr testy mctesterson (tester) Website: www.testymctersterson.com Email: test@mctesterson.com" == null :
                    email.equals("Dr testy mctesterson (tester) Website: www.testymctersterson.com Email: test@mctesterson.com") ? "Passed" : "Failed"));
        System.out.println();

        //Attempt to change the alias while logged in
        test1.setAlias("fail");
        System.out.println("Attempt to change alias while logged in: " + (test1.getAlias() == null ? "tester" == null : test1.getAlias().equals("tester") ? "Passed" : "Failed"));

        //Reset the object
        test1.reset();

        //Check to make sure everything is empty (not null!)
        firstName = test1.getFirstName();
        lastName = test1.getLastName();
        alias = test1.getAlias();
        title = test1.getTitle();
        website = test1.getWebsite();
        email = test1.getEmail();
        toString = test1.toString();
        
        System.out.println("Uninitialized First Name: " + (firstName.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Last Name: " + (lastName.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Alias: " + (alias.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Title: " + (title.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Website: " + (website.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized Email: " + (email.isEmpty() ? "Passed" : "Failed"));
        System.out.println("Uninitialized toString(): " + (toString.isEmpty() ? "Passed" : "Failed"));
        System.out.println("");

        //Close the database connection
        try{
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
