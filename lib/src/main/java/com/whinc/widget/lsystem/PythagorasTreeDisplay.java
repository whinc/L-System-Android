package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * Created by Administrator on 2015/11/20.
 */
public class PythagorasTreeDisplay extends AbsDisplay {
    private final Generator mGenerator;

    public PythagorasTreeDisplay() {
        mGenerator = new GeneratorImpl("0", "->", "1->11", "0->1[0]0");
    }

    @Override
    public void draw(Canvas canvas) {
        String pattern = mGenerator.generate(getIterations());
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            PointF start = getPos();
            PointF stop = new PointF(
                    start.x + getStep() * (float)Math.cos(Math.toRadians((double)getAngle())),
                    start.y + getStep() * (float)Math.sin(Math.toRadians((double)getAngle()))
            );
            switch (c) {
                case '1':
                    canvas.drawLine(start.x, start.y, stop.x, stop.y, getPaint());
                    setPos(stop);
                    break;
                case '0':
                    canvas.drawLine(start.x, start.y, stop.x, stop.y, getPaint());
                    break;
                case '[':
                    pushAngle();
                    pushPos();
                    rotateAngle(-getDeltaAngle());
                    break;
                case ']':
                    popAngle();
                    popPos();
                    rotateAngle(getDeltaAngle());
                    break;
                default:
                    break;
            }
        }
    }
}
