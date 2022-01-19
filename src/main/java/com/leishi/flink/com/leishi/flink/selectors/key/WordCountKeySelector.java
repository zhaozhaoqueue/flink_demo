package com.leishi.flink.com.leishi.flink.selectors.key;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;

public class WordCountKeySelector implements KeySelector<Tuple2<String, Integer>, String> {

    @Override
    public String getKey(Tuple2<String, Integer> value) throws Exception {
        return value.f0;
    }
}
