/*
 */
package swing.components.settings.manual;

/**
 *
 * @author adam
 */
public class JPanelManualGame extends javax.swing.JPanel {

    /**
     * Creates new form JPanelManualGame
     */
    public JPanelManualGame() {
        initComponents();
        setTitle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitle1 = new swing.components.settings.JPanelTitle();
        jPanelManualSub2 = new swing.components.settings.manual.JPanelManualSub();

        setBackground(new java.awt.Color(255, 255, 204));
        setLayout(null);
        add(jPanelTitle1);
        jPanelTitle1.setBounds(0, 0, 600, 50);
        add(jPanelManualSub2);
        jPanelManualSub2.setBounds(0, 50, 600, 400);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.components.settings.manual.JPanelManualSub jPanelManualSub2;
    private swing.components.settings.JPanelTitle jPanelTitle1;
    // End of variables declaration//GEN-END:variables

    private void setTitle() {
        this.jPanelTitle1.setTitle("Manual Game Settings");
    }
}