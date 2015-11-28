package com.whinc.widget.lsystem.display;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface Display {
    /**
     * <p>Set the paint used to draw.</p>
     * @param paint
     */
    void setPaint(Paint paint);

    /**
     * <p>Set the paint used to draw.</p>
     * @return
     */
    Paint getPaint();

    /**
     * <p>Set start position of drawing.</p>
     * @param pos
     */
    void setPosition(PointF pos);
    /**
     * <p>Get start position of drawing.</p>
     * @return
     */
    PointF getPosition();

    /**
     * <p>Set draw direction.</p>
     * @param degree The degree between current direction and x-axis along the counter clockwise direction.
     */
    void setDirection(float degree);

    /**
     * <p>Get current direction.</p>
     * @return The degree between current direction and x-axis along the counter clockwise direction.
     */
    float getDirection();

    /**
     * <p>Set rotation angle.</p>
     * @param degree
     */
    void setAngle(float degree);

    /**
     * <p>Get rotation angle.</p>
     * @return
     */
    float getAngle();

    /**
     * <p>Set step length.</p>
     * @param length
     */
    void setStep(float length);

    /**
     * <p>Get step length.</p>
     * @return
     */
    float getStep();

    /**
     * <p>Set iterations of L-System.</p>
     * @param iterations
     */
    void setIterations(int iterations);

    /**
     * <p>Get iterations of L-System.</p>
     * @return
     */
    int getIterations();

    /**
     * <p>Rotate draw direction based on current direction.</p>
     * @param deltaDegree If {@code deltaDegree >= 0} rotate current direction along
     *                    the clockwise direction, otherwise opposite.
     */
    void rotateDirection(float deltaDegree);

    /**
     * <p>Save current draw direction.</p>
     */
    void saveDirection();
    /**
     * <p>Restore previous draw direction.</p>
     */
    void restoreDirection();
    /**
     * <p>Save current drawing position.</p>
     */
    void savePos();
    /**
     * <p>Restore previous drawing position.</p>
     */
    void restorePos();

    /**
     * <p>Get drawing commands.</p>
     * @return
     */
    String getPattern();

    /**
     * <p>Draw L-System on argument specified canvas.</p>
     * @param canvas canvas that will draw on it.
     */
    void draw(Canvas canvas);
}
