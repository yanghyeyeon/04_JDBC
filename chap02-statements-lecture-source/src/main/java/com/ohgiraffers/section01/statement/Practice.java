package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.OgclassDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplatePractice.close;
import static com.ohgiraffers.common.JDBCTemplatePractice.getConnection;

public class Practice {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        List<OgclassDTO> classList = null;

        OgclassDTO row = null;

        try {
            stmt = con.createStatement();

            String query = "select * from class";

            rset = stmt.executeQuery(query);

            classList = new ArrayList<>();


            while(rset.next()) {
                row = new OgclassDTO();

                row.setStudentNo(rset.getString("STUDENT_NO"));
                row.setStudentName(rset.getString("STUDENT_NAME"));
                row.setGender(rset.getString("GENDER"));
                row.setGithubId(rset.getString("GITHUB_ID"));
                row.setEmail(rset.getString("EMAIL"));
                row.setMbti(rset.getString("MBTI"));
                row.setSubjectNo(rset.getString("SUBJECT_NO"));

                classList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(rset);
            close(stmt);
            close(con);

            for(OgclassDTO ogclass : classList) {
                System.out.println(ogclass);
            }
        }
    }
}
