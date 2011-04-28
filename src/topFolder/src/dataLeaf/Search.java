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

    Search(String spec, String gen, String obs, Date newDate, Location loc) {
        query = new Query();
        species = spec == null ? "" : spec;
        genus = gen == null ? "" : gen;
        observer = obs == null ? "" : obs;
        date = newDate == null ? null : newDate;
        location = loc == null ? null : loc;
    }

    /**
		Sets a new location for the search criterion.
		
		@param lat  A float conatining the new latitude.
		@param lon  A float containing the new longitude.
		@param evel A integer value for the elevation.
    */
    public void setLocation(float lat, float lon, int elev) {
        location = new Location(lat, lon, elev);
    }

    /**
		Converts the search criterion into a string.
		
		@return str A String containing the search criterion.
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
		This method will query the database for the
		specified search criterion and populate the
		subject results and observation results arrays.
		
		@param dbconn The current database connection object.
     */
    public void executeSearch(DatabaseConnection dbconn) {
        
        int flag = 0;
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
            flag = 1;
            q = q + "Subject.species = '" + species + "'";}
        if (genus.equals("")) {
            if (flag == 0) {
                flag = 1;
                q = q + "Genus.genus = '" + genus + "'";}
            else {q = q + " AND Genus.genus = '" + genus + "'";}}
        if (observer.equals("")) {
            if (flag == 0) {
                flag = 1;
                q = q + "User.alias = '" + observer + "'";}
            else {q = q + " AND User.alias = '" + observer + "'";}}
        if (date != null) {
            if (flag == 0) {
                flag = 1;
                q = q + "Observation.date = '" + date.toString() + "'";}
            else {q = q + " AND Observation.date = '" + date.toString() + "'";}}
        if (location != null) {
            if (location.getLatitude() != -10000) {
                if (flag == 0) {
                    flag = 1;
                    q = q + "Obeservation.loc_lat = '" + Float.toString(location.getLatitude()) + "'";}
                else {q = q + " AND Obeservation.loc_lat = '" + Float.toString(location.getLatitude()) + "'";}}
            if (location.getLongitude() != -10000) {
                if (flag == 0) {
                    flag = 1;
                    q = q + "Obeservation.loc_long = '" + Float.toString(location.getLongitude()) + "'";}
                else {q = q + " AND Obeservation.loc_long = '" + Float.toString(location.getLongitude()) + "'";}}
            if (location.getElevation() != -10000) {
                if (flag == 0) {
                    flag = 1;
                    q = q + "Obeservation.loc_elevation = '" + Integer.toString(location.getElevation()) + "'";}
                 else {q = q + " AND Obeservation.loc_elevation = '" + Integer.toString(location.getElevation()) + "'";}}}
		
	q = q + " AND Genus.fam_id = Family.fam_id AND " + 
                "Subject.genus_id = Genus.genus_id AND " +
		"Observation.author = User.usr_id AND " +
		"Observation.subject = Subject.sub_id";
		
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
		}
		}catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
    }
}
