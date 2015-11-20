package com.whinc.widget.lsystem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public abstract class AbsDisplay implements Display{
    private float mAngle = -90.0f;
    private float mDeltaAngle = 0.0f;
    private PointF mPos = new PointF(0, 0);
    private float mStep = 10.0f;
    private int mIterations = 1;
    private List<Float> mAngleStack = new LinkedList<>();
    private List<PointF> mPosStack = new LinkedList<>();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public PointF getPos() {
        return mPos;
    }

    public void setPos(PointF pos) {
        mPos = pos;
    }

    /** In degree */
    public float getAngle() {
        return mAngle;
    }

    /** In degree */
    public void setAngle(float angle) {
        this.mAngle = angle;
    }

    /**
     * <p>Rotate current angle with specified degree </p>
     * @param delta rotated degree. If delta < 0 rotate left, otherwise rotate right
     */
    public void rotateAngle(float delta) {
        mAngle -= delta;
    }

    public void pushAngle() {
        mAngleStack.add(mAngle);
    }

    public void popAngle() {
        if (mAngleStack.isEmpty()) {
            throw new IllegalStateException("stack is empty! push and pop operation don't match");
        }
        mAngle = mAngleStack.remove(mAngleStack.size() - 1);
    }

    public void pushPos() {
        mPosStack.add(mPos);
    }

    public void popPos() {
        if (mPosStack.isEmpty()) {
            throw new IllegalStateException("stack is empty! push and pop operation don't match");
        }
        mPos = mPosStack.remove(mPosStack.size() - 1);
    }

    @Override
    public void setStep(float length) {
        mStep = Math.max(0.0f, length);
    }

    @Override
    public float getStep() {
        return mStep;
    }

    @Override
    public void setIterations(int iterations) {
        mIterations = Math.max(0, iterations);
    }

    @Override
    public int getIterations() {
        return mIterations;
    }

    @Override
    public void setDeltaAngle(float degree) {
        mDeltaAngle = degree;
    }

    @Override
    public float getDeltaAngle() {
        return mDeltaAngle;
    }

    public abstract void draw(Canvas canvas);
}
