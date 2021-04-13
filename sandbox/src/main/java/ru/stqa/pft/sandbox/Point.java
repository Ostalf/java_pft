package ru.stqa.pft.sandbox;

public class Point {
    double xAxis;
    double yAxis;

    public Point(double xAxis, double yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public double distance(Point p2){
        return Math.sqrt(Math.pow((p2.xAxis-this.xAxis),2)+Math.pow((p2.yAxis-this.yAxis),2));
    }
}
