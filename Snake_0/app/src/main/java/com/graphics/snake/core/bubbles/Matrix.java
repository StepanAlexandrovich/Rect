package com.graphics.snake.core.bubbles;

public class Matrix {
    public Point[] points;
    public int width,height,length;

    public Matrix(int width,int height){
        this.length = (this.width = width)*(this.height = height);

        points = new Point[length];
        for(int i = 0;i<length;i++){
            points[i] = new Point();
        }

        for(int y = 0;y<height;y++){
            for(int x = 0;x<width;x++){
                // задаём точке координаты
                getPoint(x,y).x = x;
                getPoint(x,y).y = y;

                // устанавливаем связи c соседними точками
                getPoint(x,y).points[0] = getPoint(x,y);
                getPoint(x,y).points[1] = getPoint(x+1,y);
                getPoint(x,y).points[2] = getPoint(x,y+1);
                getPoint(x,y).points[3] = getPoint(x-1,y);
                getPoint(x,y).points[4] = getPoint(x,y-1);
            }
        }

    }

    ///////////// getPoint() /////////////////
    public int ring(int vel,int min,int max){
        if(vel<min)  { vel = vel + (max - min + 1); }
        if(vel>max)  { vel = vel - (max - min + 1); }
        return vel;
    }

    public Point getPoint(int x,int y){
        return points[width*ring(y,0,height - 1) + ring(x,0,width - 1)];
    }

    public Point getPointCenter(int a,int b){
        return getPoint(width/2+a,height/2+b);
    }
    //////////////////////////////////////////////////
}
