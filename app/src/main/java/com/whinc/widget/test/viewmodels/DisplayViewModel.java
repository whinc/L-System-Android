package com.whinc.widget.test.viewmodels;

import android.databinding.ObservableInt;
import android.databinding.adapters.TextViewBindingAdapter;
import android.text.Editable;
import android.view.View;

import com.whinc.util.Log;
import com.whinc.widget.lsystem.display.Display;
import com.whinc.widget.lsystem.display.Generator;

import java.util.Map;

/**
 * Created by Administrator on 2015/12/9.
 */
public class DisplayViewModel {
    public static final String TAG = DisplayViewModel.class.getSimpleName();
    private final Display mDisplay;
    public final ObservableInt color = new ObservableInt();
    public View.OnClickListener applyBtnClickHandler;
    public View.OnClickListener cancelBtnClickHandler;

    public DisplayViewModel(Display display) {
        mDisplay = display;
        color.set(display.getColor());
    }

    public Display getDisplay() {
        return mDisplay;
    }

    public String getRuleString() {
        Generator generator = mDisplay.getGenerator();
        Map<Character, String> rules = generator.getRules();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Character, String> v : rules.entrySet()) {
            builder.append(v.getKey())
                    .append(generator.getDelimiter())
                    .append(v.getValue())
                    .append(";");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    // On color EditText changed
    public TextViewBindingAdapter.AfterTextChanged afterTextChanged = new TextViewBindingAdapter.AfterTextChanged() {
        @Override
        public void afterTextChanged(Editable editable) {
            try {
                int _color = (int) Long.parseLong(editable.toString().trim(), 16);
                _color |= 0xFF000000;
                if (_color != color.get()) {
                    color.set(_color);      // update color on ui
                    mDisplay.setColor(_color);  // update color value in display
                }
            } catch (NumberFormatException e) {
                Log.e(TAG, "", e);
            }
        }
    };
}
