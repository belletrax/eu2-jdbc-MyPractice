package jdbctests;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException{
        String dbUrl ="jdbc:oracle:thin:@54.157.154.248:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        // create the connection

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary from employees");



       //  resultSet.next();
       // System.out.println(resultSet.getString("region_name"));
      //  resultSet.next();
       // System.out.println(resultSet.getString("region_name"));
       // resultSet.next();
      //  System.out.println(resultSet.getString("region_name"));
       // resultSet.next();
      //  System.out.println(resultSet.getString("region_name"));
        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name")+" "+
                    resultSet.getString("last_name")+"-"+resultSet.getInt(3));

        }

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

        //closing all connections
       // resultSet.close();
        //statement.close();
        //connection.close();


    }
}
