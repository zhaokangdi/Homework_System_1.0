package Jdbc;

import Model.Homework;
import Model.Student;
import Model.Submit;
import Model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherJdbc {

    public void AddTeacher(Teacher teacher) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "INSERT INTO TEACHER VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getTeacher_name());
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

    public List<Homework> QueryHomework(Teacher teacher) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Homework> homework_list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "SELECT * FROM HOMEWORK WHERE TEACHER_NAME=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getTeacher_name());

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

    public void AddHomework(Teacher teacher, String homework_title) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "INSERT INTO HOMEWORK VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, homework_title);
            stmt.setString(2, teacher.getTeacher_name());
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

    public void AddStudent(Teacher teacher, String student_name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "INSERT INTO TEACH VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getTeacher_name());
            stmt.setString(2, student_name);
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

    public List<Student> QueryStudent(Teacher teacher) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Student> student_list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "SELECT * FROM TEACH WHERE TEACHER_NAME=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getTeacher_name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(rs.getString("student_name"));
                    student.setStudent_name(rs.getString("student_name"));
                    student_list.add(student);
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

        return student_list;
    }

    public List<Submit> QuerySubmit(String homework_title, Teacher teacher) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Submit> submit_list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?serverTimezone=UTC","root","123456");

            String sql;
            sql = "SELECT * FROM SUBMIT WHERE HOMEWORK_TITLE=? and TEACHER_NAME=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, homework_title);
            stmt.setString(2, teacher.getTeacher_name());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Submit submit = new Submit();
                    submit.setHomework_title(homework_title);
                    submit.setTeacher_name(teacher.getTeacher_name());
                    submit.setStudent_name(rs.getString("student_name"));
                    submit.setContent(rs.getString("content"));
                    submit_list.add(submit);
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

        return submit_list;
    }
}
