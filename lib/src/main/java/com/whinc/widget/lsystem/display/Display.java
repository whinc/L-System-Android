package com.whinc.widget.lsystem.display;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface Display {
    /* Getter and setter of draw paint */
    void setPaint(Paint paint);
    Paint getPaint();

    /* Getter and setter of draw start position */
    void setPos(PointF pos);
    PointF getPos();

    /* Draw direction */
    void setAngle(float degree);
    float getAngle();

    void setDeltaAngle(float degree);
    float getDeltaAngle();

    /* Getter and setter of draw step length which determine how long the line segment */
    void setStep(float length);
    float getStep();

    /* Getter and setter of L-System iterations.*/
    void setIterations(int iterations);
    int getIterations();

    /**
     * <p>Rotate line draw direction</p>
     * @param degree rotation degree. If degree >= 0 rotate right, else rotate left.
     */
    void rotateAngle(float degree);

    /**
     * <p>Save current draw direction.</p>
     */
    void pushAngle();
    /**
     * <p>Restore previous draw direction.</p>
     */
    void popAngle();
    /**
     * <p>Save current draw start position.</p>
     */
    void pushPos();
    /**
     * <p>Restore previous draw start position.</p>
     */
    void popPos();

    /**
     * <p>Get L-System pattern generator</p>
     * @return
     */
    Generator getGenerator();

    /**
     * <p>Draw L-System on argument specified canvas.</p>
     * @param canvas canvas that will draw on it.
     */
    void draw(Canvas canvas);
}
