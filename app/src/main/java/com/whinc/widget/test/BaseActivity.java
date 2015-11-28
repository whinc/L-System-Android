package com.whinc.widget.test;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2015/11/28.
 */
public class BaseActivity extends AppCompatActivity{

    protected <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }
}
