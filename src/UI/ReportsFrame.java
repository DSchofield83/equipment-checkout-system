/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.Equipment;
import model.CheckoutRecord;
import model.User;
import service.EquipmentService;
import service.CheckoutService;

/**
 *
 * @author dscho
 */
public class ReportsFrame extends javax.swing.JFrame
{
    private final User currentUser;
    private final EquipmentService equipmentService = new EquipmentService();
    private final CheckoutService checkoutService = new CheckoutService();
    private DefaultTableModel tableModel;
    
    public ReportsFrame(User user)
    {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.currentUser = user;
        
    }
    
    private void generateSelectedReport()
    {
        String type = (String) cmbReportType.getSelectedItem();

        if ("Inventory Summary".equals(type))
        {
            loadInventorySummary();
        }
        else if ("Checked Out Equipment".equals(type))
        {
            loadCheckedOutEquipment();
        }
        else if ("Checkout History".equals(type))
        {
            loadCheckoutHistory();
        }
    }

    private void loadInventorySummary()
    {
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Equipment ID", "Name", "Category", "Status", "Location"}
        )
        {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblReport.setModel(tableModel);

        List<Equipment> list = equipmentService.getAllEquipment();
        for (Equipment e : list)
        {
            tableModel.addRow(new Object[]{
                e.getEquipmentId(),
                e.getName(),
                e.getCategory(),
                e.getStatus(),
                e.getLocation()
            });
        }

        jLabel1.setText("Inventory Summary generated: " + list.size() + " item(s).");
    }

    private void loadCheckedOutEquipment()
    {
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Equipment ID", "Name", "Category", "Status", "Location"}
        )
        {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblReport.setModel(tableModel);

        int count = 0;
        for (Equipment e : equipmentService.getAllEquipment())
        {
            if ("Checked Out".equalsIgnoreCase(e.getStatus()))
            {
                tableModel.addRow(new Object[]{
                    e.getEquipmentId(),
                    e.getName(),
                    e.getCategory(),
                    e.getStatus(),
                    e.getLocation()
                });
                count++;
            }
        }

        jLabel1.setText("Checked Out Equipment report generated: " + count + " item(s).");
    }

    private void loadCheckoutHistory()
    {
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Technician ID", "Equipment ID", "Checkout Date", "Return Date"}
        )
        {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblReport.setModel(tableModel);

        List<CheckoutRecord> list = checkoutService.getRecord();
        for (CheckoutRecord r : list)
        {
            tableModel.addRow(new Object[]{
                r.getTechnicianId(),
                r.getEquipmentId(),
                r.getCheckoutDate(),
                r.getReturnDate()
            });
        }

        jLabel1.setText("Checkout History report generated: " + list.size() + " record(s).");
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pnlHeader = new javax.swing.JPanel();
        lblReports = new javax.swing.JLabel();
        lblReportType = new javax.swing.JLabel();
        cmbReportType = new javax.swing.JComboBox<>();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();
        pnlFooter = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnGenerate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(910, 650));

        pnlHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblReports.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblReports.setText("Reports");

        lblReportType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReportType.setText("Report Type:");

        cmbReportType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inventory Summary", "Checked Out Equipment", "Checkout History" }));

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReports, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addComponent(lblReportType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addComponent(lblReports)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReportType)
                    .addComponent(cmbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 55, Short.MAX_VALUE))
        );

        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReport);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBackActionPerformed(evt);
            }
        });

        btnGenerate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGenerate.setText("Generate Report");
        btnGenerate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGenerateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnGenerate))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGenerateActionPerformed
    {//GEN-HEADEREND:event_btnGenerateActionPerformed
        generateSelectedReport();
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBackActionPerformed
    {//GEN-HEADEREND:event_btnBackActionPerformed
        new MainMenuFrame(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JComboBox<String> cmbReportType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReportType;
    private javax.swing.JLabel lblReports;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables
}
