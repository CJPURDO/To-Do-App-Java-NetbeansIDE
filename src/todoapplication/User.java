/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todoapplication;

/**
 *
 * @author OEM
 */

/*
This class acts as the model for the User object 
*/
public class User {
    
    private int UserId;
    private String name;
    private String password;
   
   //constructor

    /**
     *
     * @param name
     * @param string1
     * @param password
     */
   
   public User(int id, String name, String password){
       UserId = id;
       this.name = name;
       this.password = password;
   }

    /**
     *
     * @return
     * returns id
     */
   
    public int GetId(){
        return UserId;
    }

    /**
     *
     * @param id
     * sets id
     */
    public void SetId(int id){
        UserId = id;
    }
    
    /**
     *
     * @return
     * gets user name
     */
    public String GetName(){
        return name;
    }
    
    /**
     *
     * @param name
     * sets user name
     */
    public void SetName(String name){
        this.name = name;
    }
    
    /**
     *
     * @return
     * gets password
     */
    public String GetPassword(){
        return name;
    }
    
    /**
     *
     * @param pass
     * sets password
     */
    public void SetPassword(String pass){
        password = pass;
    }
    
    
}

