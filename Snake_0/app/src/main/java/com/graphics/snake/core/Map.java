package com.graphics.snake.core;

import com.graphics.snake.core.border.Border;
import com.graphics.snake.core.bubbles.Bubbles;
import com.graphics.snake.core.snake.Snake;

public class Map {
    ////// final
    // 1 //
    public final static int width = 151,height = 151,length = width*height;
    // 2 //
    public final Snake snake = new Snake();
    public final Bubbles bubbles = new Bubbles();
    public final Border border = new Border();
    // 3 //
    public final com.graphics.snake.core.snake.PointOptimization[] pointsHead = snake.pointsHead;
    public final com.graphics.snake.core.snake.PointOptimization[] pointsMotors = snake.pointsMotors;
    public final com.graphics.snake.core.snake.PointOptimization[] pointsBody = snake.pointsBody;
    public final com.graphics.snake.core.bubbles.Point[] pointsBubbles = bubbles.points;
    public final com.graphics.snake.core.border.Point[] pointsBorder = border.frame();

    ////// dinamic
    public int step;
    public boolean stop;

    public void reset(){
        step = 0;
        stop = false;
        bubbles.reset();
        snake.reset();
    }

    public boolean win(){ return bubbles.win(); }

    public boolean lossDeath(){ return snake.death(); }

    public boolean lossTime(){
        if(step>4000){ return true; }
        return false;
    }

    private void interaction(){
        for(int i = 0;i<Map.length;i++){
            if(pointsHead[i].getValue()>0||pointsMotors[i].getValue()>0||pointsBody[i].getValue()>0){
                pointsBubbles[i].setValue(0);
            }

            if(pointsBorder[i].border){
                pointsBubbles[i].setValue(0);
                pointsHead[i].setValue(0);
                pointsBody[i].setValue(0);
                pointsMotors[i].setValue(0);
            }
        }
    }

    private void stop(){
        if(win()||lossDeath()||lossTime()){ stop = true; }
    }

    public void process(){
        if(!stop) {
            step++;

            snake.start(step);
            bubbles.start(step);

            interaction();
            snake.process();
            bubbles.process();

            stop();
        }

        //System.out.println(Turn_Bubbles.test(bubbles.matrixBubbles,"right"));
        //System.out.println(Turn_Snake.test(snake.matrixMotors,"uTurn"));
    }
}
