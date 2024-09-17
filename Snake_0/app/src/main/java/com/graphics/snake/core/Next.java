package com.graphics.snake.core;

public class Next {
    // static
    public int[] table = {1,0};
    // dinamic
    public int now,next=table[now];

    public void next(){}

    public void process(){
        next();
        next = table[now = table[now]];
    }
}
