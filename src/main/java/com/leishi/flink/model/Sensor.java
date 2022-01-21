package com.leishi.flink.model;

public class Sensor {
    private String id;
    private Long timestamp;
    private Double temp;

    public Sensor(){
    };

    public Sensor(String id, Long timestamp, Double temp){
        setId(id);
        setTimestamp(timestamp);
        setTemp(temp);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", temp=" + temp +
                '}';
    }
}
