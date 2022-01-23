package com.leishi.flink.api.stream.sink;

import com.leishi.flink.functions.WordSplitFlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

public class KafkaSinkDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("words.txt").getPath();
        DataStream<String> dataSource = env.readTextFile(filePath, "UTF-8");

        SingleOutputStreamOperator<String> wordStream = dataSource.flatMap(new WordSplitFlatMapFunction());
//        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers", "localhost:9092");
//        properties.setProperty("group.id", "flink-sink-test");
//        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("auto.offset.reset", "latest");


        DataStreamSink<String> stringDataStreamSink = wordStream
                .global()
                .addSink(new FlinkKafkaProducer<String>("localhost:9092", "flin-sink-test", new SimpleStringSchema()));

        env.execute();

    }
}
