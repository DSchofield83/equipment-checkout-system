/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author dscho
 */

import model.CheckoutRecord;
import model.Equipment;
import model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CheckoutService
{
    private static final List< CheckoutRecord > records = new ArrayList<>();
    private final EquipmentService equipmentService = new EquipmentService();
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path RECORDS_CSV = DATA_DIR.resolve("checkout_records.csv");
    
    public CheckoutService()
    {
        if (records.isEmpty())
        {
            loadRecordsFromCsv();
        }
    }
    
    public boolean checkoutEquipment( User user, Equipment equipment )
    {
        // Verify availability
        if ( !equipment.isAvailable() )
        {
            return false;
        }
        
        equipment.setStatus("Checked Out");
        
        
        // Record Checkout
        CheckoutRecord record = new CheckoutRecord( user.getEmployeeId(), equipment.getEquipmentId(), LocalDate.now() );
        
        records.add( record );
        saveRecordsToCsv();
        equipmentService.saveToCsv();
        
        return true;
    }
    
    public void returnEquipment ( Equipment equipment )
    {
        equipment.setStatus("Available");
        equipmentService.saveToCsv();
        
        // Find the active checkout record for this equipment
        for ( CheckoutRecord r : records)
        {
            // Match equipment ID and ensure it hasn't already been returned
            if (( r.getEquipmentId().equals(equipment.getEquipmentId() ) ) && r.getReturnDate() == null) 
            {
                // Setting the return date to today
                r.setReturnDate( LocalDate.now() );
                saveRecordsToCsv();
                break; // Stop once the record is updated
            }
        }        
    }
    
    public List< CheckoutRecord > getRecord()
    {
        return new ArrayList<>(records);
    }
    
    private void loadRecordsFromCsv()
    {
        try
        {
            if (!Files.exists(DATA_DIR))
            {
                Files.createDirectories(DATA_DIR);
            }

            if (!Files.exists(RECORDS_CSV))
            {
                Files.writeString(RECORDS_CSV, "technicianId,equipmentId,checkoutDate,returnDate\n");
            }

            List<String> lines = Files.readAllLines(RECORDS_CSV);

            for (int i = 1; i < lines.size(); i++) // skip header
            {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue;

                String[] p = line.split(",", -1);
                if (p.length < 4) continue;

                String techId = p[0].trim();
                String equipId = p[1].trim();
                LocalDate checkoutDate = LocalDate.parse(p[2].trim());

                CheckoutRecord r = new CheckoutRecord(techId, equipId, checkoutDate);

                String returnDateStr = p[3].trim();
                if (!returnDateStr.isEmpty())
                {
                    r.setReturnDate(LocalDate.parse(returnDateStr));
                }

                records.add(r);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void saveRecordsToCsv()
    {
        try
        {
            if (!Files.exists(DATA_DIR))
            {
                Files.createDirectories(DATA_DIR);
            }

            try (BufferedWriter w = Files.newBufferedWriter(RECORDS_CSV))
            {
                w.write("technicianId,equipmentId,checkoutDate,returnDate");
                w.newLine();

                for (CheckoutRecord r : records)
                {
                    String returnDate = (r.getReturnDate() == null) ? "" : r.getReturnDate().toString();

                    w.write(r.getTechnicianId() + "," +
                            r.getEquipmentId() + "," +
                            r.getCheckoutDate() + "," +
                            returnDate);
                    w.newLine();
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
