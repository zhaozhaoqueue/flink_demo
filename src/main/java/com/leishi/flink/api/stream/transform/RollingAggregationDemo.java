package com.leishi.flink.api.stream.transform;

import com.leishi.flink.functions.SensorSumAllFieldReduceFunction;
import com.leishi.flink.functions.StringToSensorMapFunction;
import com.leishi.flink.functions.selectors.key.SensorIdKeySelector;
import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class RollingAggregationDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        DataStreamSource<String> dataSource = env.socketTextStream("localhost", 6666);

        String filePath1 = Thread.currentThread().getContextClassLoader().getResource("sensor.txt").getPath();
        DataStream<String> dataSource = env.readTextFile(filePath1, "UTF-8");

        DataStream<Sensor> mapped = dataSource.map(new StringToSensorMapFunction());
        KeyedStream<Sensor, String> keyedStream = mapped.keyBy(new SensorIdKeySelector());

        /**
         * sum temp
         */
        SingleOutputStreamOperator<Sensor> sum = keyedStream.sum("temp");
        sum.print("sum");
        /**
         * min and minby temp
         */
        SingleOutputStreamOperator<Sensor> min = keyedStream.min("temp");
        SingleOutputStreamOperator<Sensor> minBy = keyedStream.minBy("temp");
        min.print("min");
        minBy.print("min by");

        /**
         * minby and maxby temp
         */
        SingleOutputStreamOperator<Sensor> max = keyedStream.max("temp");
        SingleOutputStreamOperator<Sensor> maxBy = keyedStream.maxBy("temp");
        max.print("max");
        maxBy.print("max by");


        /**
         * sum temp and timestamp together
         */
        SingleOutputStreamOperator<Sensor> reduced = keyedStream.reduce(new SensorSumAllFieldReduceFunction());

        reduced.print("reduced");

        env.execute();

    }
}
