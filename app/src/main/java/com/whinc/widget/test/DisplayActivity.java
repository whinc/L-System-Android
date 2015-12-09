package com.whinc.widget.test;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewStubProxy;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;

import com.whinc.widget.lsystem.display.Display;
import com.whinc.widget.test.databinding.ActivityDisplayBinding;
import com.whinc.widget.test.databinding.DialogDisplaySettingBinding;
import com.whinc.widget.test.viewmodels.DisplayViewModel;

public class DisplayActivity extends BaseActivity {
    private static final String EXTRA_DISPLAY = "extra_display";
    public static final String TAG = DisplayActivity.class.getSimpleName();
    private ActivityDisplayBinding mBinding;

    public static void startActivity(@NonNull Context context, @NonNull Display display) {
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra(EXTRA_DISPLAY, display);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_display);

        Display display;
        if (getIntent() == null
                || (display = (Display) getIntent().getSerializableExtra(EXTRA_DISPLAY)) == null) {
            throw new IllegalArgumentException("Invalid argument");
        }

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // create ViewModel
        final DisplayViewModel displayModel = new DisplayViewModel(display);
        displayModel.applyBtnClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.lsystemView.invalidate();
                InputMethodManager manager = (InputMethodManager) DisplayActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        };
        displayModel.cancelBtnClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewStubProxy viewStubProxy = (ViewStubProxy)(Object)mBinding.viewStub;
                viewStubProxy.getRoot().setVisibility(View.GONE);
            }
        };

        mBinding.setDisplayModel(displayModel);
        mBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                DialogDisplaySettingBinding binding = DataBindingUtil.bind(inflated);
                binding.setDisplayModel(mBinding.getDisplayModel());
            }
        });
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
        if (id == R.id.action_setting) {
            ViewStubProxy viewStubProxy = (ViewStubProxy)(Object)mBinding.viewStub;
            if (!viewStubProxy.isInflated()) {
                viewStubProxy.getViewStub().inflate();
            } else {
                viewStubProxy.getRoot().setVisibility(View.VISIBLE);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
