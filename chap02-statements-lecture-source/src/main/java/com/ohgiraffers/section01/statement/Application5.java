package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application5 {

    public static void main(String[] args) {

        // 1. Connection 객체 생성
        Connection con = getConnection();

        // 2. Statement 생성 // 쿼리문을 작성해서 데이터베이스에 요청하기위해 사용하는 객체
        Statement stmt = null;

        // 3. ResultSet 생성
        ResultSet rset = null;

        // 리스트 생성
        List<EmployeeDTO> empList = null;

        // EmployeeDTO 생성
        EmployeeDTO row = null;


        try {

            // 4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
            stmt = con.createStatement();


            // employee 테이블 전체 조회
            String query = "select * from employee";

            // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet에 반환 받기
            rset = stmt.executeQuery(query);

            empList = new ArrayList<>();

            // 6. 쿼리문에 결과를 컬럼 이름을 이용해서 사용
            while (rset.next()) {

                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getDouble("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            // 7. 사용한 자원 반납
            close(rset);
            close(stmt);
            close(con);

            for(EmployeeDTO emp : empList) {
                System.out.println(emp);
            }
        }
        /*
        * Statement 의 문제점
        * 1. 에러가 발생하면 쿼리가 그대로 드러난다.
        * 2. 완전한 쿼리를 사용하다 보니, 조작이 가능해진다. SQL 인젝션
        * 3. 많은 요청에 대한 성능 이슈
        * */
    }
}
