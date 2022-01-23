package com.leishi.flink.functions.rich;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;

public class TestRichMapFunction extends RichMapFunction<String, Tuple2<String, Integer>> {
    @Override
    public void open(Configuration parameters) throws Exception {
        System.out.println("init");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close");
    }

    @Override
    public Tuple2<String, Integer> map(String value) throws Exception {
        String[] words = value.split(",");
        String id = words[0];

        // 获取subtask 编号
        int indexOfThisSubtask = getRuntimeContext().getIndexOfThisSubtask();
        return new Tuple2(id, indexOfThisSubtask);

    }
}
