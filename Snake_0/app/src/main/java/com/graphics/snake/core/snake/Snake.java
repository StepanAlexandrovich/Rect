package com.graphics.snake.core.snake;

import com.graphics.snake.core.Map;

public class Snake {
    ////// final //////
    public final MatrixOptimization matrixHead = new MatrixOptimization(Map.width,Map.height);
    public final PointOptimization[] pointsHead = matrixHead.points;
    public final GeneratorHead generatorHead = new GeneratorHead(pointsHead,8000);

    public final MatrixOptimization matrixMotors = new MatrixOptimization(Map.width,Map.height);
    public final PointOptimization[] pointsMotors = matrixMotors.points;
    public final GeneratorMotors generatorMotors = new GeneratorMotors(pointsMotors,pointsHead,20);

    public final MatrixOptimization matrixBody = new MatrixOptimization(Map.width,Map.height);
    public final PointOptimization[] pointsBody = matrixBody.points;
    ////// dinamic //////
    public int right,left;

    public Snake(){ reset(); }

    private void pointHead(int x,int y,int value,int type){
        matrixHead.getPointCenter(x,y).setValue(value);
        matrixHead.getPointCenter(x,y).setType(type);
    }

    private void pointMotors(int x,int y,int value,int type){
        matrixMotors.getPointCenter(x,y).setValue(value);
        matrixMotors.getPointCenter(x,y).setType(type);
    }

    private void pointBody(int x,int y,int value,int type){
        matrixBody.getPointCenter(x,y).setValue(value);
        matrixBody.getPointCenter(x,y).setType(type);
    }

    public void start(int step){
        if(step==1){
            int x = -70,y = 70;

            pointHead(0+x,+0+y,1000,1);
            pointMotors(-1+x,+2+y,1000,1);
            pointMotors(+1+x,+2+y,1000,2);
        }
    }

    public void right(){
        right = 4;
        left = 2;
    }

    public void left(){
        right = 2;
        left = 4;
    }

    public void reset(){
        for(int i = 0;i<Map.length;i++){
            pointsHead[i].reset();
            pointsMotors[i].reset();
            pointsBody[i].reset();
        }
        right = 3;
        left = 2;
    }

    public boolean death(){
        int amountHeadBody = 0;
        int amountMotors1 = 0,amountMotors2 = 0;
        for(int i = 0;i<Map.length;i++){
            if(pointsHead[i].getValue()>0&&pointsBody[i].getValue()==0){
                amountHeadBody++;
            }
            if(pointsMotors[i].getValue()>0&&pointsMotors[i].getType()==1){
                amountMotors1++;
            }
            if(pointsMotors[i].getValue()>0&&pointsMotors[i].getType()==2){
                amountMotors2++;
            }
        }
        if(amountHeadBody==0){ return true; }
        if(amountMotors1==0||amountMotors2==0){ return true; }
        return false;
    }

    private void interaction(){

        for(int i = 0;i<Map.length;i++){
            // head
            if(pointsHead[i].getValue()>0&&pointsMotors[i].getValue()>0){
                pointsHead[i].addValue(-pointsHead[i].getValue()/2);
            }
            // two motors
            if(pointsMotors[i].getType()==1&&pointsHead[i].getValue()>0&&pointsMotors[i].getValue()>0){
                pointsMotors[i].addValue(right);
            }
            if(pointsMotors[i].getType()==2&&pointsHead[i].getValue()>0&&pointsMotors[i].getValue()>0){
                pointsMotors[i].addValue(left);
            }

            //body
            if(pointsMotors[i].getType()==1&&pointsMotors[i].getValue()>0){
                pointsBody[i].addValue(20);
                pointsBody[i].setType(1);
            }
            if(pointsMotors[i].getType()==2&&pointsMotors[i].getValue()>0){
                pointsBody[i].addValue(20);
                pointsBody[i].setType(1);
            }

            // decrease
            if(pointsMotors[i].getValue()>0){
                pointsMotors[i].addValue(-1);
            }
            if(pointsHead[i].getValue()>0){
                pointsHead[i].addValue(-1);
            }
            if(pointsBody[i].getValue()>0){
                pointsBody[i].addValue(-1);
            }
        }

    }

    public void process(){
        //interaction();
        generatorHead.process();
        generatorMotors.process();
        interaction();

        for(PointOptimization head:pointsHead){ head.process(); }
        for(PointOptimization motors:pointsMotors){ motors.process(); }
        for(PointOptimization body:pointsBody){ body.process(); }
    }
}
