package org.syntax;

import java.util.List;
import java.util.Map;

public class E4 {
    public static void main(String[] args) {

        //System.out.println(DBUtils.fetch("Select * from employee"));
        System.out.println(DBUtils.fetch("Select salary from employee where empid='100'"));
        //List<Map<String,String>> allData=DBUtils.fetch("Select * from employee");
        List<Map<String,String>> allData=DBUtils.fetch("Select salary from employee where empid='100'");
        System.out.println(allData.get(0));
        Map<String,String> row1=allData.get(0); // get the whole Map
        System.out.println(row1.get("salary")); // we specify the Key to get the info we want
    }
}
