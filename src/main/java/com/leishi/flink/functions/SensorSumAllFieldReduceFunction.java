package com.leishi.flink.functions;

import com.leishi.flink.model.Sensor;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 将timestamp和temp都进行求和
 *
 */
public class SensorSumAllFieldReduceFunction implements ReduceFunction<Sensor> {
    @Override
    public Sensor reduce(Sensor value1, Sensor value2) throws Exception {
        Sensor sensor = new Sensor();
        sensor.setId(value2.getId());
        sensor.setTimestamp(value2.getTimestamp() + value1.getTimestamp());
        sensor.setTemp(value2.getTemp() + value1.getTemp());
        return sensor;
    }
}
