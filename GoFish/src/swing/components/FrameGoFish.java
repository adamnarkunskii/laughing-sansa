/*
 */
package swing.components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import swing.utils.SwingUtils;

/**
 *
 * @author adamnark
 */
public class FrameGoFish extends JFrame {

    JPanelGoFish jPanelGoFish;

    /**
     * Creates new form GameFrame
     */
    public FrameGoFish() {
        initComponents();
        setLocationRelativeTo(null);

        Container contentPane = this.getContentPane();
        jPanelGoFish = new JPanelGoFish();
        contentPane.add(jPanelGoFish, BorderLayout.CENTER);

        initListeners();

        initMenuBar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Go Fish - Swing");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(600, 450));
        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void initListeners() {
        this.jPanelGoFish.addPropertyChangeListener(JPanelGoFish.EVENT_EXIT, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                exit();
            }
        });
    }

    private void exit() {
        this.dispose();
    }

    private void initMenuBar() {
        JMenuBar menuBar = makeMenuBar();
        this.setJMenuBar(menuBar);
    }

    private JMenuBar makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu1 = new JMenu("Game");
        JMenuItem menuItemNew = new JMenuItem("New Game");
        menuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        menu1.add(menuItemNew);
        menu1.addSeparator();
        menu1.add(menuItemExit);

        JMenu menu2 = new JMenu("Help");
        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupAbout();
            }
        });
        menu2.add(menuItemAbout);



        menuBar.add(menu1);
        menuBar.add(menu2);

        return menuBar;
    }

    private void newGame() {
    }

    private void popupAbout() {
        ImageIcon ia = SwingUtils.getImageIcon("about_icon.png");
        JOptionPane.showMessageDialog(this, "Adam Narkunski's GoFish!", "About", JOptionPane.INFORMATION_MESSAGE, ia);
    }
}
