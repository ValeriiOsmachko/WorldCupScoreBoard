package com.sportradar.service.impl;

import com.sportradar.domain.Match;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MatchServiceImplTest {

    private final MatchServiceImpl matchService = new MatchServiceImpl();

    @Test
    public void shouldStartNewMatch() {
        UUID uuid = matchService.startMatch("Real Madrid", "Barcelona");
        assertNotNull(uuid);
    }

    @Test
    public void shouldFailWithStartWithInvalidTeamNames() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                matchService.startMatch("Ab", "Barcelona"));
        assertEquals("Home team name:Ab doesn't match expected pattern", exception.getMessage());
    }

    @Test
    public void shouldEndMath() {
        UUID uuid = matchService.startMatch("Barcelona", "Real Madrid");
        Match endedMatch = matchService.endMatch(uuid);
        assertNotNull(endedMatch);
        assertEquals("Barcelona - Real Madrid: 0 – 0", endedMatch.toString());
    }

    @Test
    public void shouldChangeScoreForTheMatch() {
        UUID uuid = matchService.startMatch("Bayern", "Dortmund");
        matchService.homeTeamScored(uuid);
        matchService.homeTeamScored(uuid);
        matchService.homeTeamScored(uuid);
        matchService.awayTeamScored(uuid);
        List<Match> summaryBoard = matchService.getSummaryBoard();
        matchService.printBoard();
        assertEquals("Bayern - Dortmund: 3 – 1", summaryBoard.get(0).toString());
    }

    @Test
    public void shouldFailToChangeScoreForUnExistingMatch() {
        matchService.startMatch("Bayern", "Dortmund");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> matchService.homeTeamScored(UUID.fromString("0c4cbe73-bf25-4df3-b38c-24f48c061481")));
        assertEquals("Match with such id:0c4cbe73-bf25-4df3-b38c-24f48c061481 not found", exception.getMessage());

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
        assertEquals(3, summaryStatistic.size());
        assertEquals("Arsenal - Liverpool: 2 – 2", summaryStatistic.get(0).toString());
        // 2 Matches have same total score, but [Chelsea - Manchester United] started earlier
        assertEquals("Chelsea - Manchester United: 2 – 1", summaryStatistic.get(1).toString());
        assertEquals("Manchester City - Tottenham: 1 – 2", summaryStatistic.get(2).toString());
    }

}