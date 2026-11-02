package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Polygon extends Shape{
	public Point[] pArr;
		
	 public Polygon() {
	        this.name = "Polygon_" + MyTools.getRandomInteger(1000);
	        this.pArr = new Point[0];
	        this.borderColor = Color.black;
	        this.fillColor = Color.yellow;
	    }
	 
	    public Polygon(Point[] pArr, Color borderColor, Color fillColor) {
	        this.name = "Polygon_" + MyTools.getRandomInteger(1000);
	        this.pArr=pArr;
	        this.borderColor = borderColor;
	        this.fillColor = fillColor;
	    }

		@Override
		public void draw(Graphics gr) {
			int[] x=new int[pArr.length];
			int[] y=new int[pArr.length];
			for (int i=0 ; i<pArr.length; i++) {
				x[i]=pArr[i].getX();
				y[i]=pArr[i].getY();
			}
			if (isFilled) {
	            gr.setColor(fillColor);
	            gr.fillPolygon(x, y, pArr.length);
	        } else {
	            gr.setColor(borderColor);
	            gr.drawPolygon(x, y, pArr.length);

	        }
	    }
		
		@Override
		public void move(int xMove, int yMove) {
			for (int i=0; i<pArr.length; i++) {
				pArr[i].setXY(pArr[i].getX()+xMove, pArr[i].getY()+yMove);
			}
			System.out.println(name + " moved!");
		}

		@Override
		public void erase() {
			System.out.println(name + " Deleted.");
		}

		@Override
		public void scale(double factor) {
			if (pArr.length == 0)
				return;
			double cx=0, cy=0;
			for (Point p : pArr) {
				cx+=p.getX();
				cy+=p.getY();
			}
			cx/=pArr.length;
			cy/=pArr.length;
			for (Point p : pArr)
				scalePoint(p, cx, cy, factor);
		}

		/**
		 * Point-in-polygon test (ray casting): a horizontal ray from the point
		 * crosses the polygon's edges an odd number of times when the point is inside.
		 */
		@Override
		public boolean hit(Point p) {
			if (pArr == null || pArr.length < 3)
				return false;
			boolean inside=false;
			for (int i=0, j=pArr.length-1; i<pArr.length; j=i++) {
				int xi=pArr[i].getX(), yi=pArr[i].getY();
				int xj=pArr[j].getX(), yj=pArr[j].getY();
				if ((yi>p.getY()) != (yj>p.getY())
						&& p.getX() < (double)(xj-xi)*(p.getY()-yi)/(yj-yi)+xi)
					inside=!inside;
			}
			return inside;
		}
}
