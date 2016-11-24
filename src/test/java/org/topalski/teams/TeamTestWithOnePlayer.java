package org.topalski.teams;

import org.goochjs.glicko2.Rating;
import org.goochjs.glicko2.RatingCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Gueorgui Tzvetoslavov Topalski on 11/24/16.
 * email: gueorgui.tzvetoslavov@gmail.com
 */
//This is the same test as the original one. The team are made with only one person per team
//The idea is to demonstrate that the original algorithm was not modified
public class TeamTestWithOnePlayer
{
    private RatingCalculator ratingSystem = new RatingCalculator(0.06, 0.5);
    private Rating player1 = new Rating("player1", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility()); // the main player of Glickman's example
    private Rating player2 = new Rating("player2", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());
    private Rating player3 = new Rating("player3", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());
    private Rating player4 = new Rating("player4", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility());
    private Rating player5 = new Rating("player5", ratingSystem.getDefaultRating(), ratingSystem.getDefaultRatingDeviation(), ratingSystem.getDefaultVolatility()); // this player won't compete during the test

    private Team A = new Team(player1);
    private Team B = new Team(player2);
    private Team C = new Team(player3);
    private Team D = new Team(player4);
    private Team E = new Team(player5);

    @Test
    public void test() {
        initialise();
        printResults("Before");
        // test that the scaling works
        assertEquals( 0, player1.getGlicko2Rating(), 0.00001 );
        assertEquals( 1.1513, player1.getGlicko2RatingDeviation(), 0.00001 );

        TeamIndividualUpdate games = new TeamIndividualUpdate();
        games.addResult(A,B);
        games.addResult(C,A);
        games.addResult(D,A);
        games.addParticpants(E);
        games.updateRating(ratingSystem);

        printResults("After");

        assertEquals( 1464.06, player1.getRating(), 0.01 );
        assertEquals( 151.52, player1.getRatingDeviation(), 0.01 );
        assertEquals( 0.05999, player1.getVolatility(), 0.01 );

        // test that opponent 4 has had appropriate calculations applied
        assertEquals( ratingSystem.getDefaultRating(), player5.getRating(), 0 );  // rating should be unaffected
        assertTrue( ratingSystem.getDefaultRatingDeviation() < player5.getRatingDeviation() );  // rating deviation should have grown
        assertEquals( ratingSystem.getDefaultVolatility(), player5.getVolatility(), 0 );  // volatility should be unaffected
    }

    private void initialise() {
        player1.setRating(1500);
        player2.setRating(1400);
        player3.setRating(1550);
        player4.setRating(1700);

        player1.setRatingDeviation(200);
        player2.setRatingDeviation(30);
        player3.setRatingDeviation(100);
        player4.setRatingDeviation(300);
    }

    private void printResults(String text) {
        System.out.println(text + "...");
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
        System.out.println(player4);
        System.out.println(player5);
    }


}
