package dataleaf;

import java.sql.*;

abstract class Query {

	protected String query;
	private ResultSet results;
	
	protected ResultSet executeQuery() {
		Connection conn = getConnection();
		try {
			  Statement db_statement = conn.createStatement();
			  results = db_statement.executeQuery
				(query);
		}
		catch (Exception e) {
			  System.out.println(e);
		}
		return results;		
	}
	
	abstract protected String buildQuery(String providedStuff);
	
	public ResultSet getResults(){
		return results;
	}
	public String getQuery() {
		return query;
	}
	
	private Connection getConnection() {
	  DatabaseConnection dbc = DatabaseConnection.getInstance(1);
	  System.out.println(dbc.getConnectString() + " " +dbc.getDriver() );
	  Connection conn = dbc.connect();
	  if (conn == null)
		  System.out.println("No connection");
	  else
		  System.out.println("Connection ");
	  return conn;
	}
}
	  
