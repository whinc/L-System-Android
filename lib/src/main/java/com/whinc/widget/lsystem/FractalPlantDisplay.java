package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.whinc.widget.lsystem.display.AbsDisplay;

/**
 * Created by Administrator on 2015/11/20.
 */
public class FractalPlantDisplay extends AbsDisplay {

    public FractalPlantDisplay() {
        this("F", "->", "F->FF[++F-F-F][-F+F+F]");
    }

    public FractalPlantDisplay(String axiom, String delimiter, String... rules) {
        super(axiom, delimiter, rules);
    }

    @Override
    public void drawContent(Canvas canvas, Paint paint) {
        String pattern = getPattern();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            float startX = getPercentX() * canvas.getWidth();
            float startY = getPercentY() * canvas.getHeight();
            float stopX = startX + getStep() * (float)Math.cos(Math.toRadians((double) getDirection()));
            float stopY = startY + getStep() * (float)Math.sin(Math.toRadians((double) getDirection()));
            switch (c) {
                case 'F':
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    setPercentX(stopX / canvas.getWidth());
                    setPercentY(stopY / canvas.getHeight());
                    break;
                case '[':
                    saveDirection();
                    savePos();
                    break;
                case ']':
                    restoreDirection();
                    restorePos();
                    break;
                case '+':
                    rotateDirection(getAngle());
                    break;
                case '-':
                    rotateDirection(-getAngle());
                    break;
                default:
                    break;
            }
        }
    }
}
