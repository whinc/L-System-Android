package com.whinc.widget.lsystem;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2015/11/20.
 */
public class FractalPlantDisplay extends AbsDisplay {
    private Generator mGenerator;

    public FractalPlantDisplay(Generator generator) {
        mGenerator = generator;
    }

    public FractalPlantDisplay(String axiom, String delimiter, String... rules) {
        mGenerator = new GeneratorImpl(axiom, delimiter, rules);
    }

    @Override
    public void draw(Canvas canvas) {
        String pattern = mGenerator.generate(getIterations());

        for (int i = 0; i < pattern.length(); ++i) {

        }
    }
}
