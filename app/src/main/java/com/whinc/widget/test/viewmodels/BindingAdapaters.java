package com.whinc.widget.test.viewmodels;

import android.databinding.BindingAdapter;
import android.widget.EditText;

import com.whinc.util.Log;
import com.whinc.widget.lsystem.LSystemView;
import com.whinc.widget.lsystem.display.Display;

/**
 * Created by Administrator on 2015/12/9.
 */
public class BindingAdapaters {

    public static final String TAG = BindingAdapaters.class.getSimpleName();

    @BindingAdapter("text")
    public static void bindEditText(EditText edt, Character text) {
        if (!edt.getText().toString().equals(text.toString())) {
            edt.setText(text);
        }
        Log.i(TAG, "bindEditText() called");
    }

    @BindingAdapter("display")
    public static void setDisplay(LSystemView view, Display display) {
        view.setDisplay(display);
    }
}
