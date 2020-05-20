package edu.umb.cs681.hw20;

import java.util.List;

public class SportsStats {

    private String TeamName;
    private int WinCount;
    private List<Players> Players;

    public SportsStats(String teamName, int winCount, List<Players> players){
        this.TeamName = teamName;
        this.WinCount = winCount;
        this.Players = players;
    }

    public String getTeamName() {
        return TeamName;
    }

    public int getWinCount() { return WinCount; }

    public List<Players> getPlayers() {
        return Players;
    }

    @Override
    public String toString() {
        return "SportsStats{" +
                "TeamName='" + TeamName + '\'' +
                ", WinCount=" + WinCount +
                ", Players=" + Players.toString() +
                '}';
    }
}

enum MatchResult {
    WON,
    LOST,
    TIE,
    NORESULT
}


