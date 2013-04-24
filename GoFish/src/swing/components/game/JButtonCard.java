/*
 */
package swing.components.game;

import engine.cards.Card;
import engine.cards.Series;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author adam
 */
public class JButtonCard extends JButton {

    public static final String EVENT_CLICKED = "JPanelCard was clicked";
    private JPanelColors jPanelColors;
    private JLabel jLableCardName;
    private static List<Series> availableSeries;
    private static List<Color> colorList;
    private static boolean isDictionaryInitiated = false;
    private boolean isHighlighted = false;

    public JButtonCard(Card card) {
        if (!isDictionaryInitiated) {
            throw new RuntimeException("JPanelCard class is not initiated! call setAvaiableSeries() before instantiating.");
        }

        initComponents(card);
        initListeners();
    }

    private void initComponents(Card card) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(80, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.jPanelColors = new JPanelColors(generateColorsFromCard(card));
        this.jLableCardName = new JLabel();
        jLableCardName.setHorizontalAlignment(JLabel.CENTER);
        jLableCardName.setText(card.getName());

        this.add(jPanelColors);
        this.add(jLableCardName);
    }

    private void initListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                toggle();
            }
        });
    }
    
    public boolean isHighlighted(){
        return this.isHighlighted;
    }
    
    private void toggle() {
        isHighlighted = !isHighlighted;
        if (isHighlighted) {
            highlight();
        } else {
            lowlight();
        }
    }

    public void highlight() {
        this.jLableCardName.setForeground(Color.blue);
    }

    public void lowlight() {
        this.jLableCardName.setForeground(this.getForeground());
    }

    private List<Color> generateColorsFromCard(Card card) {
        LinkedList<Color> lst = new LinkedList<>();
        for (Series series : card.getSeries()) {
            int i = availableSeries.indexOf(series);
            Color color = colorList.get(i);
            lst.add(color);
        }

        return lst;
    }

    public static void setAvailableSeries(List<Series> availableSeries) {
        JButtonCard.availableSeries = availableSeries;
        initColorList();
    }

    private static void initColorList() {
        colorList = new LinkedList<>();
        generateColors();
        JButtonCard.isDictionaryInitiated = true;
    }

    private static void generateColors() {
        Random rnd = new Random();
        for (Series s : availableSeries) {
            colorList.add(randomColor(rnd));
        }
    }

    private static Color randomColor(Random rnd) {
        int r = rnd.nextInt(255);
        int g = rnd.nextInt(255);
        int b = rnd.nextInt(255);

        return new Color(r, g, b);
    }
}