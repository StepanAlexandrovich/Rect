package com.graphics.snake.wrapper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class Panel extends View {
    Paint paint = new Paint();
    Animation animation = new Animation();

    public Panel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        animation.process(canvas, paint);

        if (animation.map.lossDeath()) {
            window(canvas, "THE SNAKE DIED", -0, new int[]{255, 0, 0});
        } else if (animation.map.win()) {
            window(canvas, "YOU WIN", -0, new int[]{255, 000, 255});
        } else if (animation.map.lossTime()) {
            window(canvas, "TIME IS ENDED", -0, new int[]{255, 0, 0});
        }

        invalidate();
    }

    public void start(){
        animation.reset();
    }

    private void window(Canvas canvas,String text,int shift,int[] rgb){
        // надписи
        paint.setTextSize(50);
        paint.setARGB(255,rgb[0],rgb[1],rgb[2]);

        canvas.drawText(text,animation.width/2 + shift,animation.height/2,paint);
    }

}
