package dataLeaf;

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
        int dbID;
        //Prototype newGui;
        Subject[] arrOfSubjects;
        Observation[] arrOfObs;
        Location loc;
     
	// Getter methods
        public Search getSearch()  	{ return currentSearch; }
	public User getUser()  	  	{ return theUser; }
        public DatabaseConnection getDBCon()  { return connection; }
        public Subject getCurSubject()  { return currentSubject; }
        public Observation getObs()  { return currentObservation; }
	
	// Setter Methods
 
	public void executeLogin() 
	{
            connection.getInstance(dbID);
            theUser.login(connection);
	}
	  
	public void executeLogout() 
	{
            theUser.reset();
	}
	  
	public void executeSearch()
	{
            currentSearch.executeSearch();
            arrOfSubjects = currentSearch.getSubjectResults();
            newGui.searchResultList.setListData(arrOfSubjects);
            arrOfObs = currentSearch.getObservationResults();
            for(int i = 0; i < arrOfObs.length; i++)
            {
                newGui.observationTable.setValueAt(arrOfObs[i].getSubject,i,0);
                newGui.observationTable.setValueAt(arrOfObs[i].getAuthor(),i,2);
                newGui.observationTable.setValueAt(arrOfObs[i].getDate(),i,3);
                newGui.observationTable.setValueAt(arrOfObs[i].getLocation(),i,4);
            }
                
	}
	  
	public void buildSearch() 
	{
            String spec = newGui.speciesDropBox.getSelectedItem();
            String gen = newGui.genusDropBox.getSelectedItem();
            String obs = newGui.observationDropBox.getSelectedItem();
            String newDate = newGui.dateTextBox.getText();
            
            float lat = newGui.latitudeTextBox.getText();
            float lon = newGui.longitudeTextBox.getText();
            int elev = 0;            
            
            loc.setLatitude(lat);
            loc.setLongitude(lon);
            loc.setElevation(elev);

            currentSearch = Search(spec, gen, obs, newDate, loc);
	}
	  
	private boolean validateLoginInput()
	{
            return true;
	}
	  
	private boolean validateSearchInput()
	{
            return true;
	}  

	private boolean verifyAccessLevel()
	{
            return true;
	}
	  
}