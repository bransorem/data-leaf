package dataLeaf;

/**
 * Create/Edit Location
 * 
 * @author  Brannen Sorem
 */  
class Location
{
    // Private variables
    float       latitude;
    float       longitude;
    int         elevation;
    String      description;
    
    // Constructors
    Location() 
    {
        latitude = 0;
        longitude = 0;
        elevation = 0;
        description = "";
    }
    Location(float lat, float longi, int ele)
    {
        latitude = lat;
        longitude = longi;
        elevation = ele;
        description = "";
    }

    // Getter methods
    public float    getLatitude()    { return latitude; }
    public float    getLongitude()   { return longitude; }
    public int      getElevation()   { return elevation; }
    public String   getDescription() { return description; }
    
    // Setter methods
    public void     setLatitude( float lat )        { latitude = lat;     }
    public void     setLongitude( float longi )     { longitude = longi;  }
    public void     setElevation( int ele )         { elevation = ele;    }
    public void     setDescription( String desc )   { description = desc; }
    
    // Format methods
    public void  setLatitudeFormat(int deg, int min, int sec)
    {
        Float fdeg = new Float(deg);
        Float fmin = new Float(min);
        Float fsec = new Float(sec);
        
        latitude = fdeg + (fmin / 60) + (fsec / 3600);
    }
    public void  setLongitudeFormat(int deg, int min, int sec)
    {
        Float fdeg = new Float(deg);
        Float fmin = new Float(min);
        Float fsec = new Float(sec);
        
        longitude = fdeg + (fmin / 60) + (fsec / 3600);
    }
    public String   toString()
    {
        String str = Float.toString(latitude) + ", " + Float.toString(longitude);
        return str;
    }
}