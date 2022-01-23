package com.leishi.flink.functions;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class WordSplitFlatMapFunction implements FlatMapFunction<String, String> {
    @Override
    public void flatMap(String value, Collector<String> out) throws Exception {
        String[] words = value.split(",");
        for (String word : words) {
            out.collect(word);
        }
    }
}
