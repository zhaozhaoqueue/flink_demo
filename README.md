# Learning Flink
### Batch processing demos
- [x] Word Count
### Stream processing demos
- [x] Word Count
### Deployment
#### Standalone
#### Flink on yarn
##### Session cluster
##### Per job cluster
### Structure and principal
#### Units
#### Job submission
#### Job schedule 
##### Parallelism

### API
#### Supported Data Type
- Flink流应用程序处理的是以数据对象表示的事件流。所以在Flink内部，我们需要能够处理这些对象。它们需要被序列化和反序列化，以便通过网络传送它们；或者从状态后端、检查点和保存点读取它们。为了有效地做到这一点，Flink需要明确知道应用程序所处理的数据类型。Flink使用类型信息的概念来表示数据类型，并为每个数据类型生成特定的序列化器、反序列化器和比较器。
- Flink还具有一个类型提取系统，该系统分析函数的输入和返回类型，以自动获取类型信息，从而获得序列化器和反序列化器。但是，在某些情况下，例如lambda函数或泛型类型，需要显式地提供类型信息，才能使应用程序正常工作或提高其性能。
- scala 和 java 的基础数据类型以及String
- java （flink提供的） 和 scala 的 Tuple
- scala case class
- java POJO: 必须提供无参构造方法，成员变量必须public或private提供get和set方法
- 其他：ArrayList，HashMap，Enum等
#### Create environment
- getExecutionEnvironment <br>
The returned environment is determined by the running environment;
- createLocalEnvironment <br/>
Get a local local environment, parallelism must be set;
- createRemoteEnvironment <br/>
 *to be continued*
#### Source
- [X] From collection
- [X] From file
- [X] From Kafka: consumer
- zookeeper: zookeeper-server-start.sh
- kafka server: kafka-server-start.sh -daemon config/server.properties
- producer： kafka-console-producer.sh --broker-list localhost:9092 --topic flink-test
- consumer: kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic flink-test
- [X] From socket
  - socket: nc -lk 6666
  - Mostly used for testing
- [X] From customized source
#### Transformation
- [X] Basic operators (map, flatMap, filter ...)
- [X] Rolling aggregation operators (keyby, sum, min, max, minby, maxby, reduce)
  - Aggregation operators are based on the keyed stream.
  - min/max and minBy/maxBy: min/max only update the selected field, minBy/maxBy will update the whole record
- [X] Multiple streams (split, select, connect and comap, union)
  - split and select are cancelled in new API, consider using side output
  - connect can join two streams with different data type, but only can join **two** streams
  - union can join multiple streams with same data type
### UDF functions
- [X] Basic function classes
- [X] Rich functions
  - 可以获取运行环境的上下文，并拥有一些生命周期方法，所以可以实现更复杂的功能。
  - 有生命周期的概念
    - open()方法是rich function的初始化方法，当一个算子例如map或者filter被调用之前open()会被调用。
    - close()方法是生命周期中的最后一个调用的方法，做一些清理工作。
    - getRuntimeContext()方法提供了函数的RuntimeContext的一些信息，例如函数执行的并行度，任务的名字，以及state状态
### Partition operator
- [X] shuffle: uniformly randomly send data to partition while getting one record
- [X] keyby: hash
- [X] global: send all data to the first partition
#### Sink
- [X] To kafka: producer
- [ ] To redis
- [ ] To JDBC
#### Window
##### Time window
##### Count window
##### Session window
##### Window functions
- [ ] Incremental window function
- [ ] Full window function
#### Other API
- [ ] trigger
- [ ] evitor 
- [ ] allowedLateness
- [ ] sideOutputLateData
- [ ] getSideOutput
#### Window join
To be continued
### Time and watermark
- [ ] AssignerWithPeriodicWatermarks
- [ ] AssignerWithPunctuatedWatermarks
- [ ] Window offset
### State
#### Operator state
- [ ] List state
- [ ] Union list state
- [ ] Broadcast state
#### Keyed State
- [ ] Value state
- [ ] List state
- [ ] Map state
- [ ] Reducing state & Aggregating State
#### State backends

### Procession function
- [ ] KeyedProcessFunction
- [ ] TimeService and Timers
- [ ] Side output
- [ ] CoProcessFunction

