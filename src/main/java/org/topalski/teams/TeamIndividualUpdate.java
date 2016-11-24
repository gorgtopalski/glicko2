package org.topalski.teams;

import org.goochjs.glicko2.RatingCalculator;
import org.goochjs.glicko2.RatingPeriodResults;

/**
 * Created by Gueorgui Tzvetoslavov Topalski on 11/24/16.
 * email: gueorgui.tzvetoslavov@gmail.com
 */
public class TeamIndividualUpdate
{
    private RatingPeriodResults games = new RatingPeriodResults();

    public void addResult(Team winner, Team loser)
    {
        winner.getTeam().forEach( win -> {
            loser.getTeam().forEach( lose -> games.addResult(win,lose));
        });
    }

    public void addDraw(Team teamA, Team teamB)
    {
        teamA.getTeam().forEach( a -> {
            teamB.getTeam().forEach( b -> games.addDraw(a,b));
        });
    }

    public void addParticpants(Team team)
    {
        team.getTeam().forEach( player -> games.addParticipants(player));
    }


    public void updateRating(RatingCalculator rc)
    {
      rc.updateRatings(games);
    }

}
