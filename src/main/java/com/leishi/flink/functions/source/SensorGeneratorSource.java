package com.leishi.flink.functions.source;

import com.leishi.flink.model.Sensor;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

public class SensorGeneratorSource implements SourceFunction<Sensor> {
    private boolean flag = true;
    @Override
    public void run(SourceContext<Sensor> ctx) throws Exception {
        Random random = new Random();
        while(flag){
            int id = random.nextInt(10);
            Double temp = (random.nextGaussian()) * 20 + 5;
            ctx.collect(new Sensor("sensor_" + id, System.currentTimeMillis(), temp));
            Thread.sleep(3000);
        }
    }

    @Override
    public void cancel() {
        flag = false;
    }
}
