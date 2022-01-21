package com.leishi.flink.api.stream.source;

import com.leishi.flink.functions.LineSplitFlatMapFunction;
import com.leishi.flink.functions.selectors.key.WordCountKeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FileSourceDemo {
    public static void main(String[] args) throws Exception {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("words.txt").getPath();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> dataStream = env.readTextFile(filePath, "UTF-8");

        SingleOutputStreamOperator<Tuple2<String, Integer>> wordCountTuple = dataStream.flatMap(new LineSplitFlatMapFunction());
        KeyedStream<Tuple2<String, Integer>, String> keyedStream = wordCountTuple.keyBy(new WordCountKeySelector());
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = keyedStream.sum(1);
        result.print();
        env.execute();
    }
}
