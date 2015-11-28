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
    private Generator mGenerator;
    private float mDirection = -90.0f;
    private float mAngle = 0.0f;
    private PointF mFractionPos = new PointF(0.5f, 0.5f);
    private float mStep = 10.0f;
    private int mIterations = 1;
    private List<Float> mDirectionStack = new LinkedList<>();
    private List<PointF> mPositionStack = new LinkedList<>();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public AbsDisplay(String axiom, String delimiter, String... rules) {
        mGenerator = new GeneratorImpl(axiom, delimiter, rules);
    }

    @Override
    public Generator getGenerator() {
        return mGenerator;
    }

    @Override
    public void setGenerator(Generator generator) {
        mGenerator = generator;
    }

    @Override
    public String getPattern() {
        if (mGenerator == null) {
            return "";
        }

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
    public PointF getFractionPos() {
        return mFractionPos;
    }

    @Override
    public void setFractionPos(PointF fractionPos) {
        mFractionPos = fractionPos;
    }

    @Override
    public void setPosition(float w, float h, PointF pos) {
        mFractionPos.x = pos.x / w;
        mFractionPos.y = pos.y / h;
    }

    @Override
    public PointF getPosition(float w, float h) {
        return new PointF(mFractionPos.x * w, mFractionPos.y * h);
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
        mPositionStack.add(mFractionPos);
    }

    public void restorePos() {
        if (mPositionStack.isEmpty()) {
            throw new IllegalStateException("stack is empty! push and pop operation don't match");
        }
        mFractionPos = mPositionStack.remove(mPositionStack.size() - 1);
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
