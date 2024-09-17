package com.graphics.snake.core.bubbles;

public class Generator {
    public Point[] points;
    public int[] massOfTypes   = new int[800];
    public int power;

    public Generator(Point[] points,int power){
        this.points = points;
        this.power = power;
    }

    public int getMassOfType(Point point){
        return massOfTypes[point.getType()] ;
    }

    public int getMassOfType(int type){
        return massOfTypes[type] ;
    }

    public void process(){
        for(int i = 1;i<massOfTypes.length;i++){
            massOfTypes[i] = 0;
        }

        for(Point point:points){
            if(point.getValue()>100){
                massOfTypes[point.getType()] = massOfTypes[point.getType()] + 1;
            }
        }

        for(Point point:points){
            if(point.getValue()>100){
                point.addValue(power/massOfTypes[point.getType()]);
            }
        }
    }
}
