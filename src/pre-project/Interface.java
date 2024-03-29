import java.sql.*;

/*
*	Interface Class
*
*	@author Arron Shelley
*/

class Interface 
{
	// Private Variables
	Search currentSearch;
	User theUser = User.getInstance();
	DatabaseConnection connection;
	Subject currentSubject;
	Observation currentObservation;
     
	// Getter methods
    public Search getSearch()  	{ return currentSearch; }
	public User getUser()  	  	{ return theUser; }
    public DatabaseConnection getDBCon()  { return connection; }
    public Subject getCurSubject()  { return currentSubject; }
    public Observation getObs()  { return currentObservation; }
	
	// Setter Methods
 
	public void executeLogin() 
	{
		theUser.login(connection);
	}
	  
	public void executeLogout() 
	{
		theUser.reset()
	}
	  
	public void executeSearch() 
	{
		
	}
	  
	public void buildSearch() 
	{
		
	}
	  
	private bool validateLoginInput()
	{
		
	}
	  
	private bool validateSearchInput()
	{
		
	}  

	private bool verifyAccessLevel()
	{
		
	}
	  
	private void updateSubjectResults()
	{
		
	}
}