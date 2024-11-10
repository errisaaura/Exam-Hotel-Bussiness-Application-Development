/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import Connection.ConnectionDatabase;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erisa
 */
public class HistoryReservasiCust extends javax.swing.JFrame {

    /**
     * Creates new form HistoryReservasiCust
     */
    public HistoryReservasiCust() {
        if (isSessionValid()) {
            setTitle("Page List History Reservasi Customer");
            initComponents();

            showData();

            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    exit();
                }
            });
        } else {
            this.dispose();
        }

    }

    private boolean isSessionValid() {
        if (!Session.isSessionValid()) {
            JOptionPane.showMessageDialog(this, "Harap Login Terlebih Dahulu", "Error", JOptionPane.ERROR_MESSAGE);

            SwingUtilities.invokeLater(() -> {
                new Login().setVisible(true);
            });

            SwingUtilities.invokeLater(this::dispose);

            return false;
        }
        return true;
    }

    private void exit() {
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            return;
        }
    }

    private void showData() {
        try (Connection conn = ConnectionDatabase.getConnection()) {
            if (conn != null) {
                Integer idUser = 1;
                String query = "SELECT * FROM reservasi JOIN kamar ON reservasi.id_kamar=kamar.id_kamar JOIN tipe_kamar ON kamar.id_tipe_kamar=tipe_kamar.id_tipe_kamar WHERE id_user = " + idUser;
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                DefaultTableModel tblModel = new DefaultTableModel();
                tblModel.addColumn("No.");
                tblModel.addColumn("Nomor Kamar");
                tblModel.addColumn("Tipe Kamar");
                tblModel.addColumn("Deskripsi");
                tblModel.addColumn("Harga");
                tblModel.addColumn("Status");
                tblModel.addColumn("Reserved Date");

                tblModel.getDataVector().removeAllElements();
                tblModel.fireTableDataChanged();
                tblModel.setRowCount(0);

                int no = 1;
                String status;
                while (rs.next()) {
                    Object[] data = new Object[]{
                        no,
                        rs.getString("nomor"),
                        rs.getString("nama_tipe"),
                        rs.getString("deskripsi"),
                        rs.getString("harga"),
                        rs.getString("status_reservasi"),
                        new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("reserve_in_date"))
                    };
                    tblModel.addRow(data);
                    jTable1.setModel(tblModel);
                    no++;
                }

            }

        } catch (Exception ex) {

        }
    }

//    private void searchHistory() {
//        try (Connection conn = ConnectionDatabase.getConnection()) {
//            if (conn != null) {
//                Integer idUser = Session.getId();
//                String cari = txtCari.getText().toString();
//                String query = "SELECT * FROM reservasi "
//                        + "JOIN kamar ON reservasi.id_kamar = kamar.id_kamar "
//                        + "JOIN tipe_kamar ON kamar.id_tipe_kamar = tipe_kamar.id_tipe_kamar "
//                        + "WHERE id_user = ? AND kamar.nomor LIKE ?";
//
//                PreparedStatement stmt = conn.prepareStatement(query);
//                stmt.setInt(1, idUser);
//                stmt.setString(2, "%" + cari + "%");
//                ResultSet rs = stmt.executeQuery();
//                DefaultTableModel tblModel = new DefaultTableModel();
//                tblModel.addColumn("No.");
//                tblModel.addColumn("Nomor Kamar");
//                tblModel.addColumn("Tipe Kamar");
//                tblModel.addColumn("Deskripsi");
//                tblModel.addColumn("Harga");
//                tblModel.addColumn("Status");
//                tblModel.addColumn("Reserved Date");
//
//                tblModel.getDataVector().removeAllElements();
//                tblModel.fireTableDataChanged();
//                tblModel.setRowCount(0);
//
//                int no = 1;
//                String status;
//                while (rs.next()) {
//                    Object[] data = new Object[]{
//                        no,
//                        rs.getString("nomor"),
//                        rs.getString("nama_tipe"),
//                        rs.getString("deskripsi"),
//                        rs.getString("harga"),
//                        rs.getString("status_reservasi"),
//                        new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("reserve_in_date"))
//                    };
//                    tblModel.addRow(data);
//                    jTable1.setModel(tblModel);
//                    no++;
//                }
//
//            }
//
//        } catch (Exception ex) {
//
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuDashboard = new javax.swing.JMenu();
        menuKamar = new javax.swing.JMenu();
        menuHistory = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel1.setText("List History Reservasi Kamar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nomor Kamar", "Tipe Kamar", "Deskripsi", "Harga", "Status", "Reserved Date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        menuDashboard.setText("Dashboard");
        menuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDashboardMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuDashboard);

        menuKamar.setText("List Kamar");
        menuKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKamarMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuKamar);

        menuHistory.setText("History Reservasi");
        menuHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHistoryMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuHistory);

        menuLogout.setText("Logout");
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDashboardMouseClicked
        // TODO add your handling code here:
        new DashboardCust().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuDashboardMouseClicked

    private void menuKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKamarMouseClicked
        // TODO add your handling code here:
        new ListKamarCust().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuKamarMouseClicked

    private void menuHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHistoryMouseClicked
        // TODO add your handling code here:
        new HistoryReservasiCust().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuHistoryMouseClicked

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            Session.clearSession();
            new Login().setVisible(true);
            this.dispose();
        } else {
            return;
        }
    }//GEN-LAST:event_menuLogoutMouseClicked

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
            java.util.logging.Logger.getLogger(HistoryReservasiCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryReservasiCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryReservasiCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryReservasiCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryReservasiCust().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu menuDashboard;
    private javax.swing.JMenu menuHistory;
    private javax.swing.JMenu menuKamar;
    private javax.swing.JMenu menuLogout;
    // End of variables declaration//GEN-END:variables
}
