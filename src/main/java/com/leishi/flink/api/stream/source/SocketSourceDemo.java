package com.leishi.flink.api.stream.source;

import com.leishi.flink.functions.StringToSensorMapFunction;
import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SocketSourceDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String host = "localhost";
        int port = 6666;

        DataStreamSource<String> dataSource = env.socketTextStream(host, port);

        SingleOutputStreamOperator<Sensor> result = dataSource.map(new StringToSensorMapFunction());
        result.print();

        env.execute();
    }
}
