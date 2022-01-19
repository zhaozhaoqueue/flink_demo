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
- [ ] From Kafka
- [ ] From socket <br/>
Mostly used for testing
- [ ] From customized source
#### Transformation
- [ ] Basic operators (map, flatMap, filter ...)
- [ ] Rolling aggregation operators (keyby, sum, min, max, minby, maxby, reduce)
- [ ] Multiple streams (split, select, connect and comap, union)
### UDF functions
- [ ] Basic function classes
- [ ] Rich functions
### Partition operator
- [ ] shuffle
- [ ] keyby
- [ ] global
#### Sink
- [ ] To kafka
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

