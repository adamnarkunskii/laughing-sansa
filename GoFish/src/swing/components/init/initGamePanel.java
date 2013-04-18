/*
 */
package swing.components.init;

/**
 *
 * @author adamnark
 */
public class initGamePanel extends javax.swing.JPanel {

    /**
     * Creates new form initGamePanel
     */
    public initGamePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(400, 800));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("NEW GAME");
        jPanel3.add(jLabel1);

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 80));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(null);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jButton1.setText("Manual");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(400, 310, 370, 100);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jButton2.setText("XML");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(20, 310, 360, 100);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
