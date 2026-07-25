/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author dscho
 */

import model.Equipment;
import java.io.*;
import java.nio.file.*;
import java.util.*;


public class EquipmentService 
{
    private static final List<Equipment> equipmentList = new ArrayList<>();
    
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path EQUIPMENT_CSV = DATA_DIR.resolve("equipment.csv");
    
    public EquipmentService()
    {
        if ( equipmentList.isEmpty() )
        {
            loadFromCsv();
        }
    }
    
    public List<Equipment> getAllEquipment()
    {
        // Return a copy so UI can’t accidentally modify the internal list
        return new ArrayList<>(equipmentList);
    }

    public Equipment findById(String equipmentId)
    {
        for (Equipment e : equipmentList)
        {
            if (e.getEquipmentId().equals(equipmentId))
            {
                return e;
            }
        }
        return null;
    }

    public boolean addEquipment(Equipment equipment)
    {
        if (equipment == null) return false;

        // Prevent duplicate IDs
        if (findById(equipment.getEquipmentId()) != null) return false;

        equipmentList.add(equipment);
        saveToCsv();
        return true;
    }

    public boolean removeEquipment(String equipmentId)
    {
        Equipment existing = findById(equipmentId);
        if (existing == null) return false;

        // Optional safety: prevent removing checked out items
        if ("Checked Out".equalsIgnoreCase(existing.getStatus())) return false;

        equipmentList.remove(existing);
        saveToCsv();
        return true;
    }

    private void loadFromCsv()
    {
        try
        {
            if (!Files.exists(DATA_DIR))
            {
                Files.createDirectories(DATA_DIR);
            }

            if (!Files.exists(EQUIPMENT_CSV))
            {
                // Create file with header if it doesn't exist
                Files.writeString(EQUIPMENT_CSV, "equipmentId,name,category,status,location\n");
            }

            List<String> lines = Files.readAllLines(EQUIPMENT_CSV);

            equipmentList.clear();

            for (int i = 1; i < lines.size(); i++) // skip header
            {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 5) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                String category = parts[2].trim();
                String status = parts[3].trim();
                String location = parts[4].trim();

                equipmentList.add(new Equipment(id, name, category, status, location));
            }
        }
        catch (IOException ex)
        {
            // For a school project: simple fallback
            ex.printStackTrace();
        }
    }

    public void saveToCsv()
    {
        try
        {
            if (!Files.exists(DATA_DIR))
            {
                Files.createDirectories(DATA_DIR);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(EQUIPMENT_CSV))
            {
                writer.write("equipmentId,name,category,status,location");
                writer.newLine();

                for (Equipment e : equipmentList)
                {
                    writer.write(String.join(",",
                            safeCsv(e.getEquipmentId()),
                            safeCsv(e.getName()),
                            safeCsv(e.getCategory()),
                            safeCsv(e.getStatus()),
                            safeCsv(e.getLocation())
                    ));
                    writer.newLine();
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // Basic CSV safety (handles commas/quotes minimally)
    private String safeCsv(String s)
    {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\""))
        {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }

}
