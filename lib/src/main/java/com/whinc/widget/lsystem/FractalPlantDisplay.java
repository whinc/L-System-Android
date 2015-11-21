package com.whinc.widget.lsystem;

import android.graphics.Canvas;

import com.whinc.widget.lsystem.display.AbsDisplay;

/**
 * Created by Administrator on 2015/11/20.
 */
public class FractalPlantDisplay extends AbsDisplay {

    public FractalPlantDisplay(String axiom, String delimiter, String... rules) {
        super(axiom, delimiter, rules);
    }

    @Override
    public void draw(Canvas canvas) {
        String pattern = getGenerator().generate(getIterations());

        for (int i = 0; i < pattern.length(); ++i) {

        }
    }
}
