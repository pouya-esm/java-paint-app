package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{

    public Point p1, p2;
    

    public Line() {
        name = "Line_" + MyTools.getRandomInteger(1000);
        this.p1 = new Point();
        this.p2 = new Point();
        this.borderColor = Color.black;
        

    }

    public Line(Point p1, Point p2, Color borderColor) {
        name = "Line_" + MyTools.getRandomInteger(1000);
        this.p1 = p1;
        this.p2 = p2;
        this.borderColor = borderColor;
    }

    public void draw(Graphics gr) {
        gr.setColor(borderColor);
        gr.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void move(int xMove, int yMove) {
        p1.setX(xMove + p1.getX());
        p1.setY(yMove + p1.getY());

        p2.setX(xMove + p2.getX());
        p2.setY(yMove + p2.getY());
        System.out.println(name+ " moved.");
    }

    public void erase() {
        System.out.println(name + " Deleted.");
    }

    public void scale(double factor) {
        double cx = (p1.getX() + p2.getX()) / 2.0;
        double cy = (p1.getY() + p2.getY()) / 2.0;
        scalePoint(p1, cx, cy, factor);
        scalePoint(p2, cx, cy, factor);
    }

    public boolean hit(Point p) {
        return p1.distance(p) + p2.distance(p)-p1.distance(p2)<1;
    }
}
