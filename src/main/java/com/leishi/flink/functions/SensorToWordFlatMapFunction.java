package com.leishi.flink.functions;

import com.leishi.flink.model.Sensor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class SensorToWordFlatMapFunction implements FlatMapFunction<Sensor, String> {
    @Override
    public void flatMap(Sensor value, Collector<String> out) throws Exception {
        out.collect(value.getTemp().toString());
        out.collect(value.getId());
        out.collect(value.getTimestamp().toString());
    }
}
