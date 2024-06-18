package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.DepartmentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        /*
        * Department 테이블의 부서코드(아이디)를 입력받아서
        * 부서코드(아이디), 부서명, 지역코드를 출력하시오
        * */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 부서코드를 입력하세요 : ");
        String deptId = sc.nextLine();

        String query = "select dept_id, dept_title,location_id from department where dept_id = ?";

        DepartmentDTO selectedDp = null;

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,deptId);

            rset = pstmt.executeQuery();

            if(rset.next()) {

                selectedDp = new DepartmentDTO();

                selectedDp.setDeptId(rset.getString("dept_id"));
                selectedDp.setDeptTitle(rset.getString("dept_title"));
                selectedDp.setLocationId(rset.getString("location_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(rset);
            close(pstmt);
            close(con);
        }
        System.out.println("selectedDp = " + selectedDp);

    }
}
