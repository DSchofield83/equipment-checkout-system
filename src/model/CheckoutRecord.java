/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author dscho
 */
public class CheckoutRecord
{
    private String technicianId;
    private String equipmentId;
    private LocalDate checkoutDate;
    private LocalDate returnDate;
    
    public CheckoutRecord( String technicianId, String equipmentId, LocalDate checkoutDate )
    {
        this.technicianId = technicianId;
        this.equipmentId = equipmentId;
        this.checkoutDate = checkoutDate;
        
    }
    
    public String getTechnicianId()
    {
        return technicianId;
    }
    
    public String getEquipmentId()
    {
        return equipmentId;
    }
    
    public LocalDate getCheckoutDate()
    {
        return checkoutDate;
    }
    
    public LocalDate getReturnDate()
    {
        return returnDate;
    }
    
    public void setCheckoutDate( LocalDate checkoutDate )
    {
        this.checkoutDate = checkoutDate;
    }
    
    public void setReturnDate( LocalDate returnDate )
    {
        this.returnDate = returnDate;
    }
}
