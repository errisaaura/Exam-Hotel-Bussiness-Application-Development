/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import Connection.ConnectionDatabase;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author erisa
 */
public class ListKamarCust extends javax.swing.JFrame {

    private int selectedID = -1;

    public ListKamarCust() {
        if(isSessionValid()){
            setTitle("Page List Kamar Hotel");
        initComponents();

        showData();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        }else{
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
                String query = "SELECT * FROM kamar JOIN tipe_kamar ON kamar.id_tipe_kamar = tipe_kamar.id_tipe_kamar";
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                DefaultTableModel tblModel = new DefaultTableModel();
                tblModel.addColumn("ID");
                tblModel.addColumn("No.");
                tblModel.addColumn("Nomor Kamar");
                tblModel.addColumn("Tipe Kamar");
                tblModel.addColumn("Deskripsi");
                tblModel.addColumn("Harga");
                tblModel.addColumn("Status");

                tblModel.getDataVector().removeAllElements();
                tblModel.fireTableDataChanged();
                tblModel.setRowCount(0);

                int no = 1;
                String status;
                while (rs.next()) {
                    Object[] data = new Object[]{
                        rs.getString("id_kamar"),
                        no,
                        rs.getString("nomor"),
                        rs.getString("nama_tipe"),
                        rs.getString("deskripsi"),
                        rs.getString("harga"),
                        rs.getString("status"),};
                    tblModel.addRow(data);
                    jTable1.setModel(tblModel);
                    no++;
                }

                jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());

                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setWidth(0);
                jTable1.getColumnModel().getColumn(0).setResizable(true);
            }

        } catch (Exception ex) {

        }
    }

    private void searchKamar() {
        try (Connection conn = ConnectionDatabase.getConnection()) {
            if (conn != null) {

                String selectItem = cmbCari.getSelectedItem().toString();
                String cari = "";
                if (selectItem == "Nomor") {
                    cari = "kamar.nomor";
                } else if (selectItem == "Tipe Kamar") {
                    cari = "tipe_kamar.nama_tipe";
                } else if (selectItem == "Status") {
                    cari = "kamar.status";
                }
                String query = "SELECT * FROM kamar JOIN tipe_kamar ON kamar.id_tipe_kamar = tipe_kamar.id_tipe_kamar WHERE " + cari + " LIKE '%" + txtCari.getText() + "%' ";
                PreparedStatement stmt = conn.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                DefaultTableModel tblModel = new DefaultTableModel();
                tblModel.addColumn("ID");
                tblModel.addColumn("No.");
                tblModel.addColumn("Nomor Kamar");
                tblModel.addColumn("Tipe Kamar");
                tblModel.addColumn("Deskripsi");
                tblModel.addColumn("Harga");
                tblModel.addColumn("Status");

                tblModel.getDataVector().removeAllElements();
                tblModel.fireTableDataChanged();
                tblModel.setRowCount(0);

                int no = 1;
                String status;
                while (rs.next()) {
                    Object[] data = new Object[]{
                        rs.getString("id_kamar"),
                        no,
                        rs.getString("nomor"),
                        rs.getString("nama_tipe"),
                        rs.getString("deskripsi"),
                        rs.getString("harga"),
                        rs.getString("status"),};
                    tblModel.addRow(data);
                    jTable1.setModel(tblModel);
                    no++;
                }
                jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());

                jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(0).setWidth(0);
                jTable1.getColumnModel().getColumn(0).setResizable(true);
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnReservasi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbCari = new javax.swing.JComboBox<>();
        txtCari = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuDashboard = new javax.swing.JMenu();
        menuKamar = new javax.swing.JMenu();
        menuHistory = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        jLabel1.setText("jLabel1");

        jMenuItem1.setText("jMenuItem1");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel2.setText("List Kamar Hotel");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nomor Kamar", "Tipe Kamar", "Deskripsi", "Harga", "Status"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnReservasi.setText("Reservasi");
        btnReservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasiActionPerformed(evt);
            }
        });

        jLabel3.setText("Cari Kamar");

        cmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nomor", "Tipe Kamar", "Status" }));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReservasi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCari, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtCari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReservasi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasiActionPerformed
        // TODO add your handling code here:
        if (selectedID != -1) {
            try (Connection conn = ConnectionDatabase.getConnection()) {
                if (conn != null) {

                    String queryInsert = "INSERT INTO reservasi (id_user, id_kamar, status_reservasi) VALUES (?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(queryInsert)) {
                        ps.setInt(1, Session.getId());
                        ps.setInt(2, selectedID);
                        ps.setString(3, "booked");

                        ps.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    String updateStatusKamar = "UPDATE kamar SET status = 'booked' WHERE id_kamar=?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(updateStatusKamar)) {
                        psUpdate.setInt(1, selectedID);

                        psUpdate.executeUpdate();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(this, "Sukses Reservasi!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    showData();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih kamar terlebih dahulu", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReservasiActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
        // TODO add your handling code here:
        searchKamar();
    }//GEN-LAST:event_txtCariKeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String status = jTable1.getValueAt(selectedRow, 6).toString();
            System.out.println(status);
            if (!status.equalsIgnoreCase("booked")) {
                selectedID = Integer.valueOf(jTable1.getValueAt(selectedRow, 0).toString());
                System.out.println("ID yang dipilih: " + selectedID);
            } else {
                JOptionPane.showMessageDialog(this, "Kamar telah dibooking!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
    class AlternatingRowColorRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String status = table.getValueAt(row, 6).toString();
            if (!isSelected) {
                if (status.equalsIgnoreCase("booked")) {
//                    System.out.println(status);
                    component.setBackground(Color.RED);
                } else {
                    component.setBackground(Color.WHITE);

                }
            }
            return component;
        }
    }

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
            java.util.logging.Logger.getLogger(ListKamarCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListKamarCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListKamarCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListKamarCust.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListKamarCust().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReservasi;
    private javax.swing.JComboBox<String> cmbCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu menuDashboard;
    private javax.swing.JMenu menuHistory;
    private javax.swing.JMenu menuKamar;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
