package dataLeaf;
/*
Create and edit a Search

author	Mike Despars
date	3/21/11
rev		1
 */

import java.util.Date;

class Search {

    private Query query;
    private Subject[] subjectResults;
    private Observation[] observationResults;
    private String species;
    private String genus;
    private String observer;
    private Date date;
    private Location location;

    Search(String spec, String gen, String obs, Date newDate, float lat, float lon, int elev) {
        query = new Query();
        species = spec == null ? "" : spec;
        genus = gen == null ? "" : gen;
        observer = obs == null ? "" : obs;
        date = newDate == null ? new Date() : newDate;
        location = new Location(lat, lon, elev);
    }

    //Getter methods
    public Subject[] getSubjectResults() {
        return subjectResults;
    }

    public Observation[] getObservationResults() {
        return observationResults;
    }


    public void setLocation(float lat, float lon, int elev) {
        location = new Location(lat, lon, elev);
    }

    /* toString()
    -No input
    -Returns a string containing all search criteria
     */
    public String toString() {
        String str = species + ","
                + genus + ","
                + observer + ","
                + date.toString() + ","
                + location.toString();
        return str;
    }

    /* executeSearch()
    -No input, values should be set before execution.
    -No output, method will update Subject[] and Observation[].
    -**NEED TO KNOW HOW DATA WILL BE FORMATTED FROM executeQuery().**
     */
    public void executeSearch() {
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
