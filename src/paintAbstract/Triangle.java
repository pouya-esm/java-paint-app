package paintAbstract;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape {

    public Point p1, p2, p3;

    public Triangle() {
        this.name = "Triangle_" + MyTools.getRandomInteger(1000);
        this.p1 = new Point();
        this.p2 = new Point();
        this.p3 = new Point();
        this.borderColor = Color.black;
        this.fillColor = Color.red;
    }

    public Triangle(Point p1, Point p2, Point p3, Color borderColor, Color fillColor) {
        this.name = "Triangle_" + MyTools.getRandomInteger(1000);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    public void draw(Graphics gr) {
    	 if (isFilled) {
             gr.setColor(fillColor);
             gr.fillPolygon(new int[] {p1.getX(),p2.getX(),p3.getX()}, new int[] {p1.getY(),p2.getY(),p3.getY()} , 3);

         } else {
             gr.setColor(borderColor);
             gr.drawPolygon(new int[] {p1.getX(),p2.getX(),p3.getX()}, new int[] {p1.getY(),p2.getY(),p3.getY()} , 3);

         }
    	
    }

    public void move(int xMove, int yMove) {
        p1.setX(xMove + p1.getX());
        p1.setY(yMove + p1.getY());

        p2.setX(xMove + p2.getX());
        p2.setY(yMove + p2.getY());

        p3.setX(xMove + p3.getX());
        p3.setY(yMove + p3.getY());
        System.out.println(name + " moved.");
    }

    public void erase() {
        System.out.println(name + " Deleted.");
    }
    
    public void scale(double factor) {
        double cx = (p1.getX() + p2.getX() + p3.getX()) / 3.0;
        double cy = (p1.getY() + p2.getY() + p3.getY()) / 3.0;
        scalePoint(p1, cx, cy, factor);
        scalePoint(p2, cx, cy, factor);
        scalePoint(p3, cx, cy, factor);
    }

    public double triangleArea(Point p1, Point p2, Point p3) {
    	 return Math.abs((p1.getX()*(p2.getY()-p3.getY()) + p2.getX()*(p3.getY()-p1.getY())+ p3.getX()*(p1.getY()-p2.getY()))/2.0);
    }

    public boolean hit(Point p) {
    	double area = triangleArea (p1, p2, p3);          
    	double area1 = triangleArea (p, p2, p3);      
    	double area2 = triangleArea (p1, p, p3);     
    	double area3 = triangleArea (p1, p2, p);
        return (area == area1+area2+area3);
    }
}
