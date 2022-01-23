package com.leishi.flink.api.stream.partition;

import com.leishi.flink.functions.StringToSensorMapFunction;
import com.leishi.flink.functions.selectors.key.SensorIdKeySelector;
import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class PartitionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        String filePath = Thread.currentThread().getContextClassLoader().getResource("sensor.txt").getPath();
        DataStream<String> dataSource = env.readTextFile(filePath, "UTF-8");

        SingleOutputStreamOperator<Sensor> sensorStream = dataSource.map(new StringToSensorMapFunction());
        dataSource.print("data source");

        DataStream<Sensor> shuffle = sensorStream.shuffle();
        shuffle.print("shuffle");

        KeyedStream<Sensor, String> keyby = sensorStream.keyBy(new SensorIdKeySelector());
        keyby.print("keyby");

        DataStream<Sensor> global = sensorStream.global();
        global.print("global");

        env.execute();
    }
}
