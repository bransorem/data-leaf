package dataLeaf;

class TestDatabaseConnection {
  
  public static void main(String args[]) {

      DatabaseConnection conn = DatabaseConnection.getInstance(1);
	  
	  System.out.println("Instantiated: \t" + conn.isInstantiated);
	  System.out.println("Driver: \t" + conn.getDriver());
	  System.out.println("Connect string: " + conn.getConnectString());
	  System.out.println("DB name: \t" + conn.databaseName);
	  System.out.println("Active: \t" + conn.activeConnection);
	  System.out.println("Get Conn.: \t" + conn.getConnection());
	  System.out.println("Active: \t" + conn.activeConnection);
	  
 	  System.out.println("\n\nend of test");
  }
  
}