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

    //Setter methods
    public void setSpecies(String spec) {
        species = spec;
    }

    public void setGenus(String gen) {
        genus = gen;
    }

    public void setObserver(String obs) {
        observer = obs;
    }

    public void setDate(Date new_date) {
        date = new_date;
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
    }
}
