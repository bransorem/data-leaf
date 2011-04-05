
import java.util.Date;

class ObservationTest{
    
    public static void main(String args[]){
        
        Location loc = new Location();        
        
        Observation obs1 = new Observation();
        Observation obs2 = new Observation("Test2", "Author2", loc);
        
        obs1.setSubject("Test1");
        obs1.setAuthor("Author1");
        Date date = new Date();
        obs1.setDate(date);
        obs1.setQuantity("53");
        obs1.setLocation(loc);
        obs1.setNotes("This is a test");
        
        System.out.println("Obs2 ============================");
        System.out.println(obs2.getSubject());
        System.out.println(obs2.getAuthor());
        System.out.println(obs2.getDate());
        System.out.println(obs2.getQuantity());
        System.out.println(obs2.getLocation());
        System.out.println(obs2.getNotes());
        
        System.out.println("Obs1 ============================");
        System.out.println(obs1.getSubject());
        System.out.println(obs1.getAuthor());
        System.out.println(obs1.getDate());
        System.out.println(obs1.getQuantity());
        System.out.println(obs1.getLocation());
        System.out.println(obs1.getNotes());
        
        System.out.println("Obs1 ============================");
        System.out.println(obs1);
        System.out.println("Obs2 ============================");
        System.out.println(obs2);
    }

}


/* Sample run: 


Obs2 ============================
Test2
Author2
Mon Apr 04 22:25:02 PDT 2011
null
0.0, 0.0
null
Obs1 ============================
Test1
Author1
Mon Apr 04 22:25:02 PDT 2011
53
0.0, 0.0
This is a test
Obs1 ============================
Test1, Author1, Mon Apr 04 22:25:02 PDT 2011
Obs2 ============================
Test2, Author2, Mon Apr 04 22:25:02 PDT 2011


*/