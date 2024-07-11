package org.syntax;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class Review {

    public static List<Map<String, String>> fetch(String query) {

        String dbURL="jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String userName="syntax_hrm";
        String password="syntaxhrm123";

        try {
            Connection connection=DriverManager.getConnection(dbURL,userName,password);
            Statement statement=connection.createStatement();
            String query="Select * from person;"; // 2
            ResultSet rs= statement.executeQuery(query);
            //next() it checks if there are any rows in the RS
            ResultSetMetaData rsm= rs.getMetaData();
            for (int i = 1; i <= rsm.getColumnCount() ; i++) {
                System.out.print(rsm.getColumnLabel(i)+" ");
            }
            System.out.println();
            while (rs.next()){
                String firstname=  rs.getString(2);
                String lastName=  rs.getString(3);
                System.out.println(firstname+" "+lastName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
