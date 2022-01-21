package com.leishi.flink.api.stream.source;

import com.leishi.flink.functions.source.SensorGeneratorSource;
import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CustomizedSourceDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Sensor> dataSource = env.addSource(new SensorGeneratorSource());

        dataSource.print();
        env.execute();
    }
}
