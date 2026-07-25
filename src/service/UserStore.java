/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.HashMap;
import java.util.Map;
import model.User;

/**
 *
 * @author dscho
 */
public class UserStore
{
    private static final Map<String, User> users = new HashMap<>();
    
    static
    {
        // Temporary test users will be using a database or csv to simulate employee database.
        users.put("admin", new User( "admin", "admin123!", "Admin" ) );
        users.put("tech", new User( "tech", "tech123!", "Technician" ) );
    }
    
    public static User findByUsername( String employeeId )
    {
        return users.get( employeeId );
    }
}
