package com.leishi.flink.functions;

import com.leishi.flink.model.Sensor;
import org.apache.flink.api.common.functions.FilterFunction;

public class SensorIdFilterFunction implements FilterFunction<Sensor> {
    private String sensorId;
    public SensorIdFilterFunction() {
    }

    public SensorIdFilterFunction(String sensorId){
        this.sensorId = sensorId;
    }

    @Override
    public boolean filter(Sensor value) throws Exception {
        return value.getId().equals(sensorId);
    }
}
