package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.whinc.widget.lsystem.display.AbsDisplay;

/**
 * Created by Administrator on 2015/11/20.
 */
public class PythagorasTreeDisplay extends AbsDisplay {

    // 绘图操作与特定的构造参数是对应的，所以这里不能将构造参数暴露给外部修改，而是固定。
    public PythagorasTreeDisplay() {
        super("0", "->", "1->11", "0->1[0]0");
    }

    @Override
    public void draw(Canvas canvas) {
        String pattern = getGenerator().generate(getIterations());
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
