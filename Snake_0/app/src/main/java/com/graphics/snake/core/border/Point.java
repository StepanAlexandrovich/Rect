package com.graphics.snake.core.border;

import com.graphics.snake.core.Next;

public class Point extends Next {
    // final
    public int x,y;
    public Point[] points = new Point[5]; // 0 - in place; 1 - left; 2 - up; 3 - right; 4 - down;
    // dynamic
    public boolean border;
}
