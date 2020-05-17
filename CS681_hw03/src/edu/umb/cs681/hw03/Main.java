package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String []args)
    {
        List<Players> team1Players = new ArrayList<Players>();
        team1Players.add(new Players("a1",1));
        team1Players.add(new Players("a2",1));
        team1Players.add(new Players("a3",0));

        List<Players> team2Players = new ArrayList<Players>();
        team2Players.add(new Players("b1",2));
        team2Players.add(new Players("b2",1));
        team2Players.add(new Players("b3",0));

        List<Players> team3Players = new ArrayList<Players>();
        team3Players.add(new Players("c1",3));
        team3Players.add(new Players("c2",2));
        team3Players.add(new Players("c3",1));

        List<Players> team4Players = new ArrayList<Players>();
        team4Players.add(new Players("d1",0));
        team4Players.add(new Players("d2",1));
        team4Players.add(new Players("d3",1));

        ArrayList<SportsStats> statsList = new ArrayList<SportsStats>();
        statsList.add(new SportsStats("Alpha Team", 2, team1Players));
        statsList.add(new SportsStats("Beta Team", 3, team2Players));
        statsList.add(new SportsStats("Gamma Team",4, team3Players));
        statsList.add(new SportsStats("Theta Team", 2, team4Players));

        System.out.println("*****\n "+statsList.toString()+"\n*****");

        Integer maximumWon = statsList.stream()
                .map((SportsStats s)->s.getWinCount())
                .max(Comparator.comparing((Integer WinCount)->WinCount)).get();
        System.out.println("Team With Maximum Win: "+ maximumWon.toString());

        Integer leastWon = statsList.stream()
                .map((SportsStats s)->s.getWinCount())
                .min(Comparator.comparing((Integer WinCount)->WinCount)).get();
        System.out.println("Team with least win: "+ leastWon.toString());

        Integer starPlayer = statsList.stream()
                .flatMap((SportsStats s)->s.getPlayers().stream())
                .map((Players p)->p.getPointsScored())
                .max(Comparator.comparing((Integer WinCount)->WinCount)).get();
        System.out.println("Player with maximum score: "+ starPlayer.toString());



    }
}
