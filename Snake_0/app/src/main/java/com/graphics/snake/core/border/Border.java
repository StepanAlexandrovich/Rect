package com.graphics.snake.core.border;

import com.graphics.snake.core.Map;

public class Border {
    public Matrix matrix = new Matrix(Map.width,Map.height);
    public Point[] points = matrix.points;

    public Point[] frame(){
        for(int y = 0;y<Map.width;y++){
            for(int x = 0;x<Map.height;x++){
                matrix.getPoint(x, y).border = true;
            }
        }

        for(int y = 0+1;y<Map.width-1;y++){
            for(int x = 0+1;x<Map.height-1;x++){
                matrix.getPoint(x, y).border = false;
            }
        }


        return points;
    }
}
