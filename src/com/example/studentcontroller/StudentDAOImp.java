package com.example.studentcontroller;

import com.example.model.Student;
import com.example.studentdb.StudentDb;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAOImp implements StudentDAO {

    @Override
    public void save(Student students) {
        try{
            Connection con = StudentDb.getConnection();
            String sql = "INSERT INTO studentinfo(edp, name, course) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, students.getEdp());
            ps.setString(2, students.getName());
            ps.setString(3, students.getCourse());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public void delete(Student students) {
        try{
            Connection con = StudentDb.getConnection();
            String sql = "DELETE FROM studentinfo WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, students.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public void update(Student students) {
        try{
            Connection con = StudentDb.getConnection();
            String sql = "UPDATE studentinfo SET edp = ?, name = ?, course = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, students.getEdp());
            ps.setString(2, students.getName());
            ps.setString(3, students.getCourse());
            ps.setInt(4, students.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "   Updated");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public Student get(int id) {
        Student st = new Student();
        
        try{
            Connection con = StudentDb.getConnection();
            String sql = "SELECT * FROM studentinfo WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                st.setId(rs.getInt("id"));
                st.setEdp(rs.getInt("edp"));
                st.setName(rs.getString("name"));
                st.setCourse(rs.getString("course"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
        
        return st;
    }

    @Override
    public List<Student> list() {
        List<Student> list = new ArrayList<>();
        
        try{
            Connection con = StudentDb.getConnection();
            String sql = "SELECT * FROM studentinfo";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setEdp(rs.getInt("edp"));
                st.setName(rs.getString("name"));
                st.setCourse(rs.getString("course"));
                
                list.add(st);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
        
        return list;
    }
}