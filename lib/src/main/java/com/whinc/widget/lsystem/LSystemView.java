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

    public LSystemView(Context context) {
        super(context);
        init(context, null);
    }

    public LSystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LSystemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LSystemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mDisplay = new PythagorasTreeDisplay();

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LSystemView);
            String className = typedArray.getString(R.styleable.LSystemView_ls_instance_name);
            try {
                mDisplay = (Display) Class.forName(className).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                mDisplay = new PythagorasTreeDisplay();
            }
            mDisplay.setAngle(typedArray.getFloat(R.styleable.LSystemView_ls_angle, mDisplay.getAngle()));
            mDisplay.setDeltaAngle(typedArray.getFloat(R.styleable.LSystemView_ls_delta_angle, mDisplay.getDeltaAngle()));
            mDisplay.setStep(typedArray.getFloat(R.styleable.LSystemView_ls_step, mDisplay.getStep()));
            mDisplay.setIterations(typedArray.getInteger(R.styleable.LSystemView_ls_iterations, mDisplay.getIterations()));
            Paint paint = mDisplay.getPaint();
            paint.setColor(typedArray.getColor(R.styleable.LSystemView_ls_paint_color, paint.getColor()));
            mDisplay.setPaint(paint);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mDisplay.setPos(new PointF(width / 2, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDisplay != null) {
            mDisplay.draw(canvas);
        }
    }
}
