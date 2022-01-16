package com.leishi.flink.demos

import org.apache.flink.api.scala._

object ScalaBatchDemo {
    def main(args: Array[String]): Unit = {
        val environment = ExecutionEnvironment.getExecutionEnvironment
        val dataSet = environment.readTextFile("D:\\projects\\flink_demo\\src\\main\\resources\\words.txt")
        val result = dataSet.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)
        result.print

        environment.execute()
    }
}
