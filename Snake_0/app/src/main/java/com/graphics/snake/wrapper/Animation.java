package com.graphics.snake.wrapper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.graphics.snake.core.Map;

public class Animation {
    // 1 //
    public Map map = new Map();
    public int length = map.length;
    public int mult,brightBubbles,brightSnake,greenSnake,redSnake;
    public int width,height;
    // 2 //
    com.graphics.snake.core.bubbles.Point[] pointBubbles = map.bubbles.points;
    com.graphics.snake.core.snake.PointOptimization[] pointHead = map.snake.pointsHead;
    com.graphics.snake.core.snake.PointOptimization[] pointMotors = map.snake.pointsMotors;
    com.graphics.snake.core.snake.PointOptimization[] pointBody = map.snake.pointsBody;
    com.graphics.snake.core.border.Point[] pointBorder = map.border.points;
    // 3 //
    com.graphics.snake.core.bubbles.Generator generatorBubbles = map.bubbles.generator;

    public Animation(){ reset(); }

    public void reset(){
        map.reset();
        mult = 4;
        width  = Map.width*mult;
        height = Map.height*mult;
        brightBubbles = 2;
        brightSnake   = 15;
        greenSnake = 0;
        redSnake = 0;
    }

    public int border(int value){
        if(value>255){ value = 255; }
        return value;
    }

    public void process(Canvas canvas, Paint paint){
        map.process(); //
        ////////////////

        if(map.bubbles.death()){ greenSnake = 5; }
        if(greenSnake>0){ greenSnake --; }
        if(map.snake.death()){ redSnake = 5; }

        for(int i = 0;i<length;i++){

            int bubbles = pointBubbles[i].getValue();
            int head = pointHead[i].getValue();
            int motors = pointMotors[i].getValue();
            int body = pointBody[i].getValue();
            boolean border = pointBorder[i].border;

            int x = pointBubbles[i].x;
            int y = pointBubbles[i].y;
            ////////////////////////////ОПРЕДЕЛЕНИЕ ЦВЕТА//////////////////////////
            bubbles = border(bubbles * brightBubbles);
            head = border(head * brightSnake);
            motors = border(motors * brightSnake);
            body = border(body * brightSnake);

            if(border){ paint.setColor(Color.argb(255,255,0,0)); }else
            if(head>0||motors>0||body>0){

                if(redSnake >0){
                    if(head>0||motors>0||body>0){
                        paint.setColor(Color.argb(255,100,0,0));
                    }
                }else
                if(greenSnake >0){
                    if(head>0||motors>0||body>0){
                        paint.setColor(Color.argb(255,0,255,0));
                    }
                }else
                if(greenSnake==0){  paint.setColor(Color.argb(255,head,motors,body)); }

            }else{
                int mass = border(generatorBubbles.getMassOfType(pointBubbles[i]));
                paint.setColor(Color.argb(255,bubbles,mass,mass));
            }

            //g.setColor(new Color(head,0,0));
            //g.setColor(new Color(motors,0,0));
            //g.setColor(new Color(body,0,0));

            ///////////////////////////////////////////////////////////////////////
            canvas.drawRect(x*mult,y*mult,mult*(x+1),mult*(y+1),paint);
        }

    }
}
