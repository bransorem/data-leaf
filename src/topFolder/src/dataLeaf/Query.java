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
            for(int i = 0; i < queryArgs.length; i++)
            {
                switch(argTypes[i])
                {
                    case STRING:
                        stmt.setString(i, queryArgs[i]);
                        break;
                    case INTEGER:
                        stmt.setInt(i, Integer.parseInt(queryArgs[i]));
                        break;
                    case FLOAT:
                        stmt.setFloat(i, Float.parseFloat(queryArgs[i]));
                }
            }
            results = stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    //need to find a better way to inform if the provided info cannot be built into an
    //  appropriate prepared statement
    public int initializeQuery(String q, String[] ar, DataTypes[] art){
        if ( (q.split("?").length - 1) == (ar.length) && (ar.length) == (art.length))
        {
            query = q;
            queryArgs = ar;
            argTypes = art;
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
}

