package com.tw.util;

public class IndentUtil {
    public static String indent(String input) {
        return input.replaceAll("(?m)^", "\t");
    }
}
