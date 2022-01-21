package com.leishi.flink.functions;

import org.apache.flink.api.common.functions.MapFunction;

public class WordToLengthMapFunction implements MapFunction<String, Integer> {
    @Override
    public Integer map(String value) throws Exception {
        return value.length();
    }
}
