/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dscho
 */
public class User
{
    private String employeeId;
    private String password;
    private String role; // Technician or manager
    
    public User ( String employeeId, String password, String role )
    {
        this.employeeId = employeeId;
        this.password = password;
        this.role = role;
    }
    
    public String getEmployeeId()
    {
        return employeeId;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getRole()
    {
        return role;
    }

    public boolean checkPassword(String input)
    {
        return password.equals( input );
    }
    
    public boolean isManager()
    {
        return "Manager".equalsIgnoreCase(role);
    }
    
    public boolean isTechnician()
    {
        return "Technician".equals(role);
    }
}
