package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {

    public Point center;
    public int radius;

    public Circle() {
        this.name = "Circle_" + MyTools.getRandomInteger(1000);
        this.center = new Point();
        this.radius = 1;
        this.borderColor = Color.BLACK;
        this.fillColor = Color.white;
    }

    public Circle(Point center, int radius, Color borderColor, Color fillColor) {
        this.name = "Circle_" + MyTools.getRandomInteger(1000);
        this.center = center;
        this.radius = radius;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public void draw(Graphics gr) {
        if (isFilled) {
            gr.setColor(fillColor);
            gr.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        } else {
            gr.setColor(borderColor);
            gr.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);

        }

    }

    public void move(int xMove, int yMove) {
        center.setX(xMove + center.getX());
        center.setY(yMove + center.getY());
        System.out.println(name+ " moved.");
    }

    public void erase() {
        System.out.println(name + " Deleted.");
    }

    public void scale(double factor) {
        radius = Math.max(1, (int) Math.round(radius * factor));
    }

    public boolean hit(Point p) {
        return center.distance(p) <= radius;
    }

}
