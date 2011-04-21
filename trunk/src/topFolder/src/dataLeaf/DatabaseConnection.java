package dataLeaf;

import java.sql.*;

final class DatabaseConnection {

	static boolean activeConnection;
	static String databaseID;
	static String url;
	static String driver;
	static Connection conn;
	static String databaseName = "plants";
	static String userName = "dev435";
	static String userPW = "botany";
	static boolean isInstantiated;
	private static DatabaseConnection instance = null;

	private DatabaseConnection (int requestedDatabaseID){
		activeConnection = false;
		isInstantiated = true;
	}
	 static public synchronized DatabaseConnection getInstance(int dbID) {
		if (instance == null) {
			instance = new DatabaseConnection(dbID);
		}
		String s = instance.getDatabaseInfo(dbID);
		if (s == null)
			System.out.println(dbID + " is not available");
		else {
			int i = s.indexOf('!');
			
			if (i >= 0) {
				url = s.substring(0, i);
				driver = s.substring(i+1);
		    }
		}
		return instance;
	}
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public void setUsername(String uname) {
		userName = uname;
	}

	public void setPassword (String pword){
		userPW = pword;
	}

	  /**
	   * @param A valid dbID value.
	   * @return A concatenated String representing the database
	   * connection string along with the driver
	   * OR null if the ID doesn't match
	   */
	private String  getDatabaseInfo(int databaseID)
	{
	    // use the enum values in our switch statement here
	    switch (databaseID)
	    {
	      case 1:     return "jdbc:mysql://nrs-projects.humboldt.edu:3306/" +
	                         "!" + "com.mysql.jdbc.Driver";
	      default:      return null;
	    }
	}
	
	public String  getDriver() {
		return driver;
	}
	
	public String  getConnectString() {
		return url;
	}

    public Connection connect() {
    	if (!activeConnection) {
    	   try {
              Class.forName(driver);
              conn = DriverManager.getConnection(url+databaseName,userName,userPW);
              activeConnection = true;
    	   }
           catch (Exception e) {
               System.out.println("Exception " + e);
               e.printStackTrace();
           }        // TODO add your handling code here:
    	}
    	return conn;
    }
    
    public Connection getConnection(){
    	if (activeConnection)
    		return conn;
    	else
    		return null;
    }
}