package com.leishi.flink.functions;

import org.apache.flink.api.common.functions.FilterFunction;

public class WordLengthFilter implements FilterFunction<String> {
    private int bottom;
    public WordLengthFilter(){}

    public WordLengthFilter(int bottom){
        this.bottom = bottom;
    }

    @Override
    public boolean filter(String value) throws Exception {
        return value.length() >= bottom;
    }
}
