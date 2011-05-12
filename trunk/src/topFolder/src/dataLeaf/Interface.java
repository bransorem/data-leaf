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
        private static final Interface instance = new Interface();
     
	// Getter methods
        public Search getSearch()  	{ return currentSearch; }
	public User getUser()  	  	{ return theUser; }
        public DatabaseConnection getDBCon()  { return connection; }
        public Subject getCurSubject()  { return currentSubject; }
        public Observation getObs()  { return currentObservation; }

        public Interface getInstance(prototype gui) {
            newGui = gui;
//            return instance;
//        }
//
//	// Setter Methods
//
//        public Interface(){
            Query query = new Query();
            theUser = User.getInstance();
            connection.getInstance(dbID);
            connection = DatabaseConnection.getInstance(1);
            Connection conn = connection.getConnection();
            ResultSet rs = query.genusQuery(conn);
            System.out.println("Interface building Genus dropdown");

            try {
            Subject[] subjectResults = new Subject[5000];
            int i = 0;
            rs.beforeFirst();
            while(rs.next()) {
                Subject sub = new Subject();
//                Observation obs = new Observation();
//                Location loc = new Location();
                sub.setGenus(rs.getString("genus"));
                subjectResults[i] = sub;
                i++;

                }

            System.out.println("Finished building subject results"+ i);
            for (int j = 1; j < i ; j++){
                    String genusname = subjectResults[j-1].getGenus();
                    newGui.genusDropBox.insertItemAt(genusname,j);
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }
            return instance;
        }
	public void executeLogin() 
	{
            theUser.setAlias((String) newGui.usernameTextBox.getText());
            theUser.setPassword(userpass = newGui.passwordTextBox.getText());
            connection.getInstance(dbID);
            newGui.usernameTextBox.setText("");
            newGui.passwordTextBox.setText("");

            connection = DatabaseConnection.getInstance(1);
            boolean result = theUser.login(connection);
            if (result)
                newGui.usernameLabel.setText(theUser.getAlias());
              else newGui.usernameLabel.setText("Failed to Login");
	}
	  
	public void executeLogout() 
	{
            theUser.reset();
            newGui.usernameLabel.setText("Guest");
	}
	  
	public void executeSearch()
	{
            System.out.println("Interface executeSearch");
            connection = DatabaseConnection.getInstance(1);
            currentSearch.executeSearch(connection);
            arrOfSubjects = currentSearch.getSubjectResults();
            String arrSpecies[] = new String[arrOfSubjects.length];
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
            String spec = (String) newGui.speciesDropBox.getSelectedItem();
            String gen = (String) newGui.genusDropBox.getSelectedItem();
            String obs = (String) newGui.observerDropBox.getSelectedItem();
            String newDate = newGui.dateTextBox.getText();

//            Float lat = new Float(newGui.latitudeTextBox.getText());
//            Float lon = new Float(newGui.longitudeTextBox.getText());
//            int elev = 0;
//
//            loc.setLatitude(lat);
//            loc.setLongitude(lon);
//            loc.setElevation(elev);

            currentSearch = new Search(spec, gen, obs, newDate);
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