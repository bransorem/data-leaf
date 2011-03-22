/*
	Create and edit a Search
	
	author	Mike Despars
	date	3/21/11
	rev		1
*/

class Search
{
	private Query			query;
	private Subject[]		subjectResults;
	private	Observation[]	observationResults;
	private String			species;
	private String			genus;
	private String			observer;
	private Date			date;
	private float			latitude;
	private float			longitude;
	
	Search(String spec, String gen, String obs, Date new_date, float lat, float lon)
	{
		query = new Query();
		species = spec;
		genus = gen;
		observer = obs;
		date = new_date;
		latitude = lat;
		longitude = lon;
	}
	
	//Getter methods
	public Subject[]		getSubjectResults()		{return subjectResults;}
	public Observation[]	getObservationResults()	{return ObservationResults;}
	
	//Setter methods
	public void			setSpecies(String spec)		{species = spec;}
	public void			setGenus(String gen)		{genus = gen;}
	public void			setObserver(String obs)		{oberserver = obs;}
	public void			setdate(Date new_date)		{date = new_date;}
	public void			setLatitude(float lat)		{latitude = lat;}
	public void			setLongitude(float lon)		{longitude = lon;}
	
	/* toString()
		-No input
		-Returns a string containing all search criteria
	*/
	public String toString()
	{
		String str = species + "," + 
					 genus + "," + 
					 oberserver + "," +
					 date.toString() + "," + 
					 latitude.toString() + "," +
					 longitude.toString();
		return str;
	}
	
	/* executeSearch()
		-No input, values should be set before execution.
		-No output, method will update Subject[] and Observation[].
		-**NEED TO KNOW HOW DATA WILL BE FORMATTED FROM executeQuery().**
	*/
	public void executeSearch()
	{
		
	}
	
	
	
	
	