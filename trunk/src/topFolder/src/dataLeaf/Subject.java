package dataLeaf;

/**
 *  Creation and editing of Subjects.
 *
 *  @author Mike Despars
 *  @date 3/21/11
 *  @version 1
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
	
    Subject() {
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
	
    /**
     * Gets the sub_id of the subject.
     * 
     * @return databaseID An integer containing the subject ID of the subject.
     */
    public int getDatabaseID() {return databaseID;}

    /**
     * Gets the family of the subject.
     *
     * @return family A string containing the family name.
     */
    public String getFamily() {return family;}

    /**
     * Gets the genus of the subject.
     *
     * @return genus A string containing the genus name.
     */
    public String getGenus() {return genus;}

    /**
     * Gets the species of the subject.
     *
     * @return species A string containing the species.
     */
    public String getSpecies() {return species;}

    /**
     * Gets the common name of the subject.
     *
     * @return commonName A string containing the common name.
     */
    public String getCommonName() {return commonName;}

    /**
     * Gets the jepson link of the subject.
     *
     * @return jepsonLink A string containing the jepson link.
     */
    public String getJepsonLink() {return jepsonLink;}

    /**
     * Gets the photo of the subject.
     *
     * @return photo An array of bytes representing the BLOB object.
     */
    public byte[] getPhoto() {return photo;}

    /**
     * Gets the lifeform of the subject.
     *
     * @return lifeform a string containing the lifeform.
     */
    public String getLifeform()	{return	lifeform;}

    /**
     * Gets the low elevation range of the subject.
     *
     * @return rangeLow A integer representing the low elevation.
     */
    public int getRangeLow() {return rangeLow;}

    /**
     * Gets the high elevation range of the subject.
     *
     * @return rangeHigh A integer representing the high elevation.
     */
    public int getRangeHigh() {return rangeHigh;}

    /**
     * Sets the sub_id of the subject.
     *
     * @param dbID An integer that represents the database ID.
     */
    public void	setDatabaseID(int dbID) {databaseID = dbID;}

    /**
     * Sets the family of the subject.
     *
     * @param fam A string containing the family.
     */
    public void	setFamily(String fam) {family = fam;}

    /**
     * Sets the genus of the subject.
     *
     * @param gen A string containing the genus.
     */
    public void	setGenus(String gen) {genus = gen;}

    /**
     * Sets the species of the subject.
     *
     * @param spec A string containing the species.
     */
    public void	setSpecies(String spec)	{species = spec;}

    /**
     * Sets the common name of the subject.
     *
     * @param com A string containing the common name.
     */
    public void	setCommonName(String com) {commonName = com;}

    /**
     * Sets the jepson link of the subject.
     *
     * @param jep A string containing the jepson link.
     */
    public void	setJepsonLink(String jep) {jepsonLink = jep;}

    /**
     * Sets the lifeform of the subject.
     *
     * @param form A string containing the lifeform.
     */
    public void	setLifeform(String form) {lifeform = form;}

    /**
     * Sets the low elevation range for the subject.
     *
     * @param rLow A integer representing the low elevation range.
     */
    public void	setRangeLow(int rLow) {rangeLow = rLow;}

    /**
     * Sets the high elevation range for the subject.
     *
     * @param rHigh A integer representing the high elevation range.
     */
    public void	setRangeHigh(int rHigh) {rangeHigh = rHigh;}

    /**
     * Sets the photo for the subject.
     *
     * @param pho An array of bytes representing the BLOB object.
     */
    public void	setPhoto(byte[] pho) {
        int len = pho.length;
	photo = new byte[len];
	photo = pho;
    }
}	