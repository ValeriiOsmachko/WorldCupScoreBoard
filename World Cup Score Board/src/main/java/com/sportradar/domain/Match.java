package com.sportradar.domain;

public final class Match {

    private String homeTeam;
    private String awayTeam;
    private Score currentScore;
    private Long creationTimeStamp;
    private record Score(int homeScore, int awayScore){};


    public Match(String homeTeam, String awayTeam) {
        if (!homeTeam.matches("^[a-zA-Z\\s]{4,20}$")) {
            throw new IllegalArgumentException(String.format("Home team name:%s doesn't match expected pattern", homeTeam));
        }
        if (!awayTeam.matches("^[a-zA-Z\\s]{4,20}$")) {
            throw new IllegalArgumentException(String.format("Away team name:%s doesn't match expected pattern", awayTeam));
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.currentScore = new Score(0, 0);
        this.creationTimeStamp = System.currentTimeMillis();
    }


    public Match homeTeamScored() {
        currentScore = new Score(currentScore.homeScore + 1, currentScore.awayScore);
        return this;
    }

    public Match awayTeamScored() {
        currentScore = new Score(currentScore.homeScore, currentScore.awayScore + 1);
        return this;
    }

    public Integer getTotalScore() {
        return currentScore.homeScore + currentScore.awayScore;
    }

    public Long getStartTime() {
        return creationTimeStamp;
    }


    @Override
    public String toString() {
        return homeTeam  + " - " + awayTeam + ": " + currentScore.homeScore + " – " + currentScore.awayScore;
    }
}
