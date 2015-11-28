package com.whinc.widget.lsystem.display;

import java.util.Map;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface Generator {
    String generate(int iterations);
    String getAxiom();
    String getDelimiter();
    Map<Character, String> getRules();
}
