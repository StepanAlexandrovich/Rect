package com.graphics.snake.core.snake;

public class GeneratorHead {
    public PointOptimization[] points;
    public int[] massOfType   = new int[800];
    public int[] generation   = new int[800];
    public int power;

    public GeneratorHead(PointOptimization[] points,int power){
        this.points = points;
        this.power = power;
    }

    public void process(){
        for(int i = 0;i<massOfType.length;i++){
            massOfType[i] = 0;
        }

        for(PointOptimization point:points){
            if(point.getValue()>100){
                massOfType[point.getType()] = massOfType[point.getType()] + 1;
            }
        }

        for(int i = 1;i<massOfType.length;i++){
            if(massOfType[i]!=0){
                generation[i] = power/massOfType[i];
            }
        }

        for(PointOptimization point:points){
            if(point.getValue()>100){
                point.addValue(generation[point.getType()]);
            }
        }
    }
}
