package dataLeaf;

/*
	Creation and editing of Subjects
	
	author	Mike Despars
	date	3/21/10
	rev		1
*/

class Subject
{
	private int 	databaseID;
	private String 	family;
	private String	genus;
	private String 	species;
	private String	commonName;
	private String	jepsonLink;
	private byte[]	photo;
	private String	lifeform;
	private int     rangeLow;
	private int     rangeHigh;
	
	Subject()
	{
		databaseID = -1;
		family = "";
		genus = "";
		species = "";
		commonName = "";
		jepsonLink = "";
		lifeform = "";
		rangeLow = 0;
		rangeHigh = 0;
	}
	
	// Getter Methods
	public int		getDatabaseID()		{return databaseID;}
	public String	getFamily()			{return family;}
	public String	getGenus()			{return genus;}
	public String	getSpecies()		{return species;}
	public String	getCommonName()		{return commonName;}
	public String	getJepsonLink()		{return jepsonLink;}
	public byte[]	getPhoto()			{return	photo;}
	public String	getLifeform()		{return	lifeform;}
	public int		getRangeLow()		{return rangeLow;}
	public int		getRangeHigh()		{return rangeHigh;}
	
	//Setter Methods
	public void		setDatabaseID(int dbID)			{databaseID = dbID;}
	public void		setFamily(String fam)			{family = fam;}
	public void		setGenus(String gen)			{genus = gen;}
	public void		setSpecies(String spec)			{species = spec;}
	public void		setCommonName(String com)		{commonName = com;}
	public void		setJepsonLink(String jep)		{jepsonLink = jep;}
	public void		setLifeform(String form)		{lifeform = form;}
	public void		setRangeLow(int rLow)			{rangeLow = rLow;}
	public void		setRangeHigh(int rHigh)			{rangeHigh = rHigh;}
	
	public void	setPhoto(byte[] pho)			
				{
					int len = pho.length;
					photo = new byte[len];
					photo = pho;
				}
}	