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
    String      subject;
    String      author;
    Date        date;
    String      quantity;
    Location    location;
    String      notes;

    // Constructors
    Observation()
    {
        subject = "";
        author = "";
        date = new Date();
        quantity = "";
        location = new Location();
        notes = "";
    }
    Observation(String sub, String auth, Location loc)
    {
        subject = sub;
        author = auth;
        date = new Date();
        location = loc;
    }

    // Getter methods
    public int         getDatabaseID()  { return databaseID; }
    public String      getSubject()     { return subject;    }
    public String      getAuthor()      { return author;     }
    public Date        getDate()        { return date;       }
    public String      getQuantity()    { return quantity;   }
    public Location    getLocation()    { return location;   }
    public String      getNotes()       { return notes;      }

    // Setter methods
    public void   setSubject( String sub )      { subject = sub;     }
    public void   setAuthor( String auth )      { author = auth;     }
    public void   setDate( Date new_date )      { date = new_date;   }
    public void   setQuantity( String quant )   { quantity = quant;  }
    public void   setLocation( Location loc )   { location = loc;    }
    public void   setNotes( String new_notes )  { notes = new_notes; }

    // toString
    public String toString()
    {
        String str = subject + ", " + author + ", " + date.toString();
        return str;
    }
}