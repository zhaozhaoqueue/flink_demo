package com.leishi.flink.functions.selectors.key;

import com.leishi.flink.model.Sensor;
import org.apache.flink.api.java.functions.KeySelector;

public class SensorIdKeySelector implements KeySelector<Sensor, String> {
    @Override
    public String getKey(Sensor value) throws Exception {
        return value.getId();
    }
}
