/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petugas;

import admin.*;
import admin.CRUD.data_pembayaran;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import connection.connection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import role.verif;

/**
 *
 * @author Eren
 */
public class HistoryPetugas extends javax.swing.JFrame {

    /**
     * Creates new form HistoryPetugas
     */
     Statement stmnt;
      String sql;
      String sql2;
      String sql3;
      ResultSet rs;
      Connection c;
          private DefaultTableModel TableModel;
       private Object[] selectedRowData;
       
    public HistoryPetugas() {
        initComponents();
        c = connection.getConnection();
         try {
              stmnt = c.createStatement(); // Initialize the statement object
          } catch (SQLException ex) {
              Logger.getLogger(admin.CRUD.create.data_akun.class.getName()).log(Level.SEVERE, null, ex);
          }
         this.TampilData();
         this.kelasCombo();
         this.nisnCombo();

    }
     public void TampilData(){
    int no = 1; // Counter variable
    TableModel = new DefaultTableModel(); // Initialize table model
    // Add columns to the table model
    TableModel.addColumn("no");
    TableModel.addColumn("Akun Petugas");
    TableModel.addColumn("NISN");
    TableModel.addColumn("Tanggal Bayar");
    TableModel.addColumn("Bulan diBayar");
    TableModel.addColumn("Tahun diBayar");
    TableModel.addColumn("SPP");
    TableModel.addColumn("Jumlah Bayar");

    tabelAkun1.setModel(TableModel); // Set the table model
    
    try {
        sql = "SELECT * FROM data_pembayaran"; 
                      stmnt = c.createStatement(); // Initialize the statement object

        java.sql.ResultSet res = stmnt.executeQuery(sql); // Execute query

        while (res.next()) {
             //inside your while loop, after fetching the values from the result set
//            int nominal = res.getInt("nominal_spp");
//            String tahun = res.getString("tahun_dibayar");
//            String username = res.getString("nama_akun");
//            
            // Combine nominal and tahun into one string
       //     String combinedValue = tahun  + " | " + nominal +"";
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String tglBayarString = res.getString("tgl_bayar"); // Get the date as a string from the ResultSet
Date tglBayarDate = dateFormat.parse(tglBayarString); // Convert the string to a Date

TableModel.addRow(new Object[]{
    res.getInt("id_pembayaran"),
    res.getInt("id_akun"),
    res.getString("nisn"),
    dateFormat.format(tglBayarDate), // Format the date before adding it to the table model
    res.getString("bulan_dibayar"),
    res.getString("tahun_dibayar"),
    res.getInt("id_spp"),
    res.getInt("jumlah_bayar")
});
        }
        
        // Close the result set
    } catch (SQLException e) {
        System.out.println("Error executing SQL query: " + e.getMessage()); // Print error message
    }    catch (ParseException ex) {
             Logger.getLogger(data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
         }
}
public void filter(String selectedNisn) {
    DefaultTableModel model = (DefaultTableModel) tabelAkun1.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tabelAkun1.setRowSorter(sorter);

    if (!selectedNisn.equals("All")) {
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter(selectedNisn, 2);
        sorter.setRowFilter(rowFilter);
    } else {
        tabelAkun1.setRowSorter(null);
    }
}

public void filterDate(Date date1, Date date2) {
    DefaultTableModel model = (DefaultTableModel) tabelAkun1.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tabelAkun1.setRowSorter(sorter);

    RowFilter<DefaultTableModel, Integer> rowFilter = new RowFilter<DefaultTableModel, Integer>() {
        @Override
        public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int row = entry.getIdentifier();
            Date tglBayarDate;
            try {
                tglBayarDate = dateFormat.parse(model.getValueAt(row, 3).toString());
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
            return tglBayarDate.after(date1) && tglBayarDate.before(date2);
        }
    };
    sorter.setRowFilter(rowFilter);
}

   private HashMap<String, Integer> kelasDisplayTextToIdMap = new HashMap<>();

    private void kelasCombo(){
      try{
         jComboKelas.addItem("All");
         sql = "SELECT * FROM data_kelas";
                       stmnt = c.createStatement(); // Initialize the statement object
           
         java.sql.ResultSet res = stmnt.executeQuery(sql);
         while(res.next()){
            int id_kelas = res.getInt("id_kelas");
    String nama_kelas = res.getString("nama_kelas");
    System.out.println("id_kelas: " + id_kelas + ", nama_kelas: " + nama_kelas);
    jComboKelas.addItem(nama_kelas);
    kelasDisplayTextToIdMap.put(nama_kelas, id_kelas);
         }
         
      }catch(SQLException e){
         e.printStackTrace(); // Handle the exception properly

      }  
    }
    private void nisnCombo(){
    try {
        jComboNisn.removeAllItems();// Clear existing items before adding new ones
        jComboNisn.addItem("All");
        String kelas = jComboKelas.getSelectedItem().toString();
        if (kelas != null) {  // Add a null check here
                                   stmnt = c.createStatement(); // Initialize the statement object

            sql = "SELECT * FROM data_siswa WHERE id_kelas = '" + kelasDisplayTextToIdMap.get(kelas) + "'";
            ResultSet res = stmnt.executeQuery(sql);
            while(res.next()){
                String nisn_siswa = res.getString("nisn");
                jComboNisn.addItem(nisn_siswa);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception properly
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bDashboard = new javax.swing.JButton();
        bLogout = new javax.swing.JButton();
        bEntry = new javax.swing.JButton();
        bHistory = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAkun1 = new javax.swing.JTable();
        jComboNisn = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboKelas = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        filterDates = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bDashboard.setText("Dashboard");
        bDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboardActionPerformed(evt);
            }
        });

        bLogout.setText("Log Out");

        bEntry.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        bEntry.setText("Entry Transaksi");
        bEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEntryActionPerformed(evt);
            }
        });

        bHistory.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        bHistory.setText("Lihat History");
        bHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHistoryActionPerformed(evt);
            }
        });

        tabelAkun1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Akun Petugas", "NISN", "Tanggal Bayar", "Bulan Bayar", "Tahun Bayar", "SPP", "Jumlah Bayar"
            }
        ));
        tabelAkun1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAkun1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelAkun1);

        jComboNisn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboNisn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNisnActionPerformed(evt);
            }
        });

        jLabel1.setText("NISN");

        jLabel2.setText("Kelas");

        jComboKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        jComboKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboKelasActionPerformed(evt);
            }
        });

        jLabel3.setText("Tanggal ke-1");

        jLabel4.setText("Tanggal ke-2");

        filterDates.setText("Submit");
        filterDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterDatesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bEntry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bHistory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 479, Short.MAX_VALUE)
                        .addComponent(bDashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboNisn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(86, 86, 86)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(filterDates)))
                                .addGap(182, 182, 182))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDashboard)
                    .addComponent(bEntry)
                    .addComponent(bHistory)
                    .addComponent(bLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboNisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filterDates, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboardActionPerformed
        new dashboardPetugas().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboardActionPerformed

    private void bEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEntryActionPerformed
        new admin.EntryAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bEntryActionPerformed

    private void bHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bHistoryActionPerformed
private String selectedId;
    private void tabelAkun1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAkun1MouseClicked
        int selectedRowIndex = tabelAkun1.getSelectedRow();

        if (selectedRowIndex != -1) {
            selectedRowData = new Object[]{
                selectedId =  tabelAkun1.getValueAt(selectedRowIndex, 0).toString(), // Assuming column 0 is id_akun
                tabelAkun1.getValueAt(selectedRowIndex, 1), // Assuming column 1 is username
                tabelAkun1.getValueAt(selectedRowIndex, 2), // Assuming column 2 is password
                tabelAkun1.getValueAt(selectedRowIndex, 3), // Assuming column 3 is nama
                tabelAkun1.getValueAt(selectedRowIndex, 4),
                tabelAkun1.getValueAt(selectedRowIndex, 5),
                tabelAkun1.getValueAt(selectedRowIndex, 6),
                tabelAkun1.getValueAt(selectedRowIndex, 7),// Assuming column 4 is level
            };
        }
    }//GEN-LAST:event_tabelAkun1MouseClicked

    private void jComboNisnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNisnActionPerformed
 
   Object selectedNisnObject = jComboNisn.getSelectedItem();
    if (selectedNisnObject != null) {
        String selectedNisn = selectedNisnObject.toString();
        if (selectedNisn.equals("Select Nisn")) {
            // Reload all data
            TampilData();
        } else if (selectedNisn.equals("All")) {
            // Clear any existing filters
            DefaultTableModel model = (DefaultTableModel) tabelAkun1.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            tabelAkun1.setRowSorter(sorter);
        } else {
            filter(selectedNisn);
        }
    }
    }//GEN-LAST:event_jComboNisnActionPerformed

    private void jComboKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboKelasActionPerformed
        nisnCombo();

    }//GEN-LAST:event_jComboKelasActionPerformed

    private void filterDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterDatesActionPerformed
// Add the following code to filter the table by selected dates
    Date date1 = jDateChooser1.getDate();
    Date date2 = jDateChooser2.getDate();
    filterDate(date1, date2);
    }//GEN-LAST:event_filterDatesActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HistoryPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bEntry;
    private javax.swing.JButton bHistory;
    private javax.swing.JButton bLogout;
    private javax.swing.JButton filterDates;
    private javax.swing.JComboBox<String> jComboKelas;
    private javax.swing.JComboBox<String> jComboNisn;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelAkun1;
    // End of variables declaration//GEN-END:variables
}
