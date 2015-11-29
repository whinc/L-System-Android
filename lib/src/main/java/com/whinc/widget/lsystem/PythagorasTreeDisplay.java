package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.whinc.util.Log;
import com.whinc.widget.lsystem.display.AbsDisplay;

/**
 * Created by Administrator on 2015/11/20.
 */
public class PythagorasTreeDisplay extends AbsDisplay {

    public static final String TAG = PythagorasTreeDisplay.class.getSimpleName();

    public PythagorasTreeDisplay() {
        this("0", "->", "1->11", "0->1[0]0");
    }

    public PythagorasTreeDisplay(String axiom, String delimiter, String... rules) {
        super(axiom, delimiter, rules);
    }

    @Override
    public void drawContent(Canvas canvas) {
        String pattern = getPattern();
        Log.i(TAG, "pattern:" + pattern);
        Log.i(TAG, String.format("canvas size:(%d, %d)", canvas.getWidth(), canvas.getHeight()));
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            PointF start = getPosition(canvas.getWidth(), canvas.getHeight());
            float deltaX = getStep() * (float)Math.cos(Math.toRadians((double) getDirection()));
            float deltaY = getStep() * (float)Math.sin(Math.toRadians((double) getDirection()));
            PointF stop = new PointF(start.x + deltaX, start.y + deltaY);
            switch (c) {
                case '1':
                    canvas.drawLine(start.x, start.y, stop.x, stop.y, getPaint());
                    setPosition(canvas.getWidth(), canvas.getHeight(), stop);
                    break;
                case '0':
                    canvas.drawLine(start.x, start.y, stop.x, stop.y, getPaint());
                    break;
                case '[':
                    saveDirection();
                    savePos();
                    rotateDirection(-getAngle());
                    break;
                case ']':
                    restoreDirection();
                    restorePos();
                    rotateDirection(getAngle());
                    break;
                default:
                    break;
            }
        }
    }
}
