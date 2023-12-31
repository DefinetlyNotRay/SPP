/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin.CRUD.create;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Eren
 */
public class data_pembayaran extends javax.swing.JFrame {

    /**
     * Creates new form data_pembayaran
     */
    Connection c;
    String sql;
    Statement stmnt;
    public data_pembayaran(){
        initComponents();
        c = connection.getConnection();
        try {
            stmnt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.petugasCombo();
        this.nisnCombo();
        this.sppCombo();
        
    }
    
    private HashMap<String, Integer> usernameToIdAkunMap = new HashMap<>();

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
        }
        
    } catch(SQLException e){
        e.printStackTrace(); // Handle the exception properly
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

        // Populate the bulanBayar combo box with all months in order
        bulanBayar.removeAllItems(); // Clear existing items
        String[] allMonths = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "December"};
        bulanBayar.setModel(new DefaultComboBoxModel<>(allMonths));

        // Remove paid months from the list
        if (!paidMonths.isEmpty()) {
            for (String paidMonth : paidMonths) {
                bulanBayar.removeItem(paidMonth);
            }
        }

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

        bSPP = new javax.swing.JButton();
        bSiswa = new javax.swing.JButton();
        bKelas = new javax.swing.JButton();
        bAkun = new javax.swing.JButton();
        bPembayaran = new javax.swing.JButton();
        bCRUD = new javax.swing.JButton();
        bDashboard1 = new javax.swing.JButton();
        bLogout = new javax.swing.JButton();
        id_akun5 = new javax.swing.JLabel();
        tanggalBayar = new com.toedter.calendar.JDateChooser();
        jIdsppCombo = new javax.swing.JComboBox<>();
        jumlahBayar = new javax.swing.JTextField();
        jIdakunCombo = new javax.swing.JComboBox<>();
        id_akun6 = new javax.swing.JLabel();
        id_akun1 = new javax.swing.JLabel();
        jNisnCombo = new javax.swing.JComboBox<>();
        id_akun2 = new javax.swing.JLabel();
        id_akun3 = new javax.swing.JLabel();
        id_akun4 = new javax.swing.JLabel();
        id_akun = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        bulanBayar = new javax.swing.JComboBox<>();
        tahunBayar = new javax.swing.JComboBox<>();

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

        id_akun5.setText("Id SPP");

        jIdsppCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

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

        id_akun.setText("Akun Petugas");

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

        tahunBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Select Year","2023","2024"}));
        tahunBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunBayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(bSPP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(bSiswa)
                .addGap(6, 6, 6)
                .addComponent(bKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bPembayaran)
                        .addGap(218, 218, 218)
                        .addComponent(bCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bDashboard1)
                        .addGap(6, 6, 6)
                        .addComponent(bLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_akun5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jIdsppCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(id_akun, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(id_akun1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(id_akun2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jIdakunCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jNisnCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tanggalBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(id_akun4)
                            .addComponent(tahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_akun3)
                            .addComponent(bulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_akun6)
                            .addComponent(jumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(131, 131, 131))))
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
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(id_akun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jIdakunCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(id_akun4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jIdsppCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(submitButton)))
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
                        .addComponent(jumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSPPActionPerformed
        new data_spp().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bSPPActionPerformed

    private void bSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiswaActionPerformed
        new data_siswa().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bSiswaActionPerformed

    private void bKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKelasActionPerformed
        new data_kelas().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bKelasActionPerformed

    private void bAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAkunActionPerformed
        new data_akun().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bAkunActionPerformed

    private void bPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPembayaranActionPerformed
        new data_pembayaran().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPembayaranActionPerformed

    private void bCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCRUDActionPerformed
        new dashboardCRUD().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bCRUDActionPerformed

    private void bDashboard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboard1ActionPerformed
        new dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboard1ActionPerformed

    private void bLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLogoutActionPerformed
        new login.login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogoutActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
          String selectedUsername = (String) jIdakunCombo.getSelectedItem();
    Integer id_akun = usernameToIdAkunMap.get(selectedUsername);

    String selectedNisn = (String) jNisnCombo.getSelectedItem();
    Date selectedDate = tanggalBayar.getDate();
    String selectedSPP = (String) jIdsppCombo.getSelectedItem();
    Integer id_spp = sppDisplayTextToIdMap.get(selectedSPP);

    String tahunBayars = (String) tahunBayar.getSelectedItem();
    String BulanBayar = (String) bulanBayar.getSelectedItem();
    String HJumlahBayar = jumlahBayar.getText();
    if (tahunBayars.isEmpty() || BulanBayar.isEmpty() || HJumlahBayar.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        return; // Exit the method without executing the SQL statement
    }

    try {
        Connection ce = connection.getConnection();
        sql = "SELECT * FROM data_siswa WHERE nisn = '"+ selectedNisn +"'";
        java.sql.ResultSet res = stmnt.executeQuery(sql);
        int JumlahBayar = Integer.parseInt(jumlahBayar.getText());
       if (res.next()) {
            String id_akun_siswa = res.getString("id_akun");
            int id_sppd = res.getInt("id_spp");
            String sql2 = "SELECT * FROM data_spp WHERE id_spp = '"+ id_sppd +"'";
            java.sql.ResultSet res2 = stmnt.executeQuery(sql2);
             
            if (res2.next()) {
                int nominal = res2.getInt("nominal");

                int additionalPayments = JumlahBayar / nominal; // Calculate the number of additional payments

                int currentMonth = Arrays.asList(new String[]{"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "December"}).indexOf(BulanBayar);
                
                // Prepare a SQL insert statement to add a new payment entry
                java.sql.PreparedStatement statement = ce.prepareStatement("INSERT INTO data_pembayaran(id_akun, nisn, tgl_bayar, bulan_dibayar,tahun_dibayar,id_spp,jumlah_bayar,id_akun_siswa) values(?,?,?,?,?,?,?,?)");
                if (JumlahBayar == nominal) {
                    // If JumlahBayar is the same as nominal, insert it normally with the selected BulanBayar
                    statement.setInt(1, id_akun);
                    statement.setString(2, selectedNisn);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(selectedDate);
                    statement.setString(3, formattedDate);
                    statement.setString(4, BulanBayar);
                    statement.setString(5, tahunBayars);
                    statement.setInt(6, id_spp);
                    statement.setInt(7, nominal);
                    statement.setString(8, id_akun_siswa);

                    statement.executeUpdate(); // Execute the SQL insert statement
                     new admin.CRUD.data_pembayaran().setVisible(true);
                this.dispose();
                } else {
                   for (int i = 0; i < additionalPayments; i++) {
                    int nextMonth = (currentMonth + i) % 12;

                    String nextBulanBayar = new String[]{"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "December"}[nextMonth];

                    statement.setInt(1, id_akun);
                    statement.setString(2, selectedNisn);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(selectedDate);
                    statement.setString(3, formattedDate);
                    statement.setString(4, nextBulanBayar);
                    statement.setString(5, tahunBayars);
                    statement.setInt(6, id_spp);
                    statement.setInt(7, nominal); // Use nominal value for each additional payment
                    statement.setString(8, id_akun_siswa);

                    statement.executeUpdate(); // Execute the SQL insert statement

                    BulanBayar = nextBulanBayar; // Update BulanBayar for the next iteration
                }

                new admin.CRUD.data_pembayaran().setVisible(true);
                this.dispose();
                }
            } else {
                System.out.println("No data found for id_spp: " + id_sppd);
            }
        } else {
            System.out.println("No data found for nisn: " + selectedNisn);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
      

    }//GEN-LAST:event_submitButtonActionPerformed

    private void jNisnComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNisnComboActionPerformed
        populateBulanBayarCombo();
    }//GEN-LAST:event_jNisnComboActionPerformed

    private void bulanBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulanBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bulanBayarActionPerformed

    private void tahunBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunBayarActionPerformed
        populateBulanBayarCombo();
    }//GEN-LAST:event_tahunBayarActionPerformed

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

        /* Create and display the form */
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
    private javax.swing.JTextField jumlahBayar;
    private javax.swing.JButton submitButton;
    private javax.swing.JComboBox<String> tahunBayar;
    private com.toedter.calendar.JDateChooser tanggalBayar;
    // End of variables declaration//GEN-END:variables
}
