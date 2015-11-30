package com.whinc.widget.lsystem.display;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface Display {

    void setFractionPosX(float fraction);
    float getFractionPosX();
    void setFractionPosY(float fraction);
    float getFractionPosY();

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
     * <p>Get L-System pattern generator.</p>
     * @return
     */
    Generator getGenerator();

    /**
     * <p>Set L-System pattern generator.</p>
     * @param generator
     */
    void setGenerator(Generator generator);

    /**
     * <p>Get L-System pattern on current situation.</p>
     * @return
     */
    String getPattern();

    /**
     * <p>Draw L-System on argument specified canvas.</p>
     * @param canvas canvas that will draw on it.
     */
    void draw(@NonNull Canvas canvas, @NonNull Paint paint);
}
