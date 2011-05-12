package dataLeaf;

import java.sql.*;

public class Query {
    private String query;
    private String[] queryArgs;
    private DataTypes[] argTypes;
    private ResultSet results;

    protected ResultSet executeQuery(Connection conn) {
        if (queryArgs == null || argTypes == null)
            return null; //should probably make this throw something, or protect against a null return in some other way
        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            //Load the prepared statement
            for(int i = 1; i <= queryArgs.length; i++)
            {
                switch(argTypes[i-1])
                {
                    case STRING:
                        stmt.setString(i, queryArgs[i-1]);
                        break;
                    case INTEGER:
                        stmt.setInt(i, Integer.parseInt(queryArgs[i-1]));
                        break;
                    case FLOAT:
                        stmt.setFloat(i, Float.parseFloat(queryArgs[i-1]));
                }
            }
            System.out.println("beforeQueryExecution");
            results = stmt.executeQuery();
            System.out.println("afterQueryExecution");
         //   results.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    //need to find a better way to inform if the provided info cannot be built into an
    //  appropriate prepared statement

    public ResultSet genusQuery(Connection conn)
    {
        query = "SELECT genus FROM Genus order by genus";
         try {
                PreparedStatement stmt = conn.prepareStatement(query);
                results = stmt.executeQuery();
             } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    public int initializeQuery(String q, String[] ar, DataTypes[] art){
        System.out.println(q +" "+ ar.length + " "  + ar[0]+ " " + art[0] + " " + ar[1]
                + " " + art[1]);
        System.out.println(q.replaceAll("[^?]", "").length());
        System.out.println(( q.replaceAll("[^?]", "")));
    //    if ( (q.split("/^?/").length - 1 ) == (ar.length) && (ar.length) == (art.length))
        if ( (q.replaceAll("[^?]", "").length() ) == (ar.length) && (ar.length) == (art.length))
        {
            query = q;
            queryArgs = ar;
            argTypes = art;
            System.out.println("wll rt 0");
            return 0;
        }
        else
            return -1;
    }

    public ResultSet getResults() {
        return results;
    }

    public String getQuery() {
        return query.toString();
    }

    public String queryDialog(){
        return "Best I can do";
    }
}

