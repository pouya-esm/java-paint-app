package paintAbstract;

public class MyTools {

    public static int getRandomInteger(int maxNumber){
        return (int)(Math.random()*maxNumber);
    }
    
    public static Point getRandomPoint(int maxNumberX,int maxNumberY){
        return new Point(getRandomInteger(maxNumberX), getRandomInteger(maxNumberY));
    }
}
