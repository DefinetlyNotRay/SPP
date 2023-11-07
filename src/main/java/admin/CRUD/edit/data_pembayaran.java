/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin.CRUD.edit;
import admin.CRUD.*;
import admin.dashboardCRUD;
import admin.dashboard;
import connection.connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.toedter.calendar.JDateChooser;
import java.util.HashMap;
import java.util.logging.Level;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Eren
 */
public class data_pembayaran extends javax.swing.JFrame {
    private void initializeStatement() {
    try {
        stmnt = c.createStatement();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    /**
     * Creates new form data_pembayaran
     */
     Connection c;
    String sql;
    Statement stmnt;
          private DefaultTableModel TableModel;
       private Object[] selectedRowData;
      
    public data_pembayaran(){
        initComponents();
        c = connection.getConnection();
        try {
            stmnt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(admin.CRUD.create.data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.petugasCombo();
        this.nisnCombo();
        this.sppCombo();
        this.TampilData();
            initializeStatement();

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

    tabelAkun2.setModel(TableModel); // Set the table model
    
    try {
        sql = "SELECT * FROM data_pembayaran"; 
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
        res.close();
    } catch (SQLException e) {
        System.out.println("Error executing SQL query: " + e.getMessage()); // Print error message
    }    catch (ParseException ex) {
             Logger.getLogger(admin.CRUD.data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
         }
}
    
    
    private HashMap<String, Integer> usernameToIdAkunMap = new HashMap<>();
private HashMap<Integer, String> idAkunToUsernameMap = new HashMap<>();
private HashMap<Integer, String> idSppToDisplayTextMap = new HashMap<>();

   private void petugasCombo(){
    try{
        java.sql.Statement statement = c.createStatement();
        sql = "SELECT * FROM data_akun WHERE level IN ('admin','petugas')";
        java.sql.ResultSet res = stmnt.executeQuery(sql);
        while(res.next()){
            String akun = res.getString("id_akun");
            String username = res.getString("username");

            jIdakunCombo.addItem(username);

            // Store the mapping in the HashMap
            usernameToIdAkunMap.put(username, Integer.parseInt(akun));

            // Store the reverse mapping
            idAkunToUsernameMap.put(Integer.parseInt(akun), username);
        }

    } catch(SQLException e){
        e.printStackTrace(); // Handle the exception properly
    }
}
    private void setSelectedItemById(JComboBox comboBox, HashMap<String, Integer> map, int id) {
    for (String key : map.keySet()) {
        if (map.get(key) == id) {
            comboBox.setSelectedItem(key);
            break;
        }
    }
}


    private void nisnCombo(){
        try{
            java.sql.Statement statement = c.createStatement();
            sql = "SELECT * FROM data_siswa";
            java.sql.ResultSet res = stmnt.executeQuery(sql);
            while(res.next()){
            String nisn = res.getString("nisn");

            jNisnCombo.addItem(nisn);

            
            
        }
        }catch(SQLException e){
            e.printStackTrace(); // Handle the exception properly
        }
        
    }
    
// Modify the populateBulanBayarCombo() method
private void populateBulanBayarCombo() {
    String selectedNisn = (String) jNisnCombo.getSelectedItem();
    String selectedYear = (String) tahunBayar.getSelectedItem();

    try {
        java.sql.Statement statement = c.createStatement();
        String sql = "SELECT DISTINCT bulan_dibayar " +
                     "FROM data_pembayaran " +
                     "WHERE nisn = '" + selectedNisn + "' " +
                     "AND tahun_dibayar = " + selectedYear;

        java.sql.ResultSet res = statement.executeQuery(sql);

        // Create a HashSet to store the months that have been paid
        HashSet<String> paidMonths = new HashSet<>();
        while (res.next()) {
            String month = res.getString("bulan_dibayar");
            paidMonths.add(month);
        }

        // Populate the bulanBayar combo box with the list of unpaid months
        bulanBayar.removeAllItems(); // Clear existing items
        String[] allMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ArrayList<String> allMonthsList = new ArrayList<>(Arrays.asList(allMonths));
        Collections.sort(allMonthsList);
        bulanBayar.setModel(new DefaultComboBoxModel<>(allMonthsList.toArray(new String[0])));

        // Close the result set
        res.close();

    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception properly
    }
}

    private HashMap<String, Integer> sppDisplayTextToIdMap = new HashMap<>();

    private void sppCombo(){
    try{
        java.sql.Statement statement = c.createStatement();
        sql = "SELECT * FROM data_spp";
        java.sql.ResultSet res = stmnt.executeQuery(sql);
        while(res.next()){
            int id_spp = res.getInt("id_spp");
            int nominal = res.getInt("nominal");
            int tahun = res.getInt("tahun");

            String displayText = tahun  + " | " + nominal ;
            jIdsppCombo.addItem(displayText);

            // Store the mapping in the HashMap
            sppDisplayTextToIdMap.put(displayText, id_spp);

            // Store the reverse mapping
            idSppToDisplayTextMap.put(id_spp, displayText);
        }
    } catch(SQLException e){
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAkun1 = new javax.swing.JTable();
        bSPP = new javax.swing.JButton();
        bSiswa = new javax.swing.JButton();
        bKelas = new javax.swing.JButton();
        bAkun = new javax.swing.JButton();
        bPembayaran = new javax.swing.JButton();
        bCRUD = new javax.swing.JButton();
        bDashboard1 = new javax.swing.JButton();
        bLogout = new javax.swing.JButton();
        jIdsppCombo = new javax.swing.JComboBox<>();
        submitButton = new javax.swing.JButton();
        jumlahBayar = new javax.swing.JTextField();
        bulanBayar = new javax.swing.JComboBox<>();
        jIdakunCombo = new javax.swing.JComboBox<>();
        id_akun6 = new javax.swing.JLabel();
        id_akun1 = new javax.swing.JLabel();
        jNisnCombo = new javax.swing.JComboBox<>();
        id_akun2 = new javax.swing.JLabel();
        id_akun3 = new javax.swing.JLabel();
        id_akun4 = new javax.swing.JLabel();
        id_akun5 = new javax.swing.JLabel();
        id_akun = new javax.swing.JLabel();
        tanggalBayar = new com.toedter.calendar.JDateChooser();
        tahunBayar = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelAkun2 = new javax.swing.JTable();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bSPP.setText("Data SPP");
        bSPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSPPActionPerformed(evt);
            }
        });

        bSiswa.setText("Data Siswa");
        bSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiswaActionPerformed(evt);
            }
        });

        bKelas.setText("Data Kelas");
        bKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKelasActionPerformed(evt);
            }
        });

        bAkun.setText("Data Akun");
        bAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAkunActionPerformed(evt);
            }
        });

        bPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bPembayaran.setText("Data Pembayaran");
        bPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPembayaranActionPerformed(evt);
            }
        });

        bCRUD.setText("CRUD");
        bCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCRUDActionPerformed(evt);
            }
        });

        bDashboard1.setText("Dashboard");
        bDashboard1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboard1ActionPerformed(evt);
            }
        });

        bLogout.setText("Logout");
        bLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLogoutActionPerformed(evt);
            }
        });

        jIdsppCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        bulanBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Select Month"}));
        bulanBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulanBayarActionPerformed(evt);
            }
        });

        jIdakunCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        id_akun6.setText("Jumlah Bayar");

        id_akun1.setText("NISN");

        jNisnCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jNisnCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNisnComboActionPerformed(evt);
            }
        });

        id_akun2.setText("Tanggal Bayar");

        id_akun3.setText("Bulan Dibayar");

        id_akun4.setText("Tahun Dibayar");

        id_akun5.setText("Id SPP");

        id_akun.setText("Akun Petugas");

        tahunBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Select Year","2023","2024"}));
        tahunBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunBayarActionPerformed(evt);
            }
        });

        tabelAkun2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Akun Petugas", "NISN", "Tanggal Bayar", "Bulan Bayar", "Tahun Bayar", "SPP", "Jumlah Bayar"
            }
        ));
        tabelAkun2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAkun2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelAkun2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bSPP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bSiswa)
                        .addGap(6, 6, 6)
                        .addComponent(bKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bPembayaran)
                        .addGap(506, 506, 506)
                        .addComponent(bCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bDashboard1)
                        .addGap(6, 6, 6)
                        .addComponent(bLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_akun5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jIdsppCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(id_akun, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(id_akun1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(id_akun2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jIdakunCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jNisnCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tanggalBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_akun4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_akun3)
                                    .addComponent(bulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_akun6)
                                    .addComponent(jumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bSPP)
                    .addComponent(bSiswa)
                    .addComponent(bKelas)
                    .addComponent(bAkun)
                    .addComponent(bPembayaran)
                    .addComponent(bCRUD)
                    .addComponent(bDashboard1)
                    .addComponent(bLogout))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(id_akun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jIdakunCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(id_akun1)
                        .addGap(6, 6, 6)
                        .addComponent(jNisnCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(id_akun2)
                        .addGap(6, 6, 6)
                        .addComponent(tanggalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_akun5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jIdsppCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(id_akun4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addComponent(submitButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(bulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(id_akun3)
                                                .addGap(31, 31, 31)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(id_akun6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSPPActionPerformed
        new admin.CRUD.data_spp().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bSPPActionPerformed

    private void bSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiswaActionPerformed
        new admin.CRUD.data_siswa().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bSiswaActionPerformed

    private void bKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKelasActionPerformed
        new admin.CRUD.data_kelas().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bKelasActionPerformed

    private void bAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAkunActionPerformed
        new admin.CRUD.data_akun().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAkunActionPerformed

    private void bPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPembayaranActionPerformed
        new admin.CRUD.data_pembayaran().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPembayaranActionPerformed

    private void bCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCRUDActionPerformed
        new admin.dashboardCRUD().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bCRUDActionPerformed

    private void bDashboard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboard1ActionPerformed
        new admin.dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboard1ActionPerformed

    private void bLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLogoutActionPerformed
        new login.login().setVisible(true);
        this.dispose();


    }//GEN-LAST:event_bLogoutActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
                int selectedRowIndex = tabelAkun2.getSelectedRow();
        
           
            selectedId =  tabelAkun2.getValueAt(selectedRowIndex, 0).toString();
       
        String selectedUsername = (String) jIdakunCombo.getSelectedItem();
       
        Integer id_akun = usernameToIdAkunMap.get(selectedUsername);

        String selectedNisn = (String) jNisnCombo.getSelectedItem();
        Date selectedDate = tanggalBayar.getDate();
        String selectedSPP = (String) jIdsppCombo.getSelectedItem();
        Integer id_spp = sppDisplayTextToIdMap.get(selectedSPP);

        String tahunBayars = (String) tahunBayar.getSelectedItem();
        String BulanBayar = (String) bulanBayar.getSelectedItem();
        String HJumlahBayar = jumlahBayar.getText();

        try {
            Connection ce = connection.getConnection();
            sql = "SELECT * FROM data_siswa WHERE nisn = '"+ selectedNisn +"'";
            java.sql.ResultSet res = stmnt.executeQuery(sql);

            if (res.next()) {
                String id_akun_siswa = res.getString("id_akun");

                // Prepare a SQL insert statement to add a new ticket entry
                java.sql.PreparedStatement statement = ce.prepareStatement("UPDATE data_pembayaran SET id_akun=?, nisn=?, tgl_bayar=?, bulan_dibayar=?,tahun_dibayar=?,id_spp=?,jumlah_bayar=?,id_akun_siswa=? WHERE id_pembayaran = '"+selectedId+"' ");
                statement.setInt(1, id_akun);  // Set as an integer
                statement.setString(2, selectedNisn);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(selectedDate);
                statement.setString(3, formattedDate);
                statement.setString(4, BulanBayar);
                statement.setString(5, tahunBayars);
                statement.setInt(6, id_spp);  // Set as an integer
                statement.setString(7, HJumlahBayar);
                statement.setString(8, id_akun_siswa);

                statement.executeUpdate(); // Execute the SQL insert statement
                new admin.CRUD.data_pembayaran().setVisible(true);
                this.dispose();
            } else {
                System.out.println("No data found for nisn: " + selectedNisn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_submitButtonActionPerformed

    private void bulanBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulanBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bulanBayarActionPerformed

    private void jNisnComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNisnComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNisnComboActionPerformed

    private void tahunBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunBayarActionPerformed
        populateBulanBayarCombo();
    }//GEN-LAST:event_tahunBayarActionPerformed
private String selectedId;
private int selectedAkun;
private String nisn;
private String tglBayarString;
private String bulan_bayar;
private String tahun_bayar;

private int spp;
private String jumlah_bayar;


    private void tabelAkun1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAkun1MouseClicked
       
    }//GEN-LAST:event_tabelAkun1MouseClicked

    private void tabelAkun2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAkun2MouseClicked
          int selectedRowIndex = tabelAkun2.getSelectedRow();

    if (selectedRowIndex != -1) {
        selectedRowData = new Object[]{
            selectedId =  tabelAkun2.getValueAt(selectedRowIndex, 0).toString(),
            selectedAkun= (int) tabelAkun2.getValueAt(selectedRowIndex, 1),
            nisn = tabelAkun2.getValueAt(selectedRowIndex, 2).toString(),
            tglBayarString = tabelAkun2.getValueAt(selectedRowIndex, 3).toString(),
            bulan_bayar = tabelAkun2.getValueAt(selectedRowIndex, 4).toString(),
            tahun_bayar = tabelAkun2.getValueAt(selectedRowIndex, 5).toString(),
            spp= (int) tabelAkun2.getValueAt(selectedRowIndex, 6),
            jumlah_bayar = tabelAkun2.getValueAt(selectedRowIndex, 7).toString(),
        };

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date tglBayarDate = dateFormat.parse(tglBayarString);
                    tanggalBayar.setDate(tglBayarDate);

        } catch (ParseException ex) {
            Logger.getLogger(data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set selected items in JComboBoxes based on id values
        jIdakunCombo.setSelectedItem(idAkunToUsernameMap.get(selectedAkun));
        jNisnCombo.setSelectedItem(nisn);
        jIdsppCombo.setSelectedItem(idSppToDisplayTextMap.get(spp));
        tahunBayar.setSelectedItem(tahun_bayar);
        bulanBayar.setSelectedItem(bulan_bayar);
        jumlahBayar.setText(jumlah_bayar);
    }
        
    }//GEN-LAST:event_tabelAkun2MouseClicked

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
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        Date currentDate = new Date();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new data_pembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAkun;
    private javax.swing.JButton bCRUD;
    private javax.swing.JButton bDashboard1;
    private javax.swing.JButton bKelas;
    private javax.swing.JButton bLogout;
    private javax.swing.JButton bPembayaran;
    private javax.swing.JButton bSPP;
    private javax.swing.JButton bSiswa;
    private javax.swing.JComboBox<String> bulanBayar;
    private javax.swing.JLabel id_akun;
    private javax.swing.JLabel id_akun1;
    private javax.swing.JLabel id_akun2;
    private javax.swing.JLabel id_akun3;
    private javax.swing.JLabel id_akun4;
    private javax.swing.JLabel id_akun5;
    private javax.swing.JLabel id_akun6;
    private javax.swing.JComboBox<String> jIdakunCombo;
    private javax.swing.JComboBox<String> jIdsppCombo;
    private javax.swing.JComboBox<String> jNisnCombo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jumlahBayar;
    private javax.swing.JButton submitButton;
    private javax.swing.JTable tabelAkun1;
    private javax.swing.JTable tabelAkun2;
    private javax.swing.JComboBox<String> tahunBayar;
    private com.toedter.calendar.JDateChooser tanggalBayar;
    // End of variables declaration//GEN-END:variables
}
