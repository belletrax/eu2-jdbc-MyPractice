package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class javautilexample {
    String dbUrl ="jdbc:oracle:thin:@54.157.154.248:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void metadata2() throws SQLException {
        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();
         int colCount = rsMetadata.getColumnCount();
        //list for keeping all rows as a map
        List<Map<String,Object>> queryData = new ArrayList<>();
       while (resultSet.next()){
           Map<String,Object> row = new HashMap<>();

        for( int i=1;i<colCount;i++){
            row.put(rsMetadata.getColumnName(i),resultSet.getObject(i));
           }



          //put map to main list
           queryData.add(row);
       }

       for(Map<String,Object> row : queryData){
           System.out.println(row.toString());
       }


        //-----------------------
       /* Map<String,Object> row1 = new HashMap<>();
        resultSet.next();
        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());

        System.out.println(row1.get("FIRST_NAME"));

        Map<String,Object> row2 = new HashMap<>();
         resultSet.next();
        row2.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);
        System.out.println(queryData.toString());
        System.out.println(queryData.get(0).get("SALARY"));*/


        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
