package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        /*
        * 위치홀더 : ?
        *
        * java 쿼리문은 파싱을 통해 컴파일 되서 Database에 퀴리를 수행해 결과 값을 가져온다.
        * Statement -> SQL문 실행시 매번 퀴리를 새롭게 인식해서 컴파일 진행
        * PreparedStatement -> 조건값을 ? 로 두고 다른 쿼리를 미리 컴파일 해둔뒤 쿼리는 변경하지 않고,
        *                      바인딩되는 변수만 바꿔서 SQl문이 실행된다.
        * */

        // Scanner 사용한 PreparedStatement

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        // Scanner 사번 입력받기
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사번을 입력해주세요 : ");
        String empId = sc.nextLine();

        String query = "select * from employee where emp_id = ?";

        EmployeeDTO selectedEmp = null;

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,empId);

            rset = pstmt.executeQuery();

            if (rset.next()) {

                selectedEmp = new EmployeeDTO();

                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setEmpNo(rset.getString("EMP_NO"));
                selectedEmp.setEmail(rset.getString("EMAIL"));
                selectedEmp.setPhone(rset.getString("PHONE"));
                selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectedEmp.setJobCode(rset.getString("JOB_CODE"));
                selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectedEmp.setSalary(rset.getDouble("SALARY"));
                selectedEmp.setBonus(rset.getDouble("BONUS"));
                selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectedEmp.setEntYn(rset.getString("ENT_YN"));

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        System.out.println("selectedEmp = " + selectedEmp);
    }
}
