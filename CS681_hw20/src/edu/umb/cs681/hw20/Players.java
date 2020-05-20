package edu.umb.cs681.hw20;

public class Players {
    private final String Name;
    private final int PointsScored;

    public Players(String name, int pointsScored) {
        Name = name;
        PointsScored = pointsScored;
    }

    public String getName() {
        return Name;
    }

    public int getPointsScored() {
        return PointsScored;
    }

    @Override
    public java.lang.String toString() {
        return "Players{" +
                "Name=" + Name +
                ", PointsScored=" + PointsScored +
                '}';
    }
}
