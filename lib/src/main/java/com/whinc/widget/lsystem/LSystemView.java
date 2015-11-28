package com.whinc.widget.lsystem;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.whinc.widget.lsystem.display.Display;

/**
 * Created by Administrator on 2015/11/20.
 */
public class LSystemView extends View{
    public static final String TAG = LSystemView.class.getSimpleName();

    private Display mDisplay = null;
    private float mFractionX = 0.5f;
    private float mFractionY = 0.5f;

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
        String className = typedArray.getString(R.styleable.LSystemView_ls_instance_name);
        try {
            mDisplay = (Display) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            mDisplay = new PythagorasTreeDisplay();
        }

        mDisplay.setDirection(typedArray.getFloat(R.styleable.LSystemView_ls_direction, mDisplay.getDirection()));
        mDisplay.setAngle(typedArray.getFloat(R.styleable.LSystemView_ls_angle, mDisplay.getAngle()));
        mDisplay.setStep(typedArray.getFloat(R.styleable.LSystemView_ls_step, mDisplay.getStep()));
        mDisplay.setIterations(typedArray.getInteger(R.styleable.LSystemView_ls_iterations, mDisplay.getIterations()));
        mFractionX = typedArray.getFraction(R.styleable.LSystemView_ls_position_x, 1, 1, mFractionX);
        mFractionY = typedArray.getFraction(R.styleable.LSystemView_ls_position_y, 1, 1, mFractionY);

        Paint paint = mDisplay.getPaint();
        paint.setColor(typedArray.getColor(R.styleable.LSystemView_ls_paint_color, paint.getColor()));
        mDisplay.setPaint(paint);

        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDisplay.setPosition(new PointF(w * mFractionX, h * mFractionY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDisplay != null) {
            mDisplay.draw(canvas);
        }
    }
}
