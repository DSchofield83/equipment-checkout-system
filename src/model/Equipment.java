/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dscho
 */
public class Equipment
{
    private String equipmentId;
    private String name;
    private String category;
    private String status;     // "Available" or "Checked out"
    private String location;
    
    public Equipment ( String equipmentId, String name, String category, String status, String location )
    {
        this.equipmentId = equipmentId;
        this.name = name;
        this.category = category;
        this.status = status;
        this.location = location;
        
    }
    
    public String getEquipmentId()
    {
        return equipmentId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public boolean isAvailable()
    {
        return "Available".equalsIgnoreCase( status );
    }
    
    public void setStatus ( String status )
    {
        this.status = status;
    }
   
}
