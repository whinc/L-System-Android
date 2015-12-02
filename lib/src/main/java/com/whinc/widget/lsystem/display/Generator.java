package com.whinc.widget.lsystem.display;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface Generator extends Serializable {
    String generate(int iterations);
    String getAxiom();
    String getDelimiter();
    Map<Character, String> getRules();
}
