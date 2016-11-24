package org.topalski.teams;

import org.goochjs.glicko2.Rating;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gueorgui Tzvetoslavov Topalski on 11/24/16.
 * email: gueorgui.tzvetoslavov@gmail.com
 */
public class Team
{
    private Set<Rating> team;

    private Team()
    {
        team = new HashSet<>();
    }

    public Team(Set<Rating> team)
    {
        this.team = team;
    }

    public Team(Rating player)
    {
        this();
        this.team.add(player);
    }

    public void addToTeam(Rating player)
    {
        team.add(player);
    }

    public Set<Rating> getTeam()
    {
        return team;
    }
}
