package com.kerong.jdbctest.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class test {

    private static String year;
    private static String month;
    private static String repId;

    public static void main(String[] args) {
        System.out.println(new Date());
        File file = new File("D:\\Files\\WorkFiles\\Kerong\\202006_GF0100.txt");
        // 获取年份月份和编号
        getDate(file);
        // 生成唯一id
        UUID uuid = UUID.randomUUID();
        // 处理同步记录表
        insertInfo(uuid);
        // 处理同步数据
        readFile(file,uuid);
        System.out.println(new Date());
    }

    // 获取年份月份和编号
    public static void getDate(File file){
        String name = file.getName();
        String[] s1 = name.split("\\.");
        String[] s2 = s1[0].split("_");
        year = s2[0].substring(0, 4);
        month = s2[0].substring(4, 6);
        repId = s2[1];
    }

    // 处理同步记录
    public static void insertInfo(UUID uuid){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        int times = 0;

        String sql1 = "SELECT * FROM Xxtj_etl_info WHERE is_max_time = 1 AND year = "+year+" AND Term = "+month+" AND Child_rep_id = '"+repId+"'";
        String sql2 = "UPDATE Xxtj_etl_info SET is_max_time = 0 WHERE is_max_time = 1 AND year = "+year+" AND Term = "+month+" AND Child_rep_id = '"+repId+"'";

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","orcl");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()){
                times = resultSet.getInt("times");
            }
            times ++;
            statement.executeUpdate(sql2);
            String sql3 = "INSERT INTO Xxtj_etl_info(Xxtj_etl_info_id,year,Term,Child_rep_id,Version_id,Data_range_id,Freq_id,is_max_time,times) values(" +
                    "'"+uuid.toString()+"'"+","+"'"+year+"'"+","+"'"+month+"'"+","+"'"+repId+"'"+","+"null,1,null,1"+","+times+")";
            statement.executeUpdate(sql3);
            connection.commit();
        } catch (Exception e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            close(resultSet,statement,connection);
        }
    }

    // 读取文件，获取数据
    public static void readFile(File file,UUID uuid){
        Connection connection = null;
        Statement statement = null;
        String s = null;
        BufferedReader br = null;
        int count = 0;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","orcl");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            br = new BufferedReader(new FileReader(file));
            while((s = br.readLine()) != null){
                String[] arr = s.split("\\|");
                String sql = buildSql(arr, uuid);
                statement.addBatch(sql);
                count ++;
                if(count % 10000 == 0){
                    // 满5000条就写入数据库
                    statement.executeBatch();
                    count = 0;
                }
            }
            statement.executeBatch();
            connection.commit();
        } catch (Exception e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            close(statement,connection);
        }
    }

    // 组装sql语句
    public static String buildSql(String[] arr,UUID uuid){
        String sql = "INSERT INTO xxtj_etl_data(Xxtj_etl_info_id,year,Term,Org_id,Child_rep_id,Version_id,Data_range_id," +
                "Freq_id,Cell_name,Cell_value) values(" +"'"+uuid.toString()+"'"+","+"'"+year+"'"+","+"'"+month+"'"+","+"'"+arr[0]+"'"+","+"'"+arr[1]+"'"+
                ",null,1,null," +"'"+arr[2]+"'"+","+"'"+arr[3]+"'"+
                ")";
        return sql;
    }

    // 关闭资源
    public static void close(Statement statement, Connection connection){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet resultSet,Statement statement, Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
