

class LocationTest{
    
    public static void main(String args[]){
        
        // Test Location class
        Location loc1 = new Location();
        Location loc2 = new Location((float)33.3566, (float)-160.3423, 1508);
        Location loc3 = new Location();
        
        loc3.setLatitudeFormat(15, 36, 5);
        loc3.setLongitudeFormat(18, 132, 23);
        
        loc2.setDescription("Test2");
        
        loc1.setLatitude((float)13.4134);
        loc1.setLongitude((float)234.1803);
        loc1.setElevation(18000);
        loc1.setDescription("Test1");
        
        System.out.println("Lat: " +  loc1.getLatitude());
        System.out.println("Long: " + loc1.getLongitude());
        System.out.println("Ele: " +  loc1.getElevation());   
        System.out.println("Desc: " + loc1.getDescription());
        
        System.out.println("Lat: " +  loc2.getLatitude());
        System.out.println("Long: " + loc2.getLongitude());
        System.out.println("Ele: " +  loc2.getElevation());   
        System.out.println("Desc: " + loc2.getDescription());
        
        System.out.println("Lat: " +  loc3.getLatitude());
        System.out.println("Long: " + loc3.getLongitude());
        System.out.println("Ele: " +  loc3.getElevation());   
        System.out.println("Desc: " + loc3.getDescription());
        
        System.out.println(loc1);
        System.out.println(loc2);
        System.out.println(loc3);
        
    }    
    
}



/* Sample run: 

Lat: 13.4134
Long: 234.1803
Ele: 18000
Desc: Test1
Lat: 33.3566
Long: -160.3423
Ele: 1508
Desc: Test2
Lat: 15.601389
Long: 20.20639
Ele: 0
Desc: 
13.4134, 234.1803
33.3566, -160.3423
15.601389, 20.20639


*/