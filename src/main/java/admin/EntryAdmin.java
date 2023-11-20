/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin;

import admin.CRUD.create.data_pembayaran;
import connection.connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Eren
 */
public class EntryAdmin extends javax.swing.JFrame {

    /**
     * Creates new form EntryAdmin
     */
     Connection c;
    String sql;
    Statement stmnt;
    public EntryAdmin() {
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

        bEntry = new javax.swing.JButton();
        bHistory = new javax.swing.JButton();
        bLogout = new javax.swing.JButton();
        bDashboard = new javax.swing.JButton();
        id_akun2 = new javax.swing.JLabel();
        id_akun3 = new javax.swing.JLabel();
        id_akun4 = new javax.swing.JLabel();
        id_akun5 = new javax.swing.JLabel();
        id_akun = new javax.swing.JLabel();
        tanggalBayar = new com.toedter.calendar.JDateChooser();
        submitButton = new javax.swing.JButton();
        jIdsppCombo = new javax.swing.JComboBox<>();
        bulanBayar = new javax.swing.JComboBox<>();
        jumlahBayar = new javax.swing.JTextField();
        tahunBayar = new javax.swing.JComboBox<>();
        jIdakunCombo = new javax.swing.JComboBox<>();
        id_akun6 = new javax.swing.JLabel();
        id_akun1 = new javax.swing.JLabel();
        jNisnCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        kwitansiArea = new javax.swing.JTextArea();
        id_akun7 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bEntry.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bEntry.setText("Entry Transaksi");
        bEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEntryActionPerformed(evt);
            }
        });

        bHistory.setText("Lihat History");
        bHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHistoryActionPerformed(evt);
            }
        });

        bLogout.setText("Log Out");
        bLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLogoutActionPerformed(evt);
            }
        });

        bDashboard.setText("Dashboard");
        bDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDashboardActionPerformed(evt);
            }
        });

        id_akun2.setText("Tanggal Bayar");

        id_akun3.setText("Bulan Dibayar");

        id_akun4.setText("Tahun Dibayar");

        id_akun5.setText("Id SPP");

        id_akun.setText("Akun Petugas");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jIdsppCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

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

        jIdakunCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        id_akun6.setText("Jumlah Bayar");

        id_akun1.setText("NISN");

        jNisnCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jNisnCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNisnComboActionPerformed(evt);
            }
        });

        kwitansiArea.setColumns(20);
        kwitansiArea.setRows(5);
        jScrollPane1.setViewportView(kwitansiArea);

        id_akun7.setText("Kwitansi");

        jToggleButton1.setText("Cetak");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bDashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_akun4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(id_akun6)
                            .addComponent(jumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_akun3)
                            .addComponent(bulanBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_akun7)
                            .addComponent(jToggleButton1))
                        .addGap(42, 42, 42)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(id_akun4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bDashboard)
                            .addComponent(bEntry)
                            .addComponent(bHistory)
                            .addComponent(bLogout))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_akun)
                            .addComponent(id_akun7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
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
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bEntryActionPerformed

    private void bHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHistoryActionPerformed
        new HistoryAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bHistoryActionPerformed

    private void bDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDashboardActionPerformed
        new dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboardActionPerformed

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

                int additionalPayments = JumlahBayar / nominal; 

                int currentMonth = Arrays.asList(new String[]{"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "December"}).indexOf(BulanBayar);
                
                // Prepare a SQL insert statement to add a new payment entry
                java.sql.PreparedStatement statement = ce.prepareStatement("INSERT INTO data_pembayaran(id_akun, nisn, tgl_bayar, bulan_dibayar,tahun_dibayar,id_spp,jumlah_bayar,id_akun_siswa) values(?,?,?,?,?,?,?,?)");
                if (JumlahBayar == nominal) {
                    
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

                    statement.executeUpdate(); 
                     // Prepare the kwitansi text
                    String kwitansi = "Kwitansinya\n\n";
                    kwitansi += "Petugas: " + selectedUsername + "\n";
                    kwitansi += "NISN: " + selectedNisn + "\n";
                    kwitansi += "Tanggal Bayar: " + formattedDate + "\n";
                    kwitansi += "Bulan Bayar: " + BulanBayar + "\n";
                    kwitansi += "Tahun Bayar: " + tahunBayars + "\n";
                    kwitansi += "SPP: " + selectedSPP + "\n";
                    kwitansi += "Nominal: " + HJumlahBayar + "\n"; // Use the updated 'gabungN' for total

                    // Display the updated 'kwitansi' text in the JTextArea
                    kwitansiArea.setText(kwitansi);
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
                    statement.setInt(7, nominal);
                    statement.setString(8, id_akun_siswa);

                    statement.executeUpdate(); 
                     String kwitansi = "Kwitansinya\n\n";
                    kwitansi += "Petugas: " + selectedUsername + "\n";
                    kwitansi += "NISN: " + selectedNisn + "\n";
                    kwitansi += "Tanggal Bayar: " + formattedDate + "\n";
                    kwitansi += "Bulan Bayar: " + BulanBayar + " dan "+ nextBulanBayar + "\n";
                    kwitansi += "Tahun Bayar: " + tahunBayars + "\n";
                    kwitansi += "SPP: " + selectedSPP + "\n";
                    kwitansi += "Nominal: " + HJumlahBayar + "\n"; // Use the updated 'gabungN' for total

                    // Display the updated 'kwitansi' text in the JTextArea
                    kwitansiArea.setText(kwitansi);
                    BulanBayar = nextBulanBayar; 
                    
                }

               
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

    private void bulanBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulanBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bulanBayarActionPerformed

    private void tahunBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunBayarActionPerformed
        populateBulanBayarCombo();
    }//GEN-LAST:event_tahunBayarActionPerformed

    private void jNisnComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNisnComboActionPerformed
        populateBulanBayarCombo();
    }//GEN-LAST:event_jNisnComboActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        String Text2Print = kwitansiArea.getText();
         String outputFileName = "kwitansi siswa.pdf";

    Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
        document.open();

        // Tambahkan teks dari JTextArea ke dokumen PDF
        Paragraph paragraph = new Paragraph(Text2Print);
        document.add(paragraph);

        // Tutup dokumen
        document.close();

        JOptionPane.showMessageDialog(this, "File PDF berhasil dibuat: " + outputFileName);
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void bLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLogoutActionPerformed
        new login.login().show();
        this.dispose();
    }//GEN-LAST:event_bLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(EntryAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntryAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDashboard;
    private javax.swing.JButton bEntry;
    private javax.swing.JButton bHistory;
    private javax.swing.JButton bLogout;
    private javax.swing.JComboBox<String> bulanBayar;
    private javax.swing.JLabel id_akun;
    private javax.swing.JLabel id_akun1;
    private javax.swing.JLabel id_akun2;
    private javax.swing.JLabel id_akun3;
    private javax.swing.JLabel id_akun4;
    private javax.swing.JLabel id_akun5;
    private javax.swing.JLabel id_akun6;
    private javax.swing.JLabel id_akun7;
    private javax.swing.JComboBox<String> jIdakunCombo;
    private javax.swing.JComboBox<String> jIdsppCombo;
    private javax.swing.JComboBox<String> jNisnCombo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField jumlahBayar;
    private javax.swing.JTextArea kwitansiArea;
    private javax.swing.JButton submitButton;
    private javax.swing.JComboBox<String> tahunBayar;
    private com.toedter.calendar.JDateChooser tanggalBayar;
    // End of variables declaration//GEN-END:variables
}
