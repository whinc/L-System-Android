package com.whinc.widget.lsystem.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/20.
 * <p>L-System pattern generator</p>
 */
class GeneratorImpl implements Generator {
    private final String mAxiom;
    private final String mDelimiter;
    private final Map<Character, String> mRules = new HashMap<>();
    private final List<String> mPatternCache = new ArrayList<>();

    public GeneratorImpl(String axiom, String delimiter, String... rules) {
        mAxiom = axiom.trim();
        mDelimiter = delimiter.trim();

        for (String rule : rules) {
            String[] twoString = rule.split(delimiter);
            if (twoString.length != 2) {
                throw new IllegalArgumentException(rule + " cannot be split to two sub string");
            } else {
                String left = twoString[0].trim();
                String right = twoString[1].trim();
                if (left.length() != 1) {
                    throw new IllegalArgumentException(rule + " must have only one character on left of '" + delimiter + "'");
                }
            }
            mRules.put(twoString[0].trim().charAt(0), twoString[1].trim());
        }
    }

    private String getPattern(int index) {
        if (index < mPatternCache.size()
                && mPatternCache.get(index) != null
                && !mPatternCache.get(index).isEmpty()) {
            System.out.println("hit cache at " + index);
            return mPatternCache.get(index);
        } else {
            return null;
        }
    }

    public String generate(int iterations) {
        if (iterations <= 0) {
            return mAxiom;
        }

        if (mPatternCache.isEmpty()) {
            mPatternCache.add(mAxiom);
        }

        String s = getPattern(iterations);
        if (s != null) {
            return s;
        }

        StringBuilder builder = new StringBuilder();
        // generate from last cached production
        for (int i = mPatternCache.size(); i <= iterations; ++i) {
            String axiom = mPatternCache.get(mPatternCache.size() - 1);

            builder.delete(0, builder.length());
            for (int k = 0; k < axiom.length(); ++k) {
                char c = axiom.charAt(k);
                String pattern = findMatchPattern(c);
                builder.append(pattern != null ? pattern : c);
            }
            mPatternCache.add(i, builder.toString());
        }
        return builder.toString();
    }

    @Override
    public String getAxiom() {
        return mAxiom;
    }

    @Override
    public String getDelimiter() {
        return mDelimiter;
    }

    @Override
    public Map<Character, String> getRules() {
        return mRules;
    }

    /**
     * <p>Find matched rule and return the matched pattern.</p>
     */
    private String findMatchPattern(Character c) {
        for (Character v : mRules.keySet()) {
            if (v.equals(c)) {
                return mRules.get(v);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("axiom:\n");
        builder.append(String.format("\t%s\n", mAxiom));

        builder.append("rules:\n");
        for (Map.Entry<Character, String> entry : mRules.entrySet()) {
            builder.append(String.format("\t%s-%s\n", entry.getKey(), entry.getValue()));
        }

        builder.append("cache:\n");
        for (int i = 0; i < mPatternCache.size(); ++i) {
            builder.append(String.format("\t%dth %s\n", i, mPatternCache.get(i)));
        }
        return builder.toString();
    }
}
