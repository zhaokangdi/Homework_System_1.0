package Jdbc;

import Model.Homework;
import Model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbc {
    JdbcUtil jdbc_util = new JdbcUtil();

    public void InsertStudent(Student student) {
        jdbc_util.Connect();
        String sql;
        sql = "INSERT INTO STUDENT VALUES (?)";
        try {
            jdbc_util.stmt = jdbc_util.conn.prepareStatement(sql);
            jdbc_util.stmt.setString(1, student.getStudent_name());
            jdbc_util.stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbc_util.Close();
    }

    public List<Homework> QueryHomework(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Homework> homework_list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "SELECT * FROM HOMEWORK WHERE HOMEWORK.TEACHER_NAME IN (SELECT TEACH.TEACHER_NAME FROM TEACH WHERE STUDENT_NAME=?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudent_name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Homework homework = new Homework();
                    homework.setHomework_title(rs.getString("homework_title"));
                    homework.setTeacher_name(rs.getString("teacher_name"));
                    homework_list.add(homework);
                }
            }

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2){ }

            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

        return homework_list;
    }

    public boolean QuerySubmit(Student student, String homework_title, String teacher_name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Homework> homework_list = new ArrayList<>();
        Integer number = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "SELECT COUNT(*) FROM SUBMIT WHERE HOMEWORK_TITLE=? AND TEACHER_NAME=? AND STUDENT_NAME=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, homework_title);
            stmt.setString(2, teacher_name);
            stmt.setString(3, student.getStudent_name());

            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    number = rs.getInt(1);
                }
            }

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2){ }

            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

        if(number == 0) {
            return true;
        }else {
            return false;
        }
    }

    public void InsertSubmit(String homework_title, String teacher_name, Student student, String content) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "INSERT INTO SUBMIT VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, homework_title);
            stmt.setString(2, teacher_name);
            stmt.setString(3, student.getStudent_name());
            stmt.setString(4, content);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2){ }

            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
