package dataLeaf;

/*
*	Interface Class
*
*	@author Arron Shelley
*/

import java.util.Date;
import java.text.*;
import java.sql.*;

class Interface 
{
	// Private Variables
	private Search currentSearch;
	private User theUser = User.getInstance();
	private DatabaseConnection connection;
	private Subject currentSubject;
	private Observation currentObservation;
        private int dbID;
        private Subject[] arrOfSubjects;
        private Observation[] arrOfObs;
        private Location loc;
        private prototype newGui;
        private String userpass;
     
	// Getter methods
        public Search getSearch()  	{ return currentSearch; }
	public User getUser()  	  	{ return theUser; }
        public DatabaseConnection getDBCon()  { return connection; }
        public Subject getCurSubject()  { return currentSubject; }
        public Observation getObs()  { return currentObservation; }
	
	// Setter Methods

        public Interface(prototype gui){
            newGui=gui;
            theUser = User.getInstance();
            System.out.println("finishing constructor");
    //        theUser.userDialog();
        }


	public void executeLogin() 
	{
            theUser.setAlias((String) newGui.usernameTextBox.getText());
          //  theUser.setPassword(userpass = newGui.passwordTextBox.getPassword().toString());
            theUser.setPassword(userpass = newGui.passwordTextBox.getText());
            connection.getInstance(dbID);
           // theUser.login(connection);

            connection = DatabaseConnection.getInstance(1);
            boolean result = theUser.login(connection);
            if (result)
                newGui.dateTextBox.setText("User: " + theUser.getAlias());
              else newGui.dateTextBox.setText("no result");
            
	}
	  
	public void executeLogout() 
	{
            theUser.reset();
	}
	  
	public void executeSearch()
	{
            System.out.println("Interface executeSearch");
            connection = DatabaseConnection.getInstance(1);
            currentSearch.executeSearch(connection);
            arrOfSubjects = currentSearch.getSubjectResults();
            String arrSpecies[] = new String[10];
            for(int i =0; i < arrOfSubjects.length; i++)
            {
                arrSpecies[i] = arrOfSubjects[i].getSpecies();
            }
            newGui.speciesResultList.setListData(arrSpecies);
          //  arrOfObs = currentSearch.getObservationResults();
            
          //  for(int i = 0; i < arrOfObs.length; i++)
          //  {
             //   newGui.observationTable.setValueAt(arrOfObs[i].getGenus(),i,0);
          //      newGui.observationTable.setValueAt(arrOfObs[i].getSubject(),i,1);
          //      newGui.observationTable.setValueAt(arrOfObs[i].getAuthor(),i,2);
          //      newGui.observationTable.setValueAt(arrOfObs[i].getDate(),i,3);
          //      newGui.observationTable.setValueAt(arrOfObs[i].getLocation(),i,4);
          //  }
                
	}
	  
	public void buildSearch() 
	{
            System.out.println("Interface buildSearch");
            String spec = (String) newGui.speciesDropBox.getSelectedItem();
            String gen = (String) newGui.genusDropBox.getSelectedItem();
            String obs = (String) newGui.observerDropBox.getSelectedItem();
            
            /* This line of code was giving me issues because it is not sure
             * of the parse method for DateFormat... if anyone would want to fix that
             * then it should work
             * Date newDate = new DateFormat.parse(newGui.dateTextBox.getText());
             */
           // Date newDate = new Date();
            String newDate = new String();

//            Float lat = new Float(newGui.latitudeTextBox.getText());
//            Float lon = new Float(newGui.longitudeTextBox.getText());
            int elev = 0;            

//            loc.setLatitude(lat);
//            loc.setLongitude(lon);
//            loc.setElevation(elev);

           // currentSearch = new Search(spec, gen, obs, newDate, loc);
            currentSearch = new Search(spec, gen, obs, newDate);
            System.out.println("Back in buildSearch after Search constructor");
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