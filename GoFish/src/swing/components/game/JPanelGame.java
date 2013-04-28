/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.components.game;

import engine.Engine;
import engine.players.exceptions.InvalidFourRuntimeException;
import engine.players.Player;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import swing.components.game.log.JPanelLog;
import swing.components.game.play.PlayAreaFactory;
import swing.components.game.play.PlayEvents;
import swing.components.game.play.playarea.IPlayAreaPanel;
import swing.components.game.play.playarea.JPanelHand;
import swing.utils.SwingUtils;

/**
 *
 * @author Natalie
 */
public class JPanelGame extends javax.swing.JPanel {

    public static final String EVENT_GAME_OVER = "JPanelGame EVENT_GAME_OVER";
    private Engine engine;
    private swing.components.game.graveyard.JPanelGraveyard jPanelGraveyard;
    private swing.components.game.play.JPanelPlayAreaCards jPanelPlayAreaCards;
    private swing.components.game.playerlist.JPanelPlayerList jPanelPlayerList;
    private swing.components.game.log.JPanelLog jPanelLog;
    private boolean isGameOver = false;

    /**
     * Creates new form JPanelGame
     */
    public JPanelGame() {
        initComponents();
        this.engine = null;
    }

    public void initGame(Engine engine) {
        this.engine = engine;
        initHandClass();
        initGraveyard();
        initPlayAreaCards();
        initPlayersList();
        initLog();
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        this.jPanelPlayerList = new swing.components.game.playerlist.JPanelPlayerList();
        this.jPanelPlayAreaCards = new swing.components.game.play.JPanelPlayAreaCards();
        this.jPanelGraveyard = new swing.components.game.graveyard.JPanelGraveyard();
        this.jPanelLog = new JPanelLog();

        setLayout(new java.awt.BorderLayout());
        add(jPanelLog, java.awt.BorderLayout.NORTH);
        add(jPanelPlayAreaCards, java.awt.BorderLayout.CENTER);
        add(jPanelPlayerList, java.awt.BorderLayout.WEST);
        add(jPanelGraveyard, java.awt.BorderLayout.EAST);

    }

    private void initGraveyard() {
        this.jPanelGraveyard.setEngine(engine);
    }

    private void initPlayAreaCards() {
        for (Player player : this.engine.getPlayers()) {
            String playerName = player.getName();
            IPlayAreaPanel jPanelPlayArea = PlayAreaFactory.makeJPanelPlayArea(player);
            initCardListeners((JPanel) jPanelPlayArea);
            this.jPanelPlayAreaCards.addCard(jPanelPlayArea, playerName);
        }
    }

    private void initPlayersList() {
        this.jPanelPlayerList.setEngine(engine);
    }

    private void initHandClass() {
        JPanelHand.setAvailableSeries(engine.getAvailableSeries());
    }

    private void initCardListeners(JPanel jPanelPlayArea) {
        jPanelPlayArea.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                getMessagesFromEngine();
            }
        });
        jPanelPlayArea.addPropertyChangeListener(PlayEvents.EVENT_PLAY_COMPUTER_TURN, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                playComputerTurn();
            }
        });
        jPanelPlayArea.addPropertyChangeListener(PlayEvents.EVENT_REQUEST, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        jPanelPlayArea.addPropertyChangeListener(PlayEvents.EVENT_THROW, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                handleThrowFourCards();
            }
        });
        jPanelPlayArea.addPropertyChangeListener(PlayEvents.EVENT_SKIP, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                advanceTurn();
            }
        });
    }

    private void advanceTurn() {
        this.engine.advanceTurn();
        this.jPanelPlayAreaCards.showCard(this.engine.getCurrentPlayer().getName());
        this.jPanelPlayerList.refreshCurrentPlayer();
    }

    private void getMessagesFromEngine() {
        while (!this.engine.getEventQueue().isEmpty()) {
            Engine.Event poppedEvent = this.engine.getEventQueue().pop();
            switch (poppedEvent) {
                case FAILED_REQUEST:
                    handleFailedRequset();
                    break;
                case FOUR_CARDS_NOT_THROWN:
                    handleFourCardsNotThrown();
                    break;
                case FOUR_CARDS_THROWN:
                    handleFourCardsThrown();
                    break;
                case PLAYER_OUT_OF_CARDS:
                    handlePlayerOutOfCards();
                    break;
                case SUCCESSFUL_REQUEST:
                    handleSuccessfulRequest();
                    break;
                default:
                    throw new AssertionError();
            }

        }
        checkGameOver();
    }

    private void playComputerTurn() {
        this.engine.currentPlayerMakeRequest();
        getMessagesFromEngine();
        this.engine.currentPlayerThrowFour();
        getMessagesFromEngine();
        this.advanceTurn();
    }

    private void initLog() {
        this.jPanelLog.setText("Game started!");
    }

    private void appendToLog(String string) {
        this.jPanelLog.appendToLog(string);
    }

    private void handleFailedRequset() {
        appendToLog(this.engine.getCurrentPlayer().getName() + " has made a bad request!");
    }

    private void handleFourCardsNotThrown() {
        appendToLog(this.engine.getCurrentPlayer().getName() + " hasn't thrown any cards!");
    }

    private void handleFourCardsThrown() {
        appendToLog(this.engine.getCurrentPlayer().getName() + " has thrown four cards!");
        this.jPanelPlayerList.refresh();
        this.jPanelPlayAreaCards.refresh();
        if (this.engine.getGameSettings().isForceShowOfSeries()) {
            this.jPanelGraveyard.refresh();
        }
        this.jPanelPlayAreaCards.disableThrowingForCurrentCard();
    }

    private void handlePlayerOutOfCards() {
        appendToLog(this.engine.getCurrentPlayer().getName() + " has run out of cards!");
    }

    private void handleSuccessfulRequest() {
        appendToLog(this.engine.getCurrentPlayer().getName() + " has made a successful request!");
    }

    private void checkGameOver() {
        if (this.engine.isGameOver() && !this.isGameOver) {
            String winrar = engine.getWinner().getName();
            int score = engine.getWinner().getScore();
            ImageIcon ia = SwingUtils.getImageIcon("gameover_icon.png");
            JOptionPane.showMessageDialog(this, winrar + " wins with " + score + " points!", "Game over", JOptionPane.DEFAULT_OPTION, ia);
            this.firePropertyChange(JPanelGame.EVENT_GAME_OVER, true, false);
            this.isGameOver = true;
        }
    }

    private void handleThrowFourCards() {
        try {
            this.engine.currentPlayerThrowFour();
            getMessagesFromEngine();
        } catch (InvalidFourRuntimeException ex) {
            appendToLog("ERROR: Throw FOUR(4) cards of the same series!");
        }
    }
}
