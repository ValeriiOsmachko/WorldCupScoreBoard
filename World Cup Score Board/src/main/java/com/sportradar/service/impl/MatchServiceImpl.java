package com.sportradar.service.impl;

import com.sportradar.domain.Match;
import com.sportradar.service.MatchService;

import java.util.List;
import java.util.UUID;

public class MatchServiceImpl implements MatchService {


    @Override
    public UUID startMatch(String homeTeam, String awayTeam) {
        return null;
    }

    @Override
    public Match endMatch(UUID matchId) {
        return null;
    }

    @Override
    public void homeTeamScored(UUID matchId) {

    }

    @Override
    public void awayTeamScored(UUID matchId) {

    }

    @Override
    public List<Match> getSummaryBoard() {
        return null;
    }

    @Override
    public void printBoard() {

    }
}
