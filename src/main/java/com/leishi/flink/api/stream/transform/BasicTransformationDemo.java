package com.leishi.flink.api.stream.transform;

import com.leishi.flink.functions.*;
import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class BasicTransformationDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataSource = env.socketTextStream("localhost", 6666);

        SingleOutputStreamOperator<Sensor> mapped = dataSource.map(new StringToSensorMapFunction());
        SingleOutputStreamOperator<Sensor> filtered = mapped.filter(new SensorIdFilterFunction("sensor_1"));
        filtered.print();

        SingleOutputStreamOperator<String> flatMapped = filtered.flatMap(new SensorToWordFlatMapFunction());
        flatMapped.print();

        env.execute();
    }
}
