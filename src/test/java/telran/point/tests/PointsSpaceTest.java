package test.java.telran.point.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.telran.point.dao.PointsSpace;
import main.java.telran.point.model.Point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.BiFunction;

class PointsSpaceTest {
    Point p0 = new Point(0, 0);
    Point p1 = new Point(1, 1);
    Point p2 = new Point(2, 2);
    Point p3 = new Point(3, 3);
    Point p4 = new Point(4, 4);
    Point p_2 = new Point(-2, -2);
    Point[] original = { p3, p2, p0, p4 };
    Point[] expected0 = { p0, p2, p3, p4 };
    Point[] expected4 = { p4, p3, p2, p0 };
    Point[] expected01 = { p0, p1, p2, p3, p4 };
    Point[] expected01_02 = { p0, p1, p_2, p2, p3, p4 };

    @Test
    void testConstructor() {
        PointsSpace ps = new PointsSpace(p0, original);
        assertArrayEquals(expected0, ps.getPoints());
        new PointsSpace(p4, original);
        assertArrayEquals(expected4, ps.getPoints());
    }

    @Test
    void testAddPoint() {
        PointsSpace ps = new PointsSpace(p0, original);
        ps.addPoint(p1);
        assertArrayEquals(expected01, ps.getPoints());
        ps.addPoint(p_2);
        assertArrayEquals(expected01_02, ps.getPoints());
    }

    @Test
    void testRandomArray() {
        Random random = new Random();
        final int SIZE = 50;

        Point[] newOriginal = new Point[SIZE];
        for (int i = 0; i < newOriginal.length; i++) {
            newOriginal[i] = new Point((double) random.nextInt(0, 10000) / 100,(double) random.nextInt(0, 10000) / 100);
        }
        PointsSpace ps = new PointsSpace(newOriginal[0], newOriginal);

        Comparator<Point> comp = new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                BiFunction<Point, Point, Double> calcDistance = (p1, p2) -> {
                    double lX = Math.abs(p1.getX() - p2.getX());
                    double lY = Math.abs(p1.getY() - p2.getY());
                    return Math.sqrt(lX * lX + lY * lY);
                };
                double distA = calcDistance.apply(newOriginal[0], a);
                double distB = calcDistance.apply(newOriginal[0], b);
                return Double.compare(distA, distB);
            }
        };
        Arrays.sort(newOriginal, comp);
        assertArrayEquals(newOriginal, ps.getPoints());
    }
}
