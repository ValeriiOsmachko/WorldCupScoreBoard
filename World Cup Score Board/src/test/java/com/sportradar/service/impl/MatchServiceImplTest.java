package com.sportradar.service.impl;

import com.sportradar.domain.Match;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;


public class MatchServiceImplTest {

    private final MatchServiceImpl matchService = new MatchServiceImpl();
    @Test
    public void shouldStartNewMatch() {
        UUID uuid = matchService.startMatch("Real Madrid", "Barcelona");
        assertNotNull(uuid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithStartWithInvalidTeamNames() {
        matchService.startMatch("Ab", "Barcelona");
    }

    @Test
    public void shouldEndMath() {
        Match endedMatch = matchService.endMatch(UUID.randomUUID());
        assertNotNull(endedMatch);
    }

    @Test
    public void shouldCreateThreeMatchesWithGoals() {
        UUID uuid1 = matchService.startMatch("Chelsea", "Manchester United");
        UUID uuid2 = matchService.startMatch("Arsenal", "Liverpool");
        UUID uuid3 = matchService.startMatch("Manchester City", "Tottenham");

        matchService.homeTeamScored(uuid1);
        matchService.homeTeamScored(uuid1);
        matchService.awayTeamScored(uuid1);

        matchService.homeTeamScored(uuid2);
        matchService.homeTeamScored(uuid2);
        matchService.awayTeamScored(uuid2);
        matchService.awayTeamScored(uuid2);


        matchService.awayTeamScored(uuid3);
        matchService.awayTeamScored(uuid3);
        matchService.homeTeamScored(uuid3);

        List<Match> summaryStatistic = matchService.getSummaryBoard();
        assertNotNull(summaryStatistic);

    }

}