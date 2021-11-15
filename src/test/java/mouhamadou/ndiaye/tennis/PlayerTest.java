package mouhamadou.ndiaye.tennis;

import mouhamadou.ndiaye.tennis.kata.Player;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {

    @Test
    public void pointsCanBeAddedToEachPlayer() {
        Player moussa = new Player("Moussa");
        Player marie = new Player("Marie");
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            moussa.winScore();
        });
        IntStream.rangeClosed(1, 4).forEach((Integer) -> {
            marie.winScore();
        });
        assertThat(moussa, hasProperty("score", is(3)));
        assertThat(marie, hasProperty("score", is(4)));
    }
}