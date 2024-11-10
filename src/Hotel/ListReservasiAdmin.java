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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erisa
 */
public class ListReservasiAdmin extends javax.swing.JFrame {

    private int selectedIDReservasi = -1;
    private int selectedIDKamar = -1;

    public ListReservasiAdmin() {
        if (isSessionValid()) {
            setTitle("Page List History Reservasi Hotel");
            initComponents();

            showData();

            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    exit();
                }
            });
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
                String query = "SELECT * FROM reservasi JOIN kamar ON reservasi.id_kamar=kamar.id_kamar "
                        + "JOIN tipe_kamar ON kamar.id_tipe_kamar=tipe_kamar.id_tipe_kamar "
                        + "JOIN user ON user.id_user=reservasi.id_user";
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                DefaultTableModel tblModel = new DefaultTableModel();
                tblModel.addColumn("IDReservasi");
                tblModel.addColumn("IDKamar");
                tblModel.addColumn("No.");
                tblModel.addColumn("Nama Customer");
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
                        rs.getString("id_reservasi"),
                        rs.getString("id_kamar"),
                        no,
                        rs.getString("nama"),
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

                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setWidth(0);
                jTable1.getColumnModel().getColumn(0).setResizable(true);
                jTable1.getColumnModel().getColumn(1).setMinWidth(1);
                jTable1.getColumnModel().getColumn(1).setMaxWidth(1);
                jTable1.getColumnModel().getColumn(1).setWidth(1);
                jTable1.getColumnModel().getColumn(1).setResizable(true);

            }

        } catch (Exception ex) {

        }
    }

    private void searchReservasi() {
        try (Connection conn = ConnectionDatabase.getConnection()) {
            if (conn != null) {
                String cari = txtCari.getText().toString();
                String query = "SELECT * FROM reservasi JOIN kamar ON reservasi.id_kamar=kamar.id_kamar "
                        + "JOIN tipe_kamar ON kamar.id_tipe_kamar=tipe_kamar.id_tipe_kamar "
                        + "JOIN user ON user.id_user=reservasi.id_user WHERE kamar.nomor LIKE ?";

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, "%" + cari + "%");

                ResultSet rs = stmt.executeQuery();

                DefaultTableModel tblModel = new DefaultTableModel();
                tblModel.addColumn("IDReservasi");
                tblModel.addColumn("IDKamar");
                tblModel.addColumn("No.");
                tblModel.addColumn("Nama Customer");
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
                        rs.getString("id_reservasi"),
                        rs.getString("id_kamar"),
                        no,
                        rs.getString("nama"),
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

                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setWidth(0);
                jTable1.getColumnModel().getColumn(0).setResizable(true);
                jTable1.getColumnModel().getColumn(1).setMinWidth(1);
                jTable1.getColumnModel().getColumn(1).setMaxWidth(1);
                jTable1.getColumnModel().getColumn(1).setWidth(1);
                jTable1.getColumnModel().getColumn(1).setResizable(true);

            }

        } catch (Exception ex) {

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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBatal = new javax.swing.JButton();
        btnSelesai = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuDashboard = new javax.swing.JMenu();
        menuReservasi = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel1.setText("List History Reservasi Kamar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama Customer", "Nomor Kamar", "Tipe Kamar", "Deskripsi", "Harga", "Status", "Reserved Date"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnBatal.setText("Batalkan Reservasi");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnSelesai.setText("Selesaikan Reservasi");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });

        jLabel2.setText("Cari Nomor Kamar");

        menuDashboard.setText("Dashboard");
        menuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDashboardMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuDashboard);

        menuReservasi.setText("List Reservasi");
        menuReservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuReservasiMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuReservasi);

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
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        if (selectedIDReservasi != -1) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                if (conn != null) {

                    String queryUpdate = "UPDATE reservasi SET status_reservasi = 'cancelled' where id_reservasi=?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(queryUpdate)) {
                        psUpdate.setInt(1, selectedIDReservasi);

                        psUpdate.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String queryUpdateKamar = "UPDATE kamar SET status = 'available' where id_kamar=?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(queryUpdateKamar)) {
                        psUpdate.setInt(1, selectedIDKamar);

                        psUpdate.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(this, "Sukses Batalkan Reservasi!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    showData();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Pilih Data Resevasi terlebih dahulu", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:
        if (selectedIDReservasi != -1) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                if (conn != null) {

                    String queryUpdate = "UPDATE reservasi SET status_reservasi = 'completed' where id_reservasi=?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(queryUpdate)) {
                        psUpdate.setInt(1, selectedIDReservasi);

                        psUpdate.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String queryUpdateKamar = "UPDATE kamar SET status = 'available' where id_kamar=?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(queryUpdateKamar)) {
                        psUpdate.setInt(1, selectedIDKamar);

                        psUpdate.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(this, "Sukses Selesaikan Reservasi!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    showData();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Pilih Data Reservasi terlebih dahulu", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String status = jTable1.getValueAt(selectedRow, 8).toString();
            System.out.println(status);
            if (status.equalsIgnoreCase("booked")) {
                selectedIDReservasi = Integer.valueOf(jTable1.getValueAt(selectedRow, 0).toString());
                selectedIDKamar = Integer.valueOf(jTable1.getValueAt(selectedRow, 1).toString());
                System.out.println("ID Reservasi: " + selectedIDReservasi);
                System.out.println("ID Kamar: " + selectedIDKamar);
            } else {
                JOptionPane.showMessageDialog(this, "Resevasi telah dicancel / diselesaikan", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
        searchReservasi();
    }//GEN-LAST:event_txtCariKeyPressed

    private void menuDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDashboardMouseClicked
        // TODO add your handling code here:
        new DashboardAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuDashboardMouseClicked

    private void menuReservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReservasiMouseClicked
        // TODO add your handling code here:
        new ListReservasiAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuReservasiMouseClicked

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
            java.util.logging.Logger.getLogger(ListReservasiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListReservasiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListReservasiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListReservasiAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListReservasiAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu menuDashboard;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuReservasi;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
