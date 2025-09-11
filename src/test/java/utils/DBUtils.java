package utils;



import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    public static List<Map<String,String >> fetch(String query){

        String dbURL=ConfigReader.read("dbUrl");
        String userName=ConfigReader.read("userName");
        String password=ConfigReader.read("password");

        List <Map<String,String>> tableData=new ArrayList<>();
        try(Connection connection= DriverManager.getConnection(dbURL,userName,password);
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
        ){
            ResultSetMetaData rsm=resultSet.getMetaData();

            while (resultSet.next()){
                Map<String,String> rowMap=new LinkedHashMap<>();
                for (int i = 1; i < rsm.getColumnCount() ; i++) {
                    String key=rsm.getColumnLabel(i);
                    String value=resultSet.getString(i);
                    rowMap.put(key,value);
                }
                tableData.add(rowMap);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return tableData;
    }
}
