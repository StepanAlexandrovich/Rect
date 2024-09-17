package com.graphics.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.graphics.snake.wrapper.ButtonListener;
import com.graphics.snake.wrapper.Panel;

public class MainActivity extends AppCompatActivity {
    public static Button buttonLeft,buttonRight,buttonStart;
    public ButtonListener buttonListener;
    public Panel panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        buttonStart = findViewById(R.id.buttonStart);

        panel = findViewById(R.id.panel);
        buttonListener = new ButtonListener(panel);
    }
}