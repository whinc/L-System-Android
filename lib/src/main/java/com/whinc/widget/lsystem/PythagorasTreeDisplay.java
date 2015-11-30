package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.Paint;

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
    public void drawContent(Canvas canvas, Paint paint) {
        String pattern = getPattern();
        Log.i(TAG, "pattern:" + pattern);
        Log.i(TAG, String.format("canvas size:(%d, %d)", canvas.getWidth(), canvas.getHeight()));
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            float startX = getFractionPosX() * canvas.getWidth();
            float startY = getFractionPosY() * canvas.getHeight();
            float stopX = startX + getStep() * (float)Math.cos(Math.toRadians((double) getDirection()));
            float stopY = startY + getStep() * (float)Math.sin(Math.toRadians((double) getDirection()));
            switch (c) {
                case '1':
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    setFractionPosX(stopX / canvas.getWidth());
                    setFractionPosY(stopY / canvas.getHeight());
                    break;
                case '0':
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case '[':
                    saveDirection();
                    savePos();
                    Log.i(TAG, c + " after save: " + toString());
                    rotateDirection(-getAngle());
                    break;
                case ']':
                    restoreDirection();
                    restorePos();
                    Log.i(TAG, c + " after restore: " + toString());
                    rotateDirection(getAngle());
                    break;
                default:
                    break;
            }
            Log.i(TAG, c + " " + toString());
        }
    }
}
