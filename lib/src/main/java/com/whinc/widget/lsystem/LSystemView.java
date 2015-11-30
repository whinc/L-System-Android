package com.whinc.widget.lsystem;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.whinc.widget.lsystem.display.Display;

/**
 * Created by Administrator on 2015/11/20.
 */
public class LSystemView extends View{
    public static final String TAG = LSystemView.class.getSimpleName();

    private Display mDisplay = null;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LSystemView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public LSystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public LSystemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LSystemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LSystemView);

        // Instantiate Display
        String className = typedArray.getString(R.styleable.LSystemView_ls_display_class);
            try {
                if (TextUtils.isEmpty(className)) {
                    mDisplay = PythagorasTreeDisplay.class.newInstance();
                } else {
                    mDisplay = (Display) Class.forName(className).newInstance();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        /* Display properties */
        mDisplay.setDirection(typedArray.getFloat(R.styleable.LSystemView_ls_direction, mDisplay.getDirection()));
        mDisplay.setAngle(typedArray.getFloat(R.styleable.LSystemView_ls_angle, mDisplay.getAngle()));
        mDisplay.setStep(typedArray.getFloat(R.styleable.LSystemView_ls_step, mDisplay.getStep()));
        mDisplay.setIterations(typedArray.getInteger(R.styleable.LSystemView_ls_iterations, mDisplay.getIterations()));
        mDisplay.setFractionPosX(typedArray.getFraction(R.styleable.LSystemView_ls_position_x, 1, 1, mDisplay.getFractionPosX()));
        mDisplay.setFractionPosY(typedArray.getFraction(R.styleable.LSystemView_ls_position_y, 1, 1, mDisplay.getFractionPosY()));

        /* Paint properties */
        mPaint.setColor(typedArray.getColor(R.styleable.LSystemView_ls_paint_color, mPaint.getColor()));

        typedArray.recycle();
    }

    public float getDirection() {
        return mDisplay.getDirection();
    }

    public void setDirection(float degree) {
        mDisplay.setDirection(degree);
    }

    public float getAngle() {
        return mDisplay.getAngle();
    }

    public void setAngle(float degree) {
        mDisplay.setAngle(degree);
    }

    public float getStep() {
        return mDisplay.getStep();
    }

    public void setStep(float length) {
        mDisplay.setStep(length);
    }

    public int getIterations() {
        return mDisplay.getIterations();
    }

    public void setIterations(int n) {
        mDisplay.setIterations(n);
    }

    public void setFractionPosX(float x) {
        mDisplay.setFractionPosX(x);
    }

    public float getPositionX() {
        return mDisplay.getFractionPosX() * getMeasuredWidth();
    }

    public void setFractionPosY(float y) {
        mDisplay.setFractionPosY(y);
    }

    public float getPositionY() {
        return mDisplay.getFractionPosY() * getMeasuredHeight();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public int getColor() {
        return mPaint.getColor();
    }

    public boolean setDisplayClass(@NonNull Class<? extends Display> cls) {
        boolean r = true;
        try {
            Display display = cls.newInstance();
            mDisplay = display;
            invalidate();
        } catch (InstantiationException e) {
            e.printStackTrace();
            r = false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            r = false;
        }

        return r;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDisplay != null) {
            mDisplay.draw(canvas, mPaint);
        }
    }
}
