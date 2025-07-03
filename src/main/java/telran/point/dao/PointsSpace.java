package main.java.telran.point.dao;

import java.util.Arrays;
import java.util.Comparator;

import main.java.telran.point.model.Point;

public class PointsSpace {
    Point relPoint;
    Point[] points;
    Comparator<Point> comp = new Comparator<Point>() {
        @Override
        public int compare(Point a, Point b) {
            double distA = calcDistance(relPoint, a);
            double distB = calcDistance(relPoint, b);
            return Double.compare(distA, distB);
        }
    };

    public PointsSpace(Point relPoint, Point[] points) {
        this.relPoint = relPoint;

        Arrays.sort(points, comp);
        this.points = points;            //FIXME Fix problem

        //TODO sorting this.points by proximity to relPoint (ask to Pithagoras)
        //to apply method sort of class Arrays
    }

    private double calcDistance(Point relPoint, Point refPoint) {
        double lX = Math.abs(relPoint.getX() - refPoint.getX());
        double lY = Math.abs(relPoint.getY() - refPoint.getY());
        return Math.sqrt(lX*lX + lY * lY);
    }

    public Point[] getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        //TODO keep sort of this.points
        int index = Arrays.binarySearch(points, point, comp) < 0 ? - Arrays.binarySearch(points, point, comp) - 1 : Arrays.binarySearch(points, point, comp) ;
        Point[] result = new Point[points.length + 1];
        System.arraycopy(points, 0, result, 0, index);
        result[index] = point;
        System.arraycopy(points, index, result, index + 1, points.length - index);
        this.points = result;
    }
}
