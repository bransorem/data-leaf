/*
 * Unit test for Search class
 */
package dataLeaf;

import java.util.Date;

/**
 *
 * @author Brannen Sorem
 */
public class SearchTest {
    public static void main(String[] args){
    
        // Search(String spec, String gen, String obs, Date newDate, Location loc)
        String species = "californica";
        String genus = "Lyrocarpa";
        String observer = "";
        Date date = new Date();
        Location loc = new Location();
        
        Search search;
        
        search = new Search(species, "", "", date, loc);
        search = new Search("", genus, "", date, loc);
        search = new Search("", "", observer, date, loc);
    }
}
