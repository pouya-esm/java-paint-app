package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

    public Point p1, p2;

    public Rectangle() {
        this.name = "Rectangle_" + MyTools.getRandomInteger(1000);
        this.p1 = new Point();
        this.p2 = new Point();
        this.borderColor = Color.black;
        this.fillColor = Color.yellow;
    }

    public Rectangle(Point p1, Point p2, Color borderColor, Color fillColor) {
        this.name = "Rectangle_" + MyTools.getRandomInteger(1000);
        this.p1 = p1;
        this.p2 = p2;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public void draw(Graphics gr) {
        setPoints();
        if (isFilled) {
            gr.setColor(fillColor);
            gr.fillRect(p1.getX(), p1.getY(), p2.getX() - p1.getX(), p2.getY() - p1.getY());
        } else {
            gr.setColor(borderColor);
            gr.drawRect(p1.getX(), p1.getY(), p2.getX() - p1.getX(), p2.getY() - p1.getY());

        }
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
        setPoints();
        double cx = (p1.getX() + p2.getX()) / 2.0;
        double cy = (p1.getY() + p2.getY()) / 2.0;
        scalePoint(p1, cx, cy, factor);
        scalePoint(p2, cx, cy, factor);
    }

    public boolean hit(Point p) {
        setPoints();
        return (p1.getX() <= p.getX() && p.getX() <= p2.getX())
                && (p1.getY() <= p.getY() && p.getY() <= p2.getY());
    }

    public void setPoints() {
        if (p1.getX() > p2.getX()) {
            int t = p1.getX();
            p1.setX(p2.getX());
            p2.setX(t);
        }

        if (p1.getY() > p2.getY()) {
            int t = p1.getY();
            p1.setY(p2.getY());
            p2.setY(t);
        }

    }
}
