/*
 */
package engine.Factory;

import engine.Engine;
import engine.GameSettings;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author adam
 */
public class EngineFactory {

    public static Engine generateEngine(List<PlayerItem> players, GameSettings gameSettings) {

        Engine engine = new Engine();
        engine.setGameSettings(gameSettings);
        addPlayers(engine, players);

        return engine;
    }

    private static void addPlayers(Engine engine, List<PlayerItem> players) {
        for (PlayerItem playerItem : players) {
            Player player = makePlayer(playerItem);
            generateHandForPlayer(player);
            engine.addPlayer(player);
        }
    }

    private static Player makePlayer(PlayerItem playerItem) {
        Player player;
        if (!playerItem.isHuman()) {
            player = Player.createAIPlayer();
        } else {
            player = new Player();// new Player(new SwingFourPicker(), new SwingRequestMaker());
        }
        player.setName(playerItem.getPlayerName());

        return player;
    }

    private static void generateHandForPlayer(Player player) {
        RandomStringGenerator rsg = new RandomStringGenerator();
        generateSeriesForPlayer(player, rsg, "Ao", "Mo", "Ni");
        generateSeriesForPlayer(player, rsg, "Ki", "Mo", "Ni");
        generateSeriesForPlayer(player, rsg, "Va", "Mo", "Ni");
        Collections.shuffle(player.getHand().getCards());
    }

    private static void generateSeriesForPlayer(
            Player player, 
            RandomStringGenerator rsg, 
            String seriesName, 
            String seriesName2, 
            String seriesName3) {
        for (int i = 0; i < 4; i++) {
            Card generatedCard = new Card();
            generatedCard.addSeries(new Series(seriesName));
            if (i % 2 == 0) {
                generatedCard.addSeries(new Series(seriesName2));
            } else {
                generatedCard.addSeries(new Series(seriesName3));
            }
            generatedCard.setName(seriesName + " " + rsg.makeRandomString());
            player.getHand().addCardToHand(generatedCard);
        }
    }

    private static class RandomStringGenerator {

        private Random random = new Random();

        public String makeRandomString() {
            return new BigInteger(16, random).toString(16);
        }
    }
}