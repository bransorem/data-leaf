package dataLeaf;

import java.sql.*;

/**
	A singleton object that will be used to create a set
	of user specified search results based on criterion
	selected.
	
	@author	Mike Despars
*/

public class Search
{
	private Subject[] subjectResults = new Subject[500];
	private	Observation[] observationResults = new Observation[500];
	private String			species;
	private String			genus;
	private String			observer;
	private Date			date;
	private float			latitude;
	private float			longitude;
	
	Search(String spec, String gen, String obs, Date new_date, float lat, float lon)
	{
		species = spec;
		genus = gen;
		observer = obs;
		date = new_date;
		latitude = lat;
		longitude = lon;
	}
	
	/**
		Returns an array of Subject objects based on
		search criterion.
		@return An array of Subject objects
	*/
	public Subject[]		getSubjectResults()		{return subjectResults;}
	
	/**
		Returns an array of Observation objects based on
		search criterion.
		@return An array of Observation objects.
	*/
	public Observation[]	getObservationResults()	{return ObservationResults;}
	
	/**
		Sets the current search criterion of species to
		the specified input.
		@param A string which contains the species
	*/
	public void			setSpecies(String spec)		{species = spec;}
	
	/**
		Sets the current search criterion of genus to
		the specified input.
		@param A string which contains the genus
	*/
	public void			setGenus(String gen)		{genus = gen;}
	
	/**
		Sets the current search criterion of observer to
		the specified input.
		@param A string which contains the observer
	*/
	public void			setObserver(String obs)		{oberserver = obs;}
	
	/**
		Sets the current search criterion of date to
		the specified input.
		@param A Date object which contains the date
	*/
	public void			setDate(Date new_date)		{date = new_date;}
	
	/**
		Sets the current search criterion of latitude to
		the specified input.
		@param A float which contains the latitude
	*/
	public void			setLatitude(float lat)		{latitude = lat;}
	
	/**
		Sets the current search criterion of longitude to
		the specified input.
		@param A float which contains the longitude
	*/
	public void			setLongitude(float lon)		{longitude = lon;}
	
	/** 
		Takes all search criterion and combines them into
		a string.
	*/
	// public String toString()
	// {
		// String str = species + "," + 
					 // genus + "," + 
					 // oberserver + "," +
					 // date.toString() + "," + 
					 // latitude.toString() + "," +
					 // longitude.toString();
		// return str;
	// }
	
	/** 
		This will create a database connection as well as
		a query object to execute a query based on the search
		criterion. It will build the Subject, Observation, and
		Location objects based on the result set that is returned
		from the query. It will update the Subject array and the
		Observation array.
	*/
	public void executeSearch()
	{
		//Need help here.
		dbID = 1;
		DatabaseConnection dbconn = new DatabaseConnection(dbID);
		Query query = new Query();
		ResultSet results = query.executeQuery(dbconn.connect());
		
		boolean last = false;
		int i = 0;
		results.first();
		while (!last || i < 500)
		{
			Subject sub = new Subject();
			Observation obs = new Observation();
			Location loc = new Location();
			
			loc.setLatitude(results.getInt("loc_lat"));
			loc.setLongitude(results.getInt("loc_long"));
			loc.setElevation(results.getInt("loc_elevation"));
			loc.setDescription(results.getString("loc_description"));
			
			sub.setDatabaseID(results.getInt("sub_id"));
			sub.setFamily(results.getString("family"));
			sub.setGenus(results.getString("genus"));
			sub.setSpecies(results.getString("species"));
			sub.setCommonName(results.getString("common_name"));
			sub.setJepsonLink(results.getString("jepson_link"));
			sub.setLifeform(results.getString("lifeform"));
			sub.setPhoto(results.getBlob("photo"));
			sub.setRangeLow(results.getInt("range_low"));
			sub.setRangeHigh(results.getInt("range_high"));
			
			String subject = results.getString("genus") + results.getString("species");
			obs.setSubject(subject);
			obs.setAuthor(results.getString("author"));
			obs.setDate(results.getDate("date"));
			obs.setQuantity(results.getInt("quantity"));
			obs.setLocation(loc);
			obs.setNotes(results.getString("notes"));
			
			Subject[i] = sub;
			Observation[i] = obs;
			i++;
			
		}
		
	}
}
	
	
	
	
	