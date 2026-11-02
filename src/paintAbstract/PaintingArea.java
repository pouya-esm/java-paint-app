package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * Keeps the list of shapes and paints them onto a canvas component.
 * All painting happens through {@link #drawAll(Graphics)}, which the canvas
 * calls from its paintComponent, so the drawing survives resizing/minimizing.
 */
public class PaintingArea {

    public ArrayList<Shape> shapeArr;
    public JComponent canvas;

    public PaintingArea(JComponent canvas) {
        shapeArr = new ArrayList<Shape>();
        this.canvas = canvas;
    }

    public void addShape(Shape s) {
        shapeArr.add(s);
        reDrawAll();
    }//addShape

    public boolean removeShape(int index) {
        return index >= 0 && index < shapeArr.size() && shapeArr.remove(index) != null;
    }//removeShape

    public int indexOf(Shape s) {
        return shapeArr.indexOf(s);
    }//indexOf

    public Shape getShapeByPoint(Point p) {
        Shape s = null;
        if (shapeArr != null) {
            // the shape drawn last is on top, so look from the end
            for (int i = shapeArr.size() - 1; i >= 0; i--) {
                if (shapeArr.get(i).hit(p)) {
                    s = shapeArr.get(i);
                    break;
                }
            }
        }
        return s;
    }//getShapeByPoint

    public boolean moveShape(Point p, int xMove, int yMove) {
        boolean found = false;
        Shape s = getShapeByPoint(p);
        if (s != null) {
            s.move(xMove, yMove);
            found = true;
            reDrawAll();
        }

        return found;
    }//moveShape    

    public boolean scaleShape(Point p, double factor) {
        Shape s = getShapeByPoint(p);
        if (s == null) {
            return false;
        }
        s.scale(factor);
        reDrawAll();
        return true;
    }//scaleShape

    public void drawAll(Graphics gr) {
        gr.setColor(Color.white);
        gr.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape s : shapeArr) {
            s.draw(gr);
        }
    }

    public void reDrawAll() {
        canvas.repaint();
    }

    public String getStatus() {
        int lCount = 0, cCount = 0, rCount = 0, tCount = 0, eCount = 0, sCount = 0, pCount = 0;
        if (shapeArr != null) {

            for (Shape s : shapeArr) {
                if (s instanceof Line) {
                    lCount++;
                } else if (s instanceof Circle) {
                    cCount++;
                } else if (s instanceof Rectangle) {
                    rCount++;
                } else if (s instanceof Triangle) {
                    tCount++;
                } else if (s instanceof Ellipse) {
                    eCount++;
                } else if (s instanceof Square) {
                    sCount++;
                } else if (s instanceof Polygon) {
                    pCount++;
                }
            }

        }

        return String.format("Lines:%d, Circles:%d, Ellipses:%d, Rectangles:%d, Squares:%d, Triangles:%d, Polygons:%d",
                lCount, cCount, eCount, rCount, sCount, tCount, pCount);
    }
}
