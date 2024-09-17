package com.graphics.snake.core.bubbles;

import com.graphics.snake.core.Map;

public class Bubbles {
    // 1 //
    public final Matrix matrix = new Matrix(Map.width,Map.height);
    public final Point[] points = matrix.points;
    public final Generator generator = new Generator(points,1000);
    // 2 //
    private int type;
    public int[] bubbles = new int[50];
    // 3 //
    private boolean death;
    private int numberOfBubbles;

    public Bubbles(){ reset(); }

    public void reset(){
        for(Point point:points){ point.reset(); }
        for(int i = 0;i<bubbles.length;i++){ bubbles[i] = 0; }
        type = 0;
    }

    public int nextType()   { return ++type; }

    private void pointBubbles(int x,int y,int value,int type){
        matrix.getPointCenter(x,y).setValue(value);
        matrix.getPointCenter(x,y).setType(type);
    }

    public void updateDatabase(){
        death = false;
        numberOfBubbles = 0;
        for(int i = 0;i<bubbles.length;i++){
            if(bubbles[i] == 0&&generator.getMassOfType(i)>0){
                bubbles[i] = 1;
            }else
            if(generator.getMassOfType(i) == 0&&bubbles[i] == 1){
                bubbles[i] = 2;
                death = true;
            }

            if(bubbles[i] == 1){ numberOfBubbles++; }
        }
    }

    public boolean death(){ return death; }

    public boolean win(){
        if(numberOfBubbles==0){ return true; }
        return false;
    }

    public void start(int step){
        int radius = 3;
        int i = 0;
        for(int y = -radius;y<=radius;y++){
            for(int x = -radius;x<=radius;x++){
                if(++i==step){
                    pointBubbles(x*5,y*5,1000,nextType());
                }
            }
        }
    }

    public void start1(){
        pointBubbles(-1*20,0*20,1000,nextType());
        pointBubbles(+1*20,0*20,1000,nextType());
    }

    public void process(){
        generator.process();
        for(Point point:points){
            if(point.getValue()>0){ point.addValue(-1); }
        }
        updateDatabase();

        for(Point point:points){ point.process(); }
    }
}
