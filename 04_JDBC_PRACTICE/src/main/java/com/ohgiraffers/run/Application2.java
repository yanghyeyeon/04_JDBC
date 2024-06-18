package com.ohgiraffers.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {

            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            String query = prop.getProperty("insertEmployee");

            pstmt = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);

            System.out.print();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
