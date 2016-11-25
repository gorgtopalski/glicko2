package org.topalski.teams;

import org.goochjs.glicko2.Rating;
import org.goochjs.glicko2.RatingCalculator;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Gueorgui Tzvetoslavov Topalski on 11/25/16.
 * email: gueorgui.tzvetoslavov@gmail.com
 */
public class TeamTest
{
    private RatingCalculator ratingSystem = new RatingCalculator(0.06, 0.5);
    private Rating player1 = new Rating("player1", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());
    private Rating player2 = new Rating("player2", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());

    private Rating player3 = new Rating("player3", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());
    private Rating player4 = new Rating("player4", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());

    @Test
    public void test()
    {
        Team A = new Team(new Rating[]{player1, player2});
        Team B = new Team(new Rating[]{player3, player4});

        TeamIndividualUpdate games = new TeamIndividualUpdate();
        games.addResult(A,B);
        games.updateRating(ratingSystem);

        //Player 1 and 2 won the match so their rating is higher
        assertTrue( player1.getRating() > player3.getRating());
        assertTrue( player4.getRating() < player2.getRating());

        //Every player rating deviation is lower.. as they all played a match
        //And the system knows more about their ability
        assertTrue( player1.getRatingDeviation() < ratingSystem.getDefaultRatingDeviation());
        assertTrue( player2.getRatingDeviation() < ratingSystem.getDefaultRatingDeviation());
        assertTrue( player3.getRatingDeviation() < ratingSystem.getDefaultRatingDeviation());
        assertTrue( player4.getRatingDeviation() < ratingSystem.getDefaultRatingDeviation());
    }
}
