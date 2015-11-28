package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.whinc.widget.lsystem.display.AbsDisplay;

/**
 * Created by Administrator on 2015/11/20.
 */
public class PythagorasTreeDisplay extends AbsDisplay {

    public PythagorasTreeDisplay() {
        this("0", "->", "1->11", "0->1[0]0");
    }

    public PythagorasTreeDisplay(String axiom, String delimiter, String... rules) {
        super(axiom, delimiter, rules);
    }

    @Override
    public void draw(Canvas canvas) {
        String pattern = getPattern();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            PointF start = getPosition(canvas.getWidth(), canvas.getHeight());
            PointF stop = new PointF(
                    start.x + getStep() * (float)Math.cos(Math.toRadians((double) getDirection())),
                    start.y + getStep() * (float)Math.sin(Math.toRadians((double) getDirection()))
            );
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
