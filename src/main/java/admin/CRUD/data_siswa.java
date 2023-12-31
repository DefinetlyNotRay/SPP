/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin.CRUD;
import admin.dashboardCRUD;
import admin.dashboard;
import connection.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Eren
 */
public class data_siswa extends javax.swing.JFrame {

    /**
     * Creates new form data_siswa
     */
     Statement stmnt;
      String sql;
      String sql2;
      String sql3;
      ResultSet rs;
      Connection c;
          private DefaultTableModel TableModel;
       private Object[] selectedRowData;
    public data_siswa() {
        initComponents();
         c = connection.getConnection();
         try {
              stmnt = c.createStatement(); // Initialize the statement object
          } catch (SQLException ex) {
              Logger.getLogger(admin.CRUD.create.data_akun.class.getName()).log(Level.SEVERE, null, ex);
          }
         this.TampilData();
    }
private void TampilData(){
     int no = 1; // Counter variable
        TableModel = new DefaultTableModel(); // Initialize table model
        // Add columns to the table model
        TableModel.addColumn("No");
        TableModel.addColumn("NISN");
        TableModel.addColumn("NIS");
        TableModel.addColumn("Nama");
        TableModel.addColumn("Id Kelas");
        TableModel.addColumn("Alamat");
        TableModel.addColumn("No Telpon");
        TableModel.addColumn("Id SPP");
                TableModel.addColumn("Akun Siswa");

        
        tabelAkun3.setModel(TableModel); // Set the table model

        Connection conn = connection.getConnection(); // Establish database connection
        try {
            java.sql.Statement statement = conn.createStatement();
            sql = "SELECT * FROM `data_siswa`"; // SQL query to retrieve data
            java.sql.ResultSet res = statement.executeQuery(sql); // Execute query
            while (res.next()) {
                // Add a row to the table model with data from the database
                TableModel.addRow(new Object[]{
                    no++,
                    res.getInt("nisn"),
                    res.getInt("nis"),
                    res.getString("nama"),
                    res.getInt("id_kelas"),
                    res.getString("alamat"),
                    res.getString("no_telp"),
                    res.getInt("id_spp"),
                    res.getInt("id_akun"),

                    
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // Print error message
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAkun3 = new javax.swing.JTable();
        addData = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton1 = new javax.swing.JButton();

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

        tabelAkun3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "NISN", "NIS", "Nama", "Id Kelas", "Alamat", "No Telpon", "Id SPP", "AKun Siswa"
            }
        ));
        tabelAkun3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAkun3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelAkun3);

        addData.setText("Add New Data");
        addData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDataActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton1.setText("Delete");
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
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
                .addComponent(bAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bPembayaran)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(bCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDashboard1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addData)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(65, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(84, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDashboard1)
                    .addComponent(bLogout)
                    .addComponent(bSPP)
                    .addComponent(bSiswa)
                    .addComponent(bKelas)
                    .addComponent(bCRUD)
                    .addComponent(bAkun)
                    .addComponent(bPembayaran))
                .addGap(18, 18, 18)
                .addComponent(addData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton1)
                    .addComponent(editButton))
                .addGap(46, 46, 46))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(75, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(76, Short.MAX_VALUE)))
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
private String selectedNisn;
    private void tabelAkun3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAkun3MouseClicked
        int selectedRowIndex = tabelAkun3.getSelectedRow();

         if (selectedRowIndex != -1) {
            selectedRowData = new Object[]{
                Integer.valueOf(tabelAkun3.getValueAt(selectedRowIndex, 0).toString()), // Assuming column 0 is id_kelas
                selectedNisn =  tabelAkun3.getValueAt(selectedRowIndex, 1).toString(), // Assuming column 0 is id_kelas
                tabelAkun3.getValueAt(selectedRowIndex, 2), // Assuming column 1 is nama_kelas
                tabelAkun3.getValueAt(selectedRowIndex, 3), // Assuming column 2 is kompetensi keahlian
                Integer.valueOf(tabelAkun3.getValueAt(selectedRowIndex, 4).toString()),
                tabelAkun3.getValueAt(selectedRowIndex, 5),
                tabelAkun3.getValueAt(selectedRowIndex, 6),
                Integer.valueOf(tabelAkun3.getValueAt(selectedRowIndex, 7).toString()),
                Integer.valueOf(tabelAkun3.getValueAt(selectedRowIndex, 8).toString()),

            };
        }
    }//GEN-LAST:event_tabelAkun3MouseClicked

    private void addDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDataActionPerformed
        new admin.CRUD.create.data_pembayaran().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addDataActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (selectedRowData != null) {
        int no = Integer.parseInt(selectedRowData[0].toString());
        String NISN = selectedRowData[1].toString();
        String nis = selectedRowData[2].toString();
        String nama = selectedRowData[3].toString();
        int id_kelas = Integer.parseInt(selectedRowData[4].toString());
        String alamat = selectedRowData[5].toString();
        String no_telp = selectedRowData[6].toString();
        int spp = Integer.parseInt(selectedRowData[7].toString());
        int akun_siswa = Integer.parseInt(selectedRowData[8].toString());

        // Pass the data to data_siswaEdit
        admin.CRUD.edit.data_siswa editForm = new admin.CRUD.edit.data_siswa(no,NISN, nis, nama, id_kelas, alamat, no_telp, spp, akun_siswa);
        editForm.setVisible(true);
        this.dispose();
    
    }

        

    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed
        Connection conn = connection.getConnection();

        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                // Prepare a SQL delete statement to remove the selected ticket information
                java.sql.PreparedStatement statement = conn.prepareStatement("delete from data_siswa where nisn ='" + selectedNisn + "'");
                statement.executeUpdate(); // Execute the SQL delete statement
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                TampilData(); // Refresh the displayed data

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
        String countQuery = "SELECT COUNT(nisn) FROM data_siswa;";

        try {
            Statement statement = conn.createStatement();

            // Get the total count
            ResultSet resultSet = statement.executeQuery(countQuery);
            int totalRecords = 0;
            if (resultSet.next()) {
                totalRecords = resultSet.getInt(1);
            }

            // Reset the auto-increment value
            if (totalRecords > 0) {
                String resetAutoIncrementQuery = "ALTER TABLE data_siswa AUTO_INCREMENT = " + (totalRecords + 1);
                statement.executeUpdate(resetAutoIncrementQuery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_deleteButton1ActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addData;
    private javax.swing.JButton bAkun;
    private javax.swing.JButton bCRUD;
    private javax.swing.JButton bDashboard1;
    private javax.swing.JButton bKelas;
    private javax.swing.JButton bLogout;
    private javax.swing.JButton bPembayaran;
    private javax.swing.JButton bSPP;
    private javax.swing.JButton bSiswa;
    private javax.swing.JButton deleteButton1;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelAkun3;
    // End of variables declaration//GEN-END:variables
}
