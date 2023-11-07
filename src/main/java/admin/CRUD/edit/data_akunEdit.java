/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin.CRUD.edit;
import admin.dashboardCRUD;
import admin.dashboard;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connection.connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Eren
 */
public class data_akunEdit extends javax.swing.JFrame {

    /**
     * Creates new form data_akunEdit
     */
     
      Statement stmnt;
    String sql;
    ResultSet rs;
    Connection c;
    Object[] selectedRowData;

     // Add these fields
    private int id_akun;
    private String username;
    private String password;
    private String nama;
    private String level;
    
    // Constructor
    public data_akunEdit(int id_akun, String username, String password, String nama, String level) {
        initComponents();
        c = connection.getConnection();
        
        // Set the fields
        this.id_akun = id_akun;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.level = level;
        
        // Set the text fields
        usernamee.setText(username);
        passwordd.setText(password);
        namaa.setText(nama);
        jlevelCombo.setSelectedItem(level);
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
        usernamee = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        namaa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jlevelCombo = new javax.swing.JComboBox<>();
        bSubmit = new javax.swing.JButton();

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

        bAkun.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        jLabel3.setText("Name");

        jLabel4.setText("Level");

        jlevelCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"admin","petugas","siswa"}));
        jlevelCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlevelComboActionPerformed(evt);
            }
        });

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
                .addContainerGap()
                .addComponent(bSPP, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSiswa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPembayaran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(bCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDashboard1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bSubmit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernamee, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordd, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namaa, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(jlevelCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bDashboard1)
                            .addComponent(bLogout)
                            .addComponent(bSPP)
                            .addComponent(bSiswa)
                            .addComponent(bKelas)
                            .addComponent(bCRUD)
                            .addComponent(bAkun)
                            .addComponent(bPembayaran))
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernamee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlevelCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(bSubmit)
                .addContainerGap(66, Short.MAX_VALUE))
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

    private void jlevelComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlevelComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlevelComboActionPerformed

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
        String usernameinput = usernamee.getText();
        String passwordinput = passwordd.getText();
        String nameinput = namaa.getText();
        String levelComboBox = jlevelCombo.getSelectedItem().toString();
        
        if (usernameinput.isEmpty() || passwordinput.isEmpty() || nameinput.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields.");
        return; // Exit the method without executing the SQL statement
        }
        sql = "UPDATE `data_akun` SET username='" + usernameinput + "', password='" + passwordinput + "', nama='" + nameinput + "', level='" + levelComboBox + "' WHERE id_akun ='"+ id_akun +"'";

       
        try{
           stmnt = c.createStatement();

           stmnt.execute(sql);
           
           JOptionPane.showMessageDialog(null, "Account created successfully.");
           new admin.CRUD.data_akun().setVisible(true);
            this.dispose();

        }catch(SQLException e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Error creating account. Please try again.");

       }
    }//GEN-LAST:event_bSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(data_akunEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_akunEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_akunEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_akunEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            new data_akunEdit(0, "", "", "", "").setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JComboBox<String> jlevelCombo;
    public javax.swing.JTextField namaa;
    public javax.swing.JTextField passwordd;
    public javax.swing.JTextField usernamee;
    // End of variables declaration//GEN-END:variables
}
