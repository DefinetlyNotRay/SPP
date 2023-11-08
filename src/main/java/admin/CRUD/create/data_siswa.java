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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Eren
 */
public class data_siswa extends javax.swing.JFrame {

    /**
     * Creates new form data_siswa
     */
    Connection c;
    String sql;
    Statement stmnt;
    public data_siswa() {
        initComponents();
         c = connection.getConnection();
        try {
            stmnt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(data_pembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sppCombo();
        this.kelasCombo();
        this.akunCombo();

    }
     private HashMap<String, Integer> sppDisplayTextToIdMap = new HashMap<>();

    private void sppCombo(){
        try{
        sql = "SELECT * FROM data_spp";
        java.sql.ResultSet res = stmnt.executeQuery(sql);
        while(res.next()){
            int id_spp = res.getInt("id_spp");
            int nominal = res.getInt("nominal");
            int tahun = res.getInt("tahun");

            String displayText = tahun  + " | " + nominal ;
            jComboSpp.addItem(displayText);

            // Store the mapping in the HashMap
            sppDisplayTextToIdMap.put(displayText, id_spp);
        }
    } catch(SQLException e){
        e.printStackTrace(); // Handle the exception properly
    }
    }
         private HashMap<String, Integer> kelasDisplayTextToIdMap = new HashMap<>();

    private void kelasCombo(){
      try{
         sql = "SELECT * FROM data_kelas";
         java.sql.ResultSet res = stmnt.executeQuery(sql);
         while(res.next()){
             int id_kelas = res.getInt("id_kelas");
             String nama_kelas = res.getString("nama_kelas");
             jComboKelas.addItem(nama_kelas);
             kelasDisplayTextToIdMap.put(nama_kelas,id_kelas);
         }
      }catch(SQLException e){
         e.printStackTrace(); // Handle the exception properly

      }  
    }
    private HashMap<String, Integer> akunDisplayTexyToIdMap = new HashMap<>();
    private void akunCombo(){
        try{
            sql = "SELECT * FROM data_akun WHERE level IN ('siswa')";
            java.sql.ResultSet res = stmnt.executeQuery(sql);
            while(res.next()){
                int id_akun = res.getInt("id_akun");
                String nama_akun = res.getString("nama");
                
                jComboAkun.addItem(nama_akun);
                akunDisplayTexyToIdMap.put(nama_akun,id_akun);
            }
        }catch(SQLException e){
            e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboKelas = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        eNisn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        eTelpo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboSpp = new javax.swing.JComboBox<>();
        jComboAkun = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        eAlamat = new javax.swing.JTextField();
        eNama = new javax.swing.JTextField();
        eNis = new javax.swing.JTextField();
        bSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bSPP.setText("Data SPP");
        bSPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSPPActionPerformed(evt);
            }
        });

        bSiswa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jLabel1.setText("NISN");

        jLabel2.setText("NIS");

        jLabel3.setText("Nama");

        jLabel4.setText("Kelas");

        jComboKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboKelasActionPerformed(evt);
            }
        });

        jLabel5.setText("Alamat");

        jLabel6.setText("No Telpon");

        jLabel7.setText("SPP");

        jComboSpp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jComboAkun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        jLabel8.setText("Akun SIswa");

        bSubmit.setText("Submit");
        bSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitActionPerformed(evt);
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
                        .addComponent(bSPP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSiswa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPembayaran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                        .addComponent(bCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDashboard1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)
                                        .addComponent(eNama, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                        .addComponent(jComboKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(eNis, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eNisn, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(102, 102, 102)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(eTelpo)
                                    .addComponent(jComboSpp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboAkun, 0, 186, Short.MAX_VALUE)
                                    .addComponent(eAlamat)))
                            .addComponent(bSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDashboard1)
                    .addComponent(bLogout)
                    .addComponent(bSPP)
                    .addComponent(bSiswa)
                    .addComponent(bKelas)
                    .addComponent(bCRUD)
                    .addComponent(bAkun)
                    .addComponent(bPembayaran))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboSpp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eTelpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(29, 29, 29)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(bSubmit)
                .addContainerGap(107, Short.MAX_VALUE))
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

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
       String nisn = eNisn.getText();
       String nis = eNis.getText();
       String nama = eNama.getText();
       String kelas =(String) jComboKelas.getSelectedItem();
       Integer id_kelas = kelasDisplayTextToIdMap.get(kelas);
       String alamat = eAlamat.getText();
       String no_telp = eTelpo.getText();
       String spp = (String) jComboSpp.getSelectedItem();
       Integer id_spp = sppDisplayTextToIdMap.get(spp);
       String akun = (String) jComboAkun.getSelectedItem();
       Integer id_akun = akunDisplayTexyToIdMap.get(akun);
        if (nisn.isEmpty() || nis.isEmpty() || nama.isEmpty() || kelas.isEmpty() || alamat.isEmpty() || no_telp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return; // Exit the method without executing the SQL statement
        }
                 sql = "INSERT INTO data_siswa VALUE('"+nisn+"','"+nis+"','"+nama+"','"+id_kelas+"','"+alamat+"','"+no_telp+"','"+id_spp+"','"+id_akun+"');";

       try{
           stmnt.execute(sql);
           JOptionPane.showMessageDialog(null, "Data Siswa created successfully.");
           new admin.CRUD.data_siswa().setVisible(true);
            this.dispose();
       }catch(SQLException e){
                   System.out.println(e.getMessage());

       }
    }//GEN-LAST:event_bSubmitActionPerformed

    private void jComboKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboKelasActionPerformed

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
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_siswa().setVisible(true);
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
    private javax.swing.JButton bSubmit;
    private javax.swing.JTextField eAlamat;
    private javax.swing.JTextField eNama;
    private javax.swing.JTextField eNis;
    private javax.swing.JTextField eNisn;
    private javax.swing.JTextField eTelpo;
    private javax.swing.JComboBox<String> jComboAkun;
    private javax.swing.JComboBox<String> jComboKelas;
    private javax.swing.JComboBox<String> jComboSpp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
