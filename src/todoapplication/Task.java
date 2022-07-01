/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todoapplication;

/**
 *
 * @author OEM
 */
public class Task {
    
    //Class variables
    private int taskId;
    private int userId;
    private String name;
    private String details;
    private String dates;
    
    //Constructor

    /**
     *
     * @param i1
     * @param uid
     * @param name
     * @param string2
     * @param dates
     */
    
    public Task(int id, int uid, String name, String details, String dates){
        taskId = id;
        userId = uid;
        this.name = name;
        this.details = details;
        this.dates = dates;
    }
    
    
    //Getters and Setters

    /**
     *
     * @return
     */
    
    public int GetId(){
        return taskId;
    }

    /**
     *
     * @param id
     */
    public void SetId(int id){
        taskId = id;
    }
    
    /**
     *
     * @return
     */
    public int GetUserId(){
        return userId;
    }

    /**
     *
     * @param id
     */
    public void SetUserId(int id){
        userId = id;
    }
    
    /**
     *
     * @return
     */
    public String GetName(){
        return name;
    }
    
    /**
     *
     * @param name
     */
    public void SetName(String name){
        this.name = name;
    }
    
    /**
     *
     * @return
     */
    public String GetDates(){
        return dates;
    }
    
    /**
     *
     */
    public void SetDates(){
        this.name = dates;
    }
    
    /**
     *
     * @return
     */
    public String GetDetail(){
        return details;
    }
    
    /**
     *
     * @param detail
     */
    public void SetDetail(String detail){
        details = detail;
    }
    
    
    
}
