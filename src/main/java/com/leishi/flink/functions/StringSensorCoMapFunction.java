package com.leishi.flink.functions;

import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

public class StringSensorCoMapFunction implements CoMapFunction<Sensor, String, Double> {
    @Override
    public Double map1(Sensor value) throws Exception {
        return value.getTemp();
    }

    @Override
    public Double map2(String value) throws Exception {
        String[] words = value.split(",");
        return Double.parseDouble(words[2]);
    }
}
