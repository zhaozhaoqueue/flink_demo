package com.leishi.flink.api.stream.transform;

import com.leishi.flink.functions.StringSensorCoMapFunction;
import com.leishi.flink.functions.StringToSensorMapFunction;
import com.leishi.flink.functions.selectors.key.SensorIdKeySelector;
import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MultiStreamDemo {
    public static void main(String[] args) throws Exception {
        String filePath1 = Thread.currentThread().getContextClassLoader().getResource("sensor.txt").getPath();
        String filePath2= Thread.currentThread().getContextClassLoader().getResource("sensor2.txt").getPath();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStream<String> dataSource1 = env.readTextFile(filePath1, "UTF-8");
        DataStreamSource<String> dataSource2 = env.readTextFile(filePath2, "UTF-8");

        // split and select are cancelled in new API

        SingleOutputStreamOperator<Sensor> map1 = dataSource1.map(new StringToSensorMapFunction());

        map1.print("map1");
        dataSource2.print("data source 2");

        // connect and comap
        ConnectedStreams<Sensor, String> connect = map1.connect(dataSource2);
        SingleOutputStreamOperator<Double> coMapped = connect.map(new StringSensorCoMapFunction());

        coMapped.print("co map");

        // union
        SingleOutputStreamOperator<Sensor> union = dataSource1.union(dataSource2).map(new StringToSensorMapFunction());
        union.print("union");

        env.execute();
    }
}
