/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package role;
import admin.dashboard;
import petugas.dashboardPetugas;
import siswa.dashboardSiswa;




/**
 *
 * @author Eren
 */
 
public class verif {
  
 
    
     public static void handleAdmin() {
       new dashboard().setVisible(true);
       
    }

    public static void handlePetugas() {
       new dashboardPetugas().setVisible(true);
    }

    public static void handleSiswa() {
      new dashboardSiswa(0).setVisible(true);
    }
}
