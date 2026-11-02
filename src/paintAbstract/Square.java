package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape{
	public Point p1;
	public int edge;
	
	public Square() {
		  this.name = "Square_" + MyTools.getRandomInteger(1000);
	        this.p1 = new Point();
	        this.borderColor = Color.black;
	        this.fillColor = Color.yellow;
	}
	
	public Square(Point p1, int edge, Color borderColor, Color fillColor) {
        this.name = "Square_" + MyTools.getRandomInteger(1000);
        this.p1 =p1;
        this.edge=edge;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

	@Override
	public void draw(Graphics gr) {
		if (isFilled) {
            gr.setColor(fillColor);
            gr.fillRect(p1.getX(), p1.getY(), edge, edge);
        } else {
            gr.setColor(borderColor);
            gr.drawRect(p1.getX(), p1.getY(), edge, edge);

        }
    }

	@Override
	public void move(int xMove, int yMove) {
		p1.setX(p1.getX()+xMove);
		p1.setY(p1.getY()+yMove);	
		System.out.println(name + " moved.");
	}

	@Override
	public void erase() {
		System.out.println(name + " Deleted.");
		
	}

	@Override
	public void scale(double factor) {
		double cx = p1.getX() + edge / 2.0;
		double cy = p1.getY() + edge / 2.0;
		edge = Math.max(1, (int) Math.round(edge * factor));
		p1.setXY((int) Math.round(cx - edge / 2.0), (int) Math.round(cy - edge / 2.0));
	}

	@Override
	public boolean hit(Point p) {
		return p.getX() >= p1.getX() && p.getX() <= p1.getX() + edge
				&& p.getY() >= p1.getY() && p.getY() <= p1.getY() + edge;
	}
}
