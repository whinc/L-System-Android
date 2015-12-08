package com.whinc.widget.lsystem.display;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/20.
 */
public abstract class AbsDisplay implements Display {
    private Generator mGenerator;
    private float mDirection = -90.0f;
    private float mAngle = 45.0f;
    private float mFractionPosX = 0.5f;
    private float mFractionPosY = 0.5f;
    private float mStep = 10.0f;
    private int mIterations = 1;
    /* Don't serialize these fields */
    private transient List<Float> mDirectionStack;
    private transient List<Float> mFractionPosXStack;
    private transient List<Float> mFractionPosYStack;
    private transient List mState;
    private transient Paint mPaint;

    public AbsDisplay(String axiom, String delimiter, String... rules) {
        mGenerator = new GeneratorImpl(axiom, delimiter, rules);
        init();
    }

    private void init() {
        mDirectionStack = new LinkedList<>();
        mFractionPosXStack = new LinkedList<>();
        mFractionPosYStack = new LinkedList<>();
        mState = new LinkedList();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(getColor());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        init();
        setColor(in.readInt());
    }

    /**
     * <P>Save state</P>
     */
    private void storeState() {
        mState.clear();
        mState.add(mDirection);
        mState.add(mAngle);
        mState.add(mFractionPosX);
        mState.add(mFractionPosY);
    }

    /**
     * <P>restore state</P>
     */
    private void restoreState() {
        mDirection = (float) mState.remove(0);
        mAngle = (float) mState.remove(0);
        mFractionPosX = (float) mState.remove(0);
        mFractionPosY = (float) mState.remove(0);
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
    public void setColor(int color) {
        mPaint.setColor(color);
    }

    @Override
    public int getColor() {
        return mPaint.getColor();
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
    public void setPercentX(float fraction) {
        mFractionPosX = fraction;
    }

    @Override
    public float getPercentX() {
        return mFractionPosX;
    }

    @Override
    public void setPercentY(float fraction) {
        mFractionPosY = fraction;
    }

    @Override
    public float getPercentY() {
        return mFractionPosY;
    }

    @Override
    public float getDirection() {
        return mDirection;
    }

    @Override
    public void setDirection(float degree) {
        this.mDirection = degree;
    }

    @Override
    public void rotateDirection(float deltaDegree) {
        mDirection += deltaDegree;
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
        mFractionPosXStack.add(mFractionPosX);
        mFractionPosYStack.add(mFractionPosY);
    }

    public void restorePos() {
        if (mFractionPosXStack.isEmpty() || mFractionPosYStack.isEmpty()) {
            throw new IllegalStateException("stack is empty! push and pop operation don't match");
        }
        mFractionPosX = mFractionPosXStack.remove(mFractionPosXStack.size() - 1);
        mFractionPosY = mFractionPosYStack.remove(mFractionPosYStack.size() - 1);
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

    public void draw(Canvas canvas) {
        storeState();
        drawContent(canvas, mPaint);
        restoreState();
    }

    public abstract void drawContent(Canvas canvas, Paint paint);

    @Override
    public String toString() {
        return String.format("direction:%f, angle:%f, pos:(%f, %f), step:%f, iterations:%d",
                mDirection, mAngle, mFractionPosX, mFractionPosY, mStep, mIterations);
    }
}
