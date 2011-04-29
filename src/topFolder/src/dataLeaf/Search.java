package dataLeaf;
/**
 *	Builds search results based on user specified criterion.
 *
 *	@author		Mike Despars
 *	@date		4/24/11
 *	@version	2
 */

import java.util.Date;
import java.sql.*;

public class Search {

    private Query query;
    private Subject subjectResults[];
    private Observation[] observationResults;
    private String species;
    private String genus;
    private String observer;
    private Date date;
    private Location location;
    private boolean populated;

    Search(String spec, String gen, String obs, Date newDate, Location loc) {
        query = new Query();
        species = spec == null ? "" : spec;
        genus = gen == null ? "" : gen;
        observer = obs == null ? "" : obs;
        date = newDate == null ? null : newDate;
        location = loc == null ? null : loc;
        populated = false;
    }

    /**
     *  Sets a new location for the search criterion.
     *
     *  @param lat  A float containing the new latitude.
     *  @param lon  A float containing the new longitude.
     *  @param evel A integer value for the elevation.
     */
    public void setLocation(float lat, float lon, int elev) {
        location = new Location(lat, lon, elev);
    }

    /**
     *  Converts the search criterion into a string.
     *
     *  @return str A String containing the search criterion.
     */
    public String toString() {
        String str = species + ","
                + genus + ","
                + observer + ","
                + date.toString() + ","
                + location.toString();
        return str;
    }
    
    /**
     *  Returns the array of subject results based on the search criterion.
     * 
     *  @return subResults An array of Subject objects.
     */    
    public Subject[] getSubjectResults() {
        if(populated) {return subjectResults;}
        else {return null;}
    }
    
    /**
     *  Returns the array of subject results based on the search criterion.
     * 
     *  @return subResults An array of Subject objects.
     */    
    public Observation[] getObservationResults() {
        if(populated) {return observationResults;}
        else {return null;}
    }

    /**
     *  This method will query the database for the specified search criterion
     *  and populate the subject results and observation results arrays.
     *
     *  @param dbconn The current database connection object.
     */
    public void executeSearch(DatabaseConnection dbconn) {
        
        int index = 0;
        String tempArgArray[] = new String[7];
        DataTypes tempArgTypes[] = new DataTypes[7];
        String q = "SELECT Observation.loc_lat, " +
			  "Observation.loc_long, " +
			  "Observation.loc_elevation, " +
			  "Observation.loc_description, " +
			  "Observation.date, " +
			  "Observation.quantity, " +
			  "Observation.notes, " +
			  "Subject.sub_id, " +
			  "Subject.species, " +
			  "Subject.common_name, " +
			  "Subject.jepson_link, " +
			  "Subject.lifeform, " +
			  "Subject.photo, " +
			  "Subject.range_low, " +
			  "Subject.range_high, " +
			  "Family.family, " +
			  "Genus.genus, " +				   
			  "User.alias FROM Observation, Subject, Family, Genus, User WHERE ";
	if (species.equals("")) {
            q = q + "Subject.species = ?";
            tempArgArray[index] = species;
            tempArgTypes[index] = DataTypes.STRING;
            index++;}
        if (genus.equals("")) {
            if (index == 0) {
                q = q + "Genus.genus = ?";
                tempArgArray[index] = genus;
                tempArgTypes[index] = DataTypes.STRING;
                index++;}
            else {
                q = q + " AND Genus.genus = ?";
                tempArgArray[index] = genus;
                tempArgTypes[index] = DataTypes.STRING;
                index++;}}
        if (observer.equals("")) {
            if (index == 0) {
                q = q + "User.alias = ?";
                tempArgArray[index] = observer;
                tempArgTypes[index] = DataTypes.STRING;
                index++;}
            else {
                q = q + " AND User.alias = ?";
                tempArgArray[index] = observer;
                tempArgTypes[index] = DataTypes.STRING;
                index++;}}
        if (date != null) {
            if (index == 0) {
                q = q + "Observation.date = ?";
                tempArgArray[index] = date.toString();
                tempArgTypes[index] = DataTypes.STRING;
                index++;}
            else {
                q = q + " AND Observation.date = ?";
                tempArgArray[index] = date.toString();
                tempArgTypes[index] = DataTypes.STRING;
                index++;}}
        if (location != null) {
            if (location.getLatitude() != -10000) {
                if (index == 0) {
                    q = q + "Obeservation.loc_lat = ?";
                    tempArgArray[index] = Float.toString(location.getLatitude());
                    tempArgTypes[index] = DataTypes.FLOAT;
                    index++;}
                else {
                    q = q + " AND Obeservation.loc_lat = ?";
                    tempArgArray[index] = Float.toString(location.getLatitude());
                    tempArgTypes[index] = DataTypes.FLOAT;
                    index++;}}
            if (location.getLongitude() != -10000) {
                if (index == 0) {
                    q = q + "Obeservation.loc_long = ?";
                    tempArgArray[index] = Float.toString(location.getLongitude());
                    tempArgTypes[index] = DataTypes.FLOAT;
                    index++;}
                else {
                    q = q + " AND Obeservation.loc_long = ?";
                    tempArgArray[index] = Float.toString(location.getLongitude());
                    tempArgTypes[index] = DataTypes.FLOAT;
                    index++;}}
            if (location.getElevation() != -10000) {
                if (index == 0) {
                    q = q + "Obeservation.loc_elevation = ?";
                    tempArgArray[index] = Integer.toString(location.getElevation());
                    tempArgTypes[index] = DataTypes.INTEGER;
                    index++;}
                 else {
                    q = q + " AND Obeservation.loc_elevation = ?";
                    tempArgArray[index] = Integer.toString(location.getElevation());
                    tempArgTypes[index] = DataTypes.INTEGER;
                    index++;}}}
		
	String argArray[] = new String[index + 4];
        DataTypes argTypes[] = new DataTypes[index + 4];
        for (int i = 0;i < index;i++){
            argArray[i] = tempArgArray[i];
            argTypes[i] = tempArgTypes[i];
        }
        argArray[index] = "Family.fam_id";
        argArray[index + 1] = "Genus.genus_id";
        argArray[index + 2] = "User.usr_id";
        argArray[index + 3] = "Subject.sub_id";
        argTypes[index] = DataTypes.STRING;
        argTypes[index + 1] = DataTypes.STRING;
        argTypes[index + 2] = DataTypes.STRING;
        argTypes[index + 3] = DataTypes.STRING;
        
        q = q + " AND Genus.fam_id = ? AND " +
                "Subject.genus_id = ? AND " +
		"Observation.author = ? AND " +
		"Observation.subject = ?";

	query.initializeQuery(q, argArray, argTypes);
	ResultSet results = query.executeQuery(dbconn.getConnection());

        try {
            int count = 0;
            while(results.next()) {
                count++;
            }
            subjectResults = new Subject[count];
            observationResults = new Observation[count];
            int i = 0;
            results.beforeFirst();
            while(results.next()) {
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
                long len = results.getBlob("photo").length();
                long pos = 0;
                sub.setPhoto(results.getBlob("photo").getBytes(pos,(int)len));
                sub.setRangeLow(results.getInt("range_low"));
                sub.setRangeHigh(results.getInt("range_high"));

                String subject = results.getString("genus") + results.getString("species");
                obs.setSubject(subject);
                obs.setAuthor(results.getString("alias"));
                obs.setDate(results.getDate("date"));
                obs.setQuantity(Integer.toString(results.getInt("quantity")));
                obs.setLocation(loc);
                obs.setNotes(results.getString("notes"));

                subjectResults[i] = sub;
                observationResults[i] = obs;
                i++;
                populated = true;
            }
	}catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
