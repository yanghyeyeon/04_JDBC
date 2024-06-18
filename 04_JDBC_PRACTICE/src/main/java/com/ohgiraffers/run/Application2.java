package com.ohgiraffers.run;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
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

            System.out.print("사원의 번호를 입력하세요 : ");
            String empId = sc.next();

            System.out.print("사원의 이름을 입력하세요 : ");
            String empName = sc.next();

            System.out.print("사원의 주민번호를 입력하세요 : ");
            String empNo = sc.next();

            System.out.print("사원의 이메일을 입력하세요 : ");
            String email = sc.next();

            System.out.print("사원의 전화번호를 입력하세요 : ");
            String phone = sc.next();

            System.out.print("사원의 부서코드를 입력하세요 : ");
            String deptCode = sc.next();

            System.out.print("사원의 직급코드를 입력하세요 : ");
            String jobCode = sc.next();

            System.out.print("사원의 급여등급을 입력하세요 : ");
            String salLevel = sc.next();

            System.out.print("사원의 급여를 입력하세요 : ");
            double salary = sc.nextDouble();

            System.out.print("사원의 관리자 사번을 입력하세요 : ");
            String manageId = sc.next();

            EmployeeDTO newEmp = new EmployeeDTO();

            newEmp.setEmpId(empId);
            newEmp.setEmpName(empName);
            newEmp.setEmpNo(empNo);
            newEmp.setEmail(email);
            newEmp.setPhone(phone);
            newEmp.setDeptCode(deptCode);
            newEmp.setJobCode(jobCode);
            newEmp.setSalLevel(salLevel);
            newEmp.setSalary(salary);
            newEmp.setManagerId(manageId);



            pstmt.setString(1,newEmp.getEmpId());
            pstmt.setString(2,newEmp.getEmpName());
            pstmt.setString(3,newEmp.getEmpNo());
            pstmt.setString(4,newEmp.getEmail());
            pstmt.setString(5,newEmp.getPhone());
            pstmt.setString(6,newEmp.getDeptCode());
            pstmt.setString(7,newEmp.getJobCode());
            pstmt.setString(8,newEmp.getSalLevel());
            pstmt.setDouble(9,newEmp.getSalary());
            pstmt.setString(10,newEmp.getManagerId());

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("정보 저장 성공");
        } else {
            System.out.println("정보 저장 실패");
        }
    }
}
