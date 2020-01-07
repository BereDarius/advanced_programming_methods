package Repos;

import Domain.Activity;
import Domain.Teacher;
import Exceptions.ActivityException;
import Exceptions.TeacherException;

import java.sql.*;
import java.util.ArrayList;

public class DBrepo {
    private static final String url = "jdbc:sqlite:C:\\Users\\tubor\\IdeaProjects\\Lab1\\newdb.db";

    private Connection conn = null;


    public DBrepo() {
        this.openConnection();
        this.createSchema();
    }

    public void openConnection(){
        try{
            if(conn == null || conn.isClosed())
                conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSchema(){
        try{
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS teachers(id int primary key, first_name varchar(100), last_name varchar(100), title varchar(100), email varchar(100));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS activities(id int primary key, discipline int, type varchar(100), teacher int, room varchar(100));");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher newTeacher) {
        try{
            PreparedStatement statement = conn.prepareStatement("INSERT INTO teachers VALUES(?, ?, ?, ?, ?)");
            statement.setInt(1, newTeacher.getId());
            statement.setString(2, newTeacher.getFirstName());
            statement.setString(3, newTeacher.getLastName());
            statement.setString(4, newTeacher.getTitle());
            statement.setString(5, newTeacher.getEmail());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addActivity(Activity a){
        try{
            PreparedStatement statement = conn.prepareStatement("INSERT INTO activities VALUES(?, ?, ?, ?, ?)");
            statement.setInt(1, a.getId());
            statement.setInt(2, a.getDiscipline());
            statement.setString(3, a.getActivityType());
            statement.setInt(4, a.getTeacher());
            statement.setString(5, a.getRoom());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Activity> getActivities(){
        ArrayList<Activity> activities = new ArrayList<>();
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM activities");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Activity a = new Activity(rs.getInt("id"), rs.getInt("discipline"), rs.getString("activityType"), rs.getInt("teacher"), rs.getString("room"));
                activities.add(a);
            }
        } catch (SQLException | ActivityException e) {
            e.printStackTrace();
        }
        return activities;
    }

    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM teachers");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Teacher t = new Teacher(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("title"), rs.getString("email"));
                teachers.add(t);
            }
        }
        catch (SQLException | TeacherException ex){
            ex.printStackTrace();
        }
        return teachers;
    }


}