package com.graphics.snake.core.snake;

public class GeneratorMotors {
    public PointOptimization[] pointsMotors;
    public PointOptimization[] pointsHead;
    public int length;
    public int[] massOfType   = new int[800];
    public int[] generation   = new int[800];
    public int power;

    public GeneratorMotors(PointOptimization[] pointsMotors,PointOptimization[] pointsHead,int power){
        this.pointsMotors = pointsMotors;
        this.pointsHead = pointsHead;
        this.power = power;
        this.length = pointsMotors.length;
    }

    public void process(){
        for(int i = 0;i<massOfType.length;i++){
            massOfType[i] = 0;
        }

        for(int i = 0;i<length;i++){
            if(pointsHead[i].getValue()>0&&pointsMotors[i].getValue()>0){
                massOfType[pointsMotors[i].getType()] += 1;
            }
        }


        for(int i = 1;i<massOfType.length;i++){
            if(massOfType[i]!=0){
                generation[i] = power/massOfType[i];
            }
        }

        for(int i = 0;i<length;i++){
            if(pointsHead[i].getValue()>0&&pointsMotors[i].getValue()>0){
                pointsMotors[i].addValue(generation[pointsMotors[i].getType()]);
            }
        }

    }
}
