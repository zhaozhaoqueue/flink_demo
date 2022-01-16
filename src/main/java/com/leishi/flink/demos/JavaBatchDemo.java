package com.leishi.flink.demos;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
import org.junit.Test;

public class JavaBatchDemo {

    public void countWords(String filePath) throws Exception {
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> stringDataSource = executionEnvironment.readTextFile(filePath);

        FlatMapOperator<String, Tuple2<String, Integer>> stringTuple2FlatMapOperator = stringDataSource.flatMap(new MyFlatMapFunction());
        UnsortedGrouping<Tuple2<String, Integer>> tuple2UnsortedGrouping = stringTuple2FlatMapOperator.groupBy(0);
        AggregateOperator<Tuple2<String, Integer>> sum = tuple2UnsortedGrouping.sum(1);
        sum.print();
        executionEnvironment.execute();

    }

    static class MyFlatMapFunction implements FlatMapFunction<String, Tuple2<String, Integer>> {

        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            for (String word : value.split(" ")) {
                out.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }

    @Test
    public void wordCountTest(){
        String filePath = "D:\\projects\\flink_demo\\src\\main\\resources\\words.txt";
        try {
            countWords(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
