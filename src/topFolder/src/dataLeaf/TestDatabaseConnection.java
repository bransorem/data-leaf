import java.sql.*;


class TestDatabaseConnection {
  
  public static void main(String args[]) {
	  
	  System.out.println("Instantiated: \t" + DatabaseConnection.getInstance(1).isInstantiated);
	  //System.out.println(DatabaseConnection.getInstance(1).getConnection());
	  System.out.println("Driver: \t" + DatabaseConnection.getInstance(1).getDriver());
	  System.out.println("Connect string: " + DatabaseConnection.getInstance(1).getConnectString());
	  System.out.println("DB name: \t" + DatabaseConnection.getInstance(1).databaseName);
	  System.out.println("Active: \t" + DatabaseConnection.getInstance(1).activeConnection);
	  System.out.println("Get Conn.: \t" + DatabaseConnection.getInstance(1).getConnection());
	  System.out.println("Connection: \t" + DatabaseConnection.getInstance(1).conn);
	  
	  System.out.println("\n\nend of test");	  
  }
  
}
