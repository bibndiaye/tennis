package mouhamadou.ndiaye.tennis;

import lombok.extern.slf4j.Slf4j;
import mouhamadou.ndiaye.tennis.kata.Game;
import mouhamadou.ndiaye.tennis.kata.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
Implement a simple tennis game
**Rules:**
* Scores from zero to three points are described as "0", "15", "30", and "40" respectively.
* If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is "advantage" for the player in the lead.
* If at least three points have been scored by each player, and the scores are equal, the score is "deuce".
* A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
*/
@Slf4j
@SpringBootTest
public class GameTest {

    Player moussa;
    Player marie;
    Game game;

    @Before
    public void beforeGameTest() {
        moussa = new Player("Moussa");
        marie = new Player("Marie");
        game = new Game(moussa, marie);
    }

    /**
     * Player1 Test
     */
    @Test
    public void player1Test() {
        Game game1 = new Game();
        Player player1 = new Player();
        Assertions.assertNotNull(game1);
        Assertions.assertNotNull(player1);
        game1.setPlayer1(player1);
        Assertions.assertNull(game1.getPlayer1().getName());
        player1.setName("Moussa");
        Assertions.assertEquals("Moussa", game1.getPlayer1().getName());
    }
    /**
     * Player1 Test
     */
    @Test
    public void player2Test() {
        Game game2 = new Game();
        Player player2 = new Player();
        Assertions.assertNotNull(game2);
        Assertions.assertNotNull(player2);
        game2.setPlayer2(player2);
        Assertions.assertNull(game2.getPlayer2().getName());
        player2.setName("Marie");
        Assertions.assertEquals("Marie", game2.getPlayer2().getName());
    }


    /**
     * 0 Should Be Description For Score 0
     */
    @Test
    public void zeroShouldBeDescriptionForScore0() {
        Game game = new Game(moussa, marie);
        assertThat(game, hasProperty("score", is("0, 0")));
    }

    /**
     * 15 ShouldBe Description For Score 1
     */
    @Test
    public void fifteenShouldBeDescriptionForScore1() {
        marie.winScore();
        assertThat(game, hasProperty("score", is("0, 15")));
    }

    /**
     * 30 ShouldBe Description For Score 2
     */
    @Test
    public void thirtyShouldBeDescriptionForScore2() {
        moussa.winScore();
        moussa.winScore();
        marie.winScore();
        assertThat(game, hasProperty("score", is("30, 15")));
    }

    /**
     *  40 Should Be Description For Score 3
     */
    @Test
    public void fortyShouldBeDescriptionForScore3() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            moussa.winScore();
        });
        assertThat(game, hasProperty("score", is("40, 0")));
    }


    /**
     * advantage Should Be Description When Least Three Points Have Been Scored
     * By Each Side And Player Has One Point More Than His Opponent
     */
    @Test
    public void advantageTest() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            moussa.winScore();
        });

        log.info("Player 1 : {}", moussa.getName());
        log.info("Player 2 : {}", marie.getName());
        log.info("Score {} = {}", moussa.getName(), moussa.getScore());

        IntStream.rangeClosed(1, 4).forEach((Integer) -> {
            marie.winScore();
        });
        log.info("Score {} = {}", marie.getName(), marie.getScore());
        log.info("Score    :  "+moussa.generateResultOfAsetWin("win")+" "+moussa.generateResultOfAsetWin("win")
                +" "+moussa.generateResultOfAsetWin("win")+" "+marie.generateResultOfAsetWin("win")
                +" "+marie.generateResultOfAsetWin("win")+" "+marie.generateResultOfAsetWin("win")+" "+
                     marie.generateResultOfAsetWin("win"));
        log.info("advantage Marie");
        assertThat(game, hasProperty("score", is("advantage Marie")));
    }


    /**
     * deuce Should BeDescription When At Least Three Points Have Been Scored
     * By Each Player And The Scores Are Equal
     */
    @Test
    public void deuceTest() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            moussa.winScore();
        });
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            marie.winScore();
        });
        log.info("Player 1 : {}", moussa.getName());
        log.info("Player 2 : {}", marie.getName());
        log.info("---------------------------------------------------------------");
        log.info("Score    :  "+moussa.generateResultOfAsetWin("win")+" "+moussa.generateResultOfAsetWin("win")
                +" "+moussa.generateResultOfAsetWin("win")+" "+moussa.generateResultOfAsetWin("lost")
                +" "+moussa.generateResultOfAsetWin("lost")+" "+moussa.generateResultOfAsetWin("lost"));
        log.info("Current game status : deuce");

        log.info("---------------------------------------------------------------");
        assertThat(game, hasProperty("score", is("deuce")));
        moussa.winScore();
        assertThat(game, hasProperty("score", is(not("deuce"))));
        marie.winScore();
        assertThat(game, hasProperty("score", is("deuce")));
    }

    /**
     * game Should Be Won By The First Player To Have Won At Least Four Points
     * In Total And With At Least Two Points More Than The Opponent
     */

    @Test
    public void wonTest() {
        int valueP1=moussa.ssetGame(), valueP2=marie.ssetGame();
        IntStream.rangeClosed(1, 4).forEach((Integer) -> {
            moussa.winScore();
        });
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            marie.winScore();
        });
        log.info("---------------------------------------------------------------");
        log.info("Palyer 1 : {} ", moussa.getName());
        log.info("Palyer 2 : {} ", marie.getName());
        log.info("Score    :  "+moussa.generateResultOfAsetWin("win")+" "+moussa.generateResultOfAsetWin("win")
                              +" "+moussa.generateResultOfAsetWin("win")+" "+moussa.generateResultOfAsetWin("win")
                              +" "+moussa.generateResultOfAsetWin("lost")+" "+moussa.generateResultOfAsetWin("lost"));
        log.info("---------------------------------------------------------------");

        assertThat(game, hasProperty("score", is(not("Moussa won"))));
        assertThat(game, hasProperty("score", is(not("Marie won"))));
        moussa.winScore();
        assertThat(game, hasProperty("score", is("Moussa won")));
    }
}