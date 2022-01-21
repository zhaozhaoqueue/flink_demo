package com.leishi.flink.functions;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class LineSplitFlatMapFunction implements FlatMapFunction<String, Tuple2<String, Integer>> {
    private String splitCharacter;

    public LineSplitFlatMapFunction(){
        this.splitCharacter = " ";
    }

    public LineSplitFlatMapFunction(String splitCharacter){
        this.splitCharacter = splitCharacter;
    }
    @Override
    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
        String[] words = value.split(splitCharacter);
        for (String word : words) {
            out.collect(new Tuple2<>(word, 1));
        }
    }
}
