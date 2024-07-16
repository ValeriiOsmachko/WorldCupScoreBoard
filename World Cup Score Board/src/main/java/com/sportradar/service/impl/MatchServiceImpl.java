package com.sportradar.service.impl;

import com.sportradar.domain.Match;
import com.sportradar.service.MatchService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MatchServiceImpl implements MatchService {

    private ConcurrentHashMap<UUID, Match> storage;

    public MatchServiceImpl() {
        this.storage = new ConcurrentHashMap<>();
    }

    /**
     * Creates new match and returns match uuid. Initial score is 0 - 0
     * @param homeTeam home team name which has to match expected pattern
     * @param awayTeam away team name which has to match expected pattern
     * @return unique identifier
     */
    @Override
    public UUID startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        UUID matchUuid = UUID.randomUUID();
        storage.put(matchUuid, match);
        return matchUuid;
    }

    /**
     * Removes match from the storage by provided id.
     * @param matchId match unique identifier
     * @return removed match
     */
    @Override
    public Match endMatch(UUID matchId) {
        return storage.remove(matchId);
    }

    /**
     * Performs score for a home team, increment home team score +1.
     * @param matchId
     */
    @Override
    public void homeTeamScored(UUID matchId) {
        Match match = storage.get(matchId);
        if (match == null) {
            throw new IllegalArgumentException(String.format("Match with such id:%s not found", matchId));
        }
        Match updatedMatch = match.homeTeamScored();
        storage.put(matchId, updatedMatch);
    }

    /**
     * Performs score for away team, increment away team score +1.
     * @param matchId
     */
    @Override
    public void awayTeamScored(UUID matchId) {
        Match match = storage.get(matchId);
        if (match == null) {
            throw new IllegalArgumentException("Event with such id not found");
        }
        Match updatedMatch = match.awayTeamScored();
        storage.put(matchId, updatedMatch);
    }

    /**
     * Returns list of matches sorted by total score,
     * if 2 matches have same total score then earlier would be
     * that one which started earlier
     * @return list of active matches
     */
    @Override
    public List<Match> getSummaryBoard() {
        return storage.entrySet().stream().sorted((match1, match2) -> {
                    Match value1 = match1.getValue();
                    Match value2 = match2.getValue();
                    int i = value2.getTotalScore().compareTo(value1.getTotalScore());
                    return i == 0 ? value2.getStartTime().compareTo(value1.getStartTime()) : i;
                })
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    /**
     * Prints all matches on the boars
     */
    @Override
    public void printBoard() {
        getSummaryBoard()
                .forEach(e -> System.out.println(e.toString()));
    }
}
