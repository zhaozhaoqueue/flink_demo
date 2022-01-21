package com.leishi.flink.functions;

import com.leishi.flink.model.Sensor;
import org.apache.flink.api.common.functions.MapFunction;

public class StringToSensorMapFunction implements MapFunction<String, Sensor> {
    @Override
    public Sensor map(String value) throws Exception {
        String[] parts = value.split(",");
        String id = parts[0];
        Long timestamp = null;
        Double temp = null;
        try {
            timestamp = Long.parseLong(parts[1]);
            temp = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return new Sensor(id, timestamp, temp);
    }
}
