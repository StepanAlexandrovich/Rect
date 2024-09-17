package com.graphics.snake.core.bubbles;

import com.graphics.snake.core.Next;

public class Point extends Next {
    // final
    public int x,y;
    public Point[] points = new Point[5]; // 0 - in place; 1 - left; 2 - up; 3 - right; 4 - down;
    // dynamic
    private int[] value = new int[2];
    private int[] type = new int[2];

    //// getSet ////
    public int getValue()   { return value[now]; }
    public void setValue(int v) { value[now]  = v; }
    public void addValue(int v) {
        value[now] += v;
        if(value[now]<0){ value[now] = 0; }
    }

    public int getType()   { return type[now]; }
    public void setType(int v){ type[now] = v; }

    public void reset(){
        value[0] = 0; value[1] = 0;
        type[0] = 0; type[1] = 0;
    }

    @Override
    public void next(){
        this.value[next] = this.value[now];
        this.type[next] = this.type[now];

        for(Point point:points){
            int sum = 0;
            int value = 0;

            if(point.type[now] == this.type[now]){ value = this.value[now]; }

            for(Point point1:points){
                if(point.type[now] == point1.type[now]){
                    sum += point1.value[now] - value;
                }else{
                    sum += -point1.value[now] - value;
                }
            }

            int nextThisValue = value + sum/5;
            if(nextThisValue>0){
                this.value[next] = nextThisValue;
                this.type[next] = point.type[now];
                break;
            }
        }

        if(value[next]==0){ type[next] = 0; }

    }
}
