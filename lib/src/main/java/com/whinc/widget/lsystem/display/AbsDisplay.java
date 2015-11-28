package com.whinc.widget.lsystem.display;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public abstract class AbsDisplay implements Display{
    private final Generator mGenerator;
    private float mDirection = -90.0f;
    private float mAngle = 0.0f;
    private PointF mPosition = new PointF(0, 0);
    private float mStep = 10.0f;
    private int mIterations = 1;
    private List<Float> mDirectionStack = new LinkedList<>();
    private List<PointF> mPositionStack = new LinkedList<>();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public AbsDisplay(String axiom, String delimiter, String... rules) {
        mGenerator = new GeneratorImpl(axiom, delimiter, rules);
    }

    @Override
    public String getPattern() {
        return mGenerator.generate(mIterations);
    }

    @Override
    public float getAngle() {
        return mAngle;
    }

    @Override
    public void setAngle(float angle) {
        mAngle = angle;
    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    @Override
    public PointF getPosition() {
        return mPosition;
    }

    @Override
    public void setPosition(PointF pos) {
        mPosition = pos;
    }

    @Override
    public float getDirection() {
        return mDirection;
    }

    @Override
    public void setDirection(float angle) {
        this.mDirection = angle;
    }

    @Override
    public void rotateDirection(float deltaDegree) {
        mDirection -= deltaDegree;
    }

    @Override
    public void saveDirection() {
        mDirectionStack.add(mDirection);
    }

    @Override
    public void restoreDirection() {
        if (mDirectionStack.isEmpty()) {
            throw new IllegalStateException("stack is empty! push and pop operation don't match");
        }
        mDirection = mDirectionStack.remove(mDirectionStack.size() - 1);
    }

    @Override
    public void savePos() {
        mPositionStack.add(mPosition);
    }

    public void restorePos() {
        if (mPositionStack.isEmpty()) {
            throw new IllegalStateException("stack is empty! push and pop operation don't match");
        }
        mPosition = mPositionStack.remove(mPositionStack.size() - 1);
    }

    @Override
    public float getStep() {
        return mStep;
    }

    @Override
    public void setStep(float length) {
        mStep = Math.max(0.0f, length);
    }

    @Override
    public int getIterations() {
        return mIterations;
    }

    @Override
    public void setIterations(int iterations) {
        mIterations = Math.max(0, iterations);
    }

    public abstract void draw(Canvas canvas);
}
