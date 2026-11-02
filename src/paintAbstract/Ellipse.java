package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Shape{
	public Point center;
	public int majorRadius;
	public int minorRadius;
	
	public Ellipse() {
			this.name = "Ellipse_" + MyTools.getRandomInteger(1000);
			this.center = new Point();
	        this.majorRadius = 1;
	        this.minorRadius=1;
	        this.borderColor = Color.BLACK;
	        this.fillColor = Color.white;
	}
	
	 public Ellipse(Point center, int majorRadius, int minorRadius, Color borderColor, Color fillColor) {
	        this.name = "Ellipse_" + MyTools.getRandomInteger(1000);
	        this.center = center;
	        this.majorRadius=majorRadius;
	        this.minorRadius=minorRadius;
	        this.borderColor = borderColor;
	        this.fillColor = fillColor;
	    }

	@Override
	public void draw(Graphics gr) {
		if (isFilled) {
            gr.setColor(fillColor);
            gr.fillOval(center.getX() - majorRadius, center.getY() - minorRadius, majorRadius * 2, minorRadius * 2);
        } else {
            gr.setColor(borderColor);
            gr.drawOval(center.getX() - majorRadius, center.getY() - minorRadius, majorRadius * 2, minorRadius * 2);

        }
	}

	@Override
	public void move(int xMove, int yMove) {
		center.setX(xMove + center.getX());
        center.setY(yMove + center.getY());
        System.out.println(name+ " moved.");
	}

	@Override
	public void erase() {
		System.out.println(name + " Deleted.");
	}

	@Override
	public void scale(double factor) {
		majorRadius = Math.max(1, (int) Math.round(majorRadius * factor));
		minorRadius = Math.max(1, (int) Math.round(minorRadius * factor));
	}

	@Override
	public boolean hit(Point p) {
		if (majorRadius == 0 || minorRadius == 0)
			return false;
		return (Math.pow((p.getX() - center.getX()), 2) / Math.pow(majorRadius, 2)) + 
				(Math.pow((p.getY() - center.getY()), 2) / Math.pow(minorRadius, 2)) <=1;
	}
	
}
