package com.whinc.widget.lsystem.display;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface Display extends Serializable{

    /**
     * <p>Set percentage of x position on canvas.</p>
     * @param fraction [0.0f, 1.0f]
     */
    void setPercentX(float fraction);
    float getPercentX();

    /**
     * <p>Set percentage of y position on canvas.</p>
     * @param fraction [0.0f, 1.0f]
     */
    void setPercentY(float fraction);
    float getPercentY();

    /**
     * <p>Set draw direction.</p>
     * @param degree The degree between current direction and x-axis along the counter clockwise direction.
     */
    void setDirection(float degree);
    float getDirection();

    /**
     * <p>Set rotation angle.</p>
     * @param degree
     */
    void setAngle(float degree);
    float getAngle();

    /**
     * <p>Set step length.</p>
     * @param length
     */
    void setStep(float length);
    float getStep();

    /**
     * <p>Set iterations of L-System.</p>
     * @param iterations
     */
    void setIterations(int iterations);
    int getIterations();

    /**
     * <p>Set the color of line segment.</p>
     * @param color argb format
     */
    void setColor(int color);
    int getColor();

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
    void draw(@NonNull Canvas canvas);
}
