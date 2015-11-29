package com.whinc.widget.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.whinc.widget.lsystem.LSystemView;
import com.whinc.widget.lsystem.display.Display;

public class DisplayActivity extends BaseActivity {
    private static final String EXTRA_DISPLAY = "extra_display";
    private LSystemView mLSystemView;
    private Class<? extends Display> mDisplayCls;

    public static void startActivity(@NonNull Context context, @NonNull Class<? extends Display> displayClass) {
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra(EXTRA_DISPLAY, displayClass);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        if (getIntent() == null
                || (mDisplayCls = (Class<? extends Display>) getIntent().getSerializableExtra(EXTRA_DISPLAY)) == null) {
            throw new IllegalArgumentException("Invalid argument");
        }

        mLSystemView = $(R.id.display_lsystem_view);
        mLSystemView.setDisplayClass(mDisplayCls);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
