package dataLeaf;

/**
 * Create/Edit Observations
 * 
 * @author  Brannen Sorem
 */  
 
import java.util.Date;

class Observation
{
    // Private variables
    int         databaseID;
    String      author;
    Date        date;
    String      quantity;
    Location    location;
    String      notes;
    private String	genus;
    private String 	species;
    
    // Constructors
    Observation()
    {
        species = "";
        genus = "";
        author = "";
        date = new Date();
        quantity = "";
        location = new Location();
        notes = "";
    }
    Observation(String spec, String genu, String auth, Location loc)
    {
        species = spec;
        genus = genu;
        author = auth;
        date = new Date();
        location = loc;
    }
        
    // Getter methods
    public int         getDatabaseID()  { return databaseID; }
    public String      getSpecies()     { return species;    }
    public String      getGenus()       { return genus;      }
    public String      getAuthor()      { return author;     }
    public Date        getDate()        { return date;       }
    public String      getQuantity()    { return quantity;   }
    public Location    getLocation()    { return location;   }
    public String      getNotes()       { return notes;      } 
    
    // Setter methods
    public void   setSpecies( String spec )      { species = spec;   }
    public void   setGenus( String genu )      { species = genu;   }
    public void   setAuthor( String auth )      { author = auth;     }
    public void   setDate( Date new_date )      { date = new_date;   }
    public void   setQuantity( String quant )   { quantity = quant;  }
    public void   setLocation( Location loc )   { location = loc;    } 
    public void   setNotes( String new_notes )  { notes = new_notes; }
    
    // toString
    public String toString()
    {
        String str = species + ", " + genus + ", " + author + ", " + date.toString();
        return str;
    }
}