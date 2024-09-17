package com.graphics.snake.wrapper;

import android.view.View;
import android.widget.Button;

import com.graphics.snake.MainActivity;
import com.graphics.snake.R;
import com.graphics.snake.core.snake.Snake;

public class ButtonListener implements View.OnClickListener{
    public Button buttonRight = MainActivity.buttonRight;
    public Button buttonLeft = MainActivity.buttonLeft;
    public Button buttonStart = MainActivity.buttonStart;

    public Snake snake;
    public Panel panel;

    public ButtonListener(Panel panel){
        this.panel = panel;

        buttonRight.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);
        buttonStart.setOnClickListener(this);

        snake = panel.animation.map.snake;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonLeft) { snake.left(); }
        if(view.getId()==R.id.buttonRight){ snake.right(); }
        if(view.getId()==R.id.buttonStart){ panel.start(); }
    }
}
