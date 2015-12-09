package com.whinc.widget.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.whinc.widget.lsystem.FractalPlantDisplay;
import com.whinc.widget.lsystem.PythagorasTreeDisplay;
import com.whinc.widget.lsystem.display.Display;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                DisplayActivity.startActivity(MainActivity.this);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Display display = new PythagorasTreeDisplay();
            DisplayActivity.startActivity(this, display);
            return true;
        } else if (id == R.id.action_2) {
            Display display = new FractalPlantDisplay();
            display.setAngle(20);
            display.setIterations(4);
            display.setColor(Color.argb(0xFF, 0x00, 0xA0, 0x00));
            DisplayActivity.startActivity(this, display);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
