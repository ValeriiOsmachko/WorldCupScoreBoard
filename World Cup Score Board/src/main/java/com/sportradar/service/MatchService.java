package com.sportradar.service;

import com.sportradar.domain.Match;

import java.util.List;
import java.util.UUID;

public interface MatchService {


    UUID startMatch(String homeTeam, String awayTeam);

    Match endMatch(UUID matchId);

    void homeTeamScored(UUID matchId);

    void awayTeamScored(UUID matchId);

    List<Match> getSummaryBoard();

    void printBoard();


}
