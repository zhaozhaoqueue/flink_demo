package com.leishi.flink.api.stream.udf;

import com.leishi.flink.functions.rich.TestRichMapFunction;
import com.leishi.flink.model.Sensor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class UDFFunctionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("sensor.txt").getPath();
        DataStream<String> dataSource = env.readTextFile(filePath, "UTF-8");

        SingleOutputStreamOperator<Tuple2<String, Integer>> richMapped = dataSource.map(new TestRichMapFunction());
        richMapped.print("rich map");

        env.execute();
    }
}
