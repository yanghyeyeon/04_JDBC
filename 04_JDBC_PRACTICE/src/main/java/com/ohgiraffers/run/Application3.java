package com.ohgiraffers.run;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);

        System.out.print("변경할 사원 번호를 입력해주세요 : ");
        String empId = sc.next();

        System.out.print("변경할 사원 이름을 입력해주세요 : ");
        String empName = sc.next();

        System.out.print("변경할 사원 주민번호를 입력해주세요 : ");
        String empNo = sc.next();

        System.out.print("변경할 사원 이메일을 입력해주세요 : ");
        String email = sc.next();

        System.out.print("변경할 사원 전화번호를 입력해주세요 : ");
        String phone = sc.next();

        System.out.print("변경할 사원 부서코드를 입력하세요 : ");
        String deptCode = sc.next();

        System.out.print("변경할 사원 직급코드를 입력하세요 : ");
        String jobCode = sc.next();

        System.out.print("변경할 사원 급여등급을 입력하세요 : ");
        String salLevel = sc.next();

        System.out.print("변경할 사원 급여를 입력하세요 : ");
        double salary = sc.nextDouble();

        System.out.print("변경할 사원 관리자 사번을 입력하세요 : ");
        String manageId = sc.next();

        EmployeeDTO changedEmp = new EmployeeDTO();
        changedEmp.setEmpId(empId);
        changedEmp.setEmpName(empName);
        changedEmp.setEmpNo(empNo);
        changedEmp.setEmail(email);
        changedEmp.setPhone(phone);
        changedEmp.setDeptCode(deptCode);
        changedEmp.setJobCode(jobCode);
        changedEmp.setSalLevel(salLevel);
        changedEmp.setSalary(salary);
        changedEmp.setManagerId(manageId);


        try {

            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            String query = prop.getProperty("updateEmp");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, changedEmp.getEmpName());
            pstmt.setString(2, changedEmp.getEmpNo());
            pstmt.setString(3, changedEmp.getEmail());
            pstmt.setString(4, changedEmp.getPhone());
            pstmt.setString(5, changedEmp.getDeptCode());
            pstmt.setString(6, changedEmp.getJobCode());
            pstmt.setString(7, changedEmp.getSalLevel());
            pstmt.setDouble(8, changedEmp.getSalary());
            pstmt.setString(9, changedEmp.getManagerId());
            pstmt.setString(10, changedEmp.getEmpId());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
        if(result > 0) {
            System.out.println("사원 변경 성공");
        } else {
            System.out.println("사원 변경 실패");
        }
    }
}
