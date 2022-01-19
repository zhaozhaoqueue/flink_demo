package com.leishi.flink.api.stream.source;

import com.leishi.flink.com.leishi.flink.functions.WordToLengthMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class CollectionSourceDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // from collection
        DataStreamSource<String> dataSource = env.fromCollection(Arrays.asList("hello", "hai", "random"));

        DataStream<Integer> result = dataSource.map(new WordToLengthMapFunction());
        result.print();

        env.execute();
    }

}
