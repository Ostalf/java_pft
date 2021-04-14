package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceZero() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2),0.0);
    }
    @Test
    public void testDistanceNegativeBigNumbers() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(-999999999,0);
        Assert.assertEquals(p1.distance(p2),999999999.0);
    }
    @Test
    public void testDistancePositiveBigNumbers() {
        Point p1 = new Point(0,999999999);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2),999999999.0);
    }
}
