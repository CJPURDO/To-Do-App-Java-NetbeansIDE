/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todoapplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author OEM
 */
public class Controller {

    //Task collection and current user

    /**
     *
     */
    public static Controller controllerInstance = null;

    private Controller() {

    }

    /**
     *
     * @return returns instance
     */
    public static Controller getInstance() {

        if (controllerInstance == null) {
            controllerInstance = new Controller();
            return controllerInstance;
        }
        return controllerInstance;
    }

    //list of tasks
    ArrayList<Task> Tasks = new ArrayList<Task>();

    //class variables
    /**
     * Task
     */
    public Task selectedTask;

    /**
     * User
     */
    public User currentUser;

    /**
     * The lastID of a task
     */
    public int lastId;

    /**
     *
     * @param task set the selected task
     */
    public void setSelectedTask(Task task) {
        selectedTask = task;
    }

    /**
     *
     * @return get selected task
     */
    public Task getSelectedTask() {
        return selectedTask;
    }

    /**
     *
     * @return return task array list
     */
    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    /**
     *
     * @return array of tasks
     */
    public Task[] getArray() {

        Task[] tasks = Tasks.toArray(new Task[TaskLength()]);

        return tasks;
    }

    /**
     *
     * @param task
     */
    public void AddTask(Task task) {
        Tasks.add(task);

    }

    /**
     *
     * @return
     */
    public int TaskLength() {
        return Tasks.size();
    }

    /**
     *
     * @return
     */
    public String[] taskStrings() {

        String listString;
        int i = TaskLength();
        String[] strings = new String[i];

        int n = 0;
        for (Task t : Tasks) {
            strings[n] = t.GetName() + " " + t.GetDates() + "/ ";

            n++;
        }

        return strings;
    }

    //set current user
    /**
     *
     * @param user
     */
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    //get current user ID
    /**
     *
     * @return
     */
    public int getId() {
        return currentUser.GetId();
    }

    //generate a unique ID for every new task to ensure that there are no duplicates
    /**
     *
     */
    public void GenerateId() {
        Connection c = null;
        Statement stmt = null;

        int UserID = getId();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(TaskId) FROM Tasks;");

            lastId = rs.getInt("MAX(TaskId)");

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    //adds a new task to the database
    /**
     *
     * @param name
     * @param details
     * @param dates
     */
    public void NewTask(String name, String details, String dates) {

        int userId = getId();

        GenerateId();
        int id = lastId + 1;

        String Name = "'" + name + "'";
        String Dates = "'" + dates + "'";
        String Details = "'" + details + "'";

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Tasks (TaskId, UserId, taskname, details, datee) "
                    + "VALUES (" + id + "," + userId + "," + Name + "," + Details + "," + Dates + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    // checks credentials from the login form
    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean LogIn(String username, String password) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE name = " + username + "AND password = " + password + ";");

            if (rs.next() == false) {

                rs.close();
                stmt.close();
                c.close();

                return false;
            } else {

                //Create User and set as current user
                User user = new User(rs.getInt("UserId"), rs.getString("name"), rs.getString("password"));
                setCurrentUser(user);

                rs.close();
                stmt.close();
                c.close();
                return true;
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }// end of login form

    /**
     * Connect to the test.db database
     */
    public void Connect() {

        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    // get all tasks from the current user
    /**
     *
     */
    public void SelectAll() {

        Tasks.clear();

        Connection c = null;
        Statement stmt = null;

        int n = 0;

        int UserID = getId();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tasks WHERE UserId = " + UserID + " ORDER BY datee;");

            while (rs.next()) {
                int id = rs.getInt("TaskId");
                int Userid = rs.getInt("UserId");
                String name = rs.getString("taskname");
                String details = rs.getString("details");
                String date = rs.getString("datee");
                Task task = new Task(id, Userid, name, details, date);
                Tasks.add(task);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    //deletes a given task from the database
    /**
     *
     * @param id
     */
    public void DeletetTask(int id) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE from Tasks WHERE TaskId =" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }// end of delete task

    //updates a given task's details in the datasbe
    /**
     *
     * @param name
     * @param details
     * @param date
     */
    public void UpdateTask(String name, String details, String date) {

        Connection c = null;
        Statement stmt = null;

        int id = selectedTask.GetId();

        //format input strings
        String Fname = "'" + name + "'";
        String Fdetail = "'" + details + "'";
        String Fdate = "'" + date + "'";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ToDoApp.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "UPDATE Tasks SET taskname = " + Fname + ",details = " + Fdetail + ",datee = " + Fdate + "  WHERE TaskId =" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }// end of update task

}
