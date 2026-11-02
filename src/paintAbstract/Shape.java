package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    public String name;
    public Color borderColor;
    public Color fillColor;
    public boolean isFilled = false;

    public abstract void draw(Graphics gr);

    public abstract void move(int xMove, int yMove);

    public abstract void erase();

    public abstract boolean hit(Point p);

    /**
     * Resizes the shape around its own center.
     *
     * @param factor 2.0 doubles the size, 0.5 halves it
     */
    public abstract void scale(double factor);

    /**
     * Scales a single point around the center (cx, cy).
     */
    protected static void scalePoint(Point p, double cx, double cy, double factor) {
        p.setXY((int) Math.round(cx + (p.getX() - cx) * factor),
                (int) Math.round(cy + (p.getY() - cy) * factor));
    }
}
