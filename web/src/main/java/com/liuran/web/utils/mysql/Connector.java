package com.liuran.web.utils.mysql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connector {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liuran",
                        "root", "liuran");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PreparedStatement ppst = null;
        ResultSet rs = null;
        List<String> outPut = new ArrayList<>();
        try {
            ppst =connection.prepareStatement("select word, count from word_count where count >= 300 order by count desc ");
            ppst.execute();
            rs = ppst.getResultSet();
            int index = 0;
            while (rs.next()){
                if (index % 100 == 0){
                    outPut.add("============ page:" + (index / 100 + 1) +" count:" + rs.getInt(2) + " =============");
                }
                String line = rs.getString(1);
                outPut.add(line);
                index ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ppst != null){
                try {
                    ppst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("/Users/liuran/File/Program/project/Java/idea/study/words.txt")));
            for (String line : outPut){
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return;
    }

}
