# Spring_Batch

## Introduction 
It is a light-weight batch framework based on spring. It is used to do batch processing. Batch processing is the process of processing large amount of data at a time. e.g in bank
## Components of spring batch
 
### Job Launcher
-	A job launcher executes a job.
### Job Repository
-	Job Repository stores the metadata about the jobs that have been configured and executed .
### Job
-	In a Spring Batch application, a job is the batch process that is to be executed. It runs from start to finish without interruption. This job is further divided into steps (or a job contains steps).
### Step
-	A step is an independent part of a job which contains the necessary information to define and execute the job (its part). 
-	A job may contain one or more steps.
  There are two types of steps :
•	Tasklet Step
•	Chunk oriented Step

Chunk means how many records at a time we want to process in a step from the available data. Chunk oriented steps  have three steps :
ItemReader -> ItemProcessor(optional) -> ItemWriter

o	Item reader reads data into a Spring Batch application from a particular source.

o	An Item processor is a class which contains the processing code which processes the data read into the spring batch. If the application reads "n" records, then the code in the processor will be executed on each record.

o	An Item writer writes data from the Spring Batch application to a particular destination.

## Different Types Of ItemReaders

3.1	 FlatFileItemReader
-	A flat file is any type of file that contains at most two-dimensional (tabular) data. Reading flat files in the Spring Batch framework is facilitated by the class called FlatFileItemReader, which provides basic functionality for reading and parsing flat files. The two most important required dependencies of FlatFileItemReader are Resource and LineMapper.
-	Resource specifies which input file to read. LineMapper converts string line into a Object .

3.2	 JdbcCursorItemReader
-	JdbcCursorItemReader is the JDBC implementation of the cursor-based technique. It works directly with a ResultSet and requires an SQL statement to run against a connection obtained from a DataSource.
-	It uses RowMapper. RowMapper is a callback interface used by jdbcTemplate’s query methods to process the ResultSet.

3.3	 JsonItemReader
- The JsonItemReader delegates JSON parsing and binding to implementation of the org.springframework.batch.item.json.JsonObjectReader  interface.
- This interface is intended to be implemented by using a streaming API to read JSON objects in chunks
- To be able to process JSON records, the following is needed:

  Resource: A Spring Resource that represents the JSON file to read.

  JsonObjectReader: A JSON object reader to parse and bind JSON objects to items

3.4	 ItemReaderAdapter
- Invokes a custom method on a delegate plain old Java object which itself provides an item. It is useful when we have read data from rest api.

3.5	 StaxEventItemReader
-	The StaxEventItemReader configuration provides a typical setup for the processing of records from an XML input stream.
-	To be able to process the XML records , the following is needed:
•	Root Element Name: The name of the root element of the fragment that constitutes the object to be mapped. The example configuration demonstrates this with the value of trade.
•	Resource: A Spring Resource that represents the file to read.
•	Unmarshaller: An unmarshalling facility provided by Spring OXM for mapping the XML fragment to an object.


## Different Types of ItemWriters

4.1	FlatFileItemWriter
-	A step must be able to write either delimited or fixed length formats in a transactional manner.
-	Here instead of LineTokenizer we use LineAggregator. Just as the LineTokenizer interface is necessary to take an item and turn it into a String, file writing must have a way to aggregate multiple fields into a single string for writing to a file. 
4.2	ItemWriterAdapter
-  There are cases where the existing service needs to act as an ItemReader or ItemWriter, either to satisfy the dependency of another Spring Batch class or because it truly is the main ItemReader for a step. It is fairly trivial to write an adapter class for each service that needs wrapping, but because it is such a common concern.
- Spring Batch provides implementations: ItemReaderAdapter and ItemWriterAdapter. Both classes implement the standard Spring method by invoking the delegate pattern and are fairly simple to set up.


4.3	JdbcBatchItemWriter
-	The writer is thread-safe after its properties are set (normal singleton behavior), so it can be used to write in multiple concurrent transactions. ItemWriter that uses the batching features from NamedParameterJdbcTemplate to execute a batch of statements for all items provided. 
-	The user must provide an SQL query and a special callback for either of ItemPreparedStatementSetter or ItemSqlParameterSourceProvider

4.4	 JsonItemWriter
-	The JsonFileItemWriter delegates the marshalling of items to the org.springframework.batch.item.json.JsonObjectMarshaller interface. The contract of this interface is to take an object and marshall it to a JSON String.

-	To be able to write JSON records, the following is needed:
•	Resource: A Spring Resource that represents the JSON file to write
•	JsonObjectMarshaller: A JSON object marshaller to marshall objects to JSON format

## Fault tolerance

Fault tolerance is a process that enables an application to respond to failure.
Fault tolerance is very important in spring batch as it deals with large amount of data and failing of one single record can hamper whole application .
Batch processes typically interact with other services (such as databases, messages brokers, web services, and others) which make fault tolerance even more important.

### Skip policy 

Any errors encountered during a Spring batch job processing will make a corresponding step fail. However, there are many situations where we'd rather like to skip the currently processed item for certain exceptions. 

So skip policy is skipping bad records while processing or while writing it to avoid failure of spring batch step. It can be done in following ways :

1.	Using skip and skipLimit
To enable skip functionality, we need to include a call to faultTolerant() during the step-building process.
Within skip() and skipLimit(), we define the exceptions we want to skip and the maximum number of skipped items.
2.	Using noSkip
In some situations, it is more appropriate to identify exceptions that should make our step fail and skip on any other.

3.	Using skipPolicy interface
It allows us to  provide our own implementation of skip logic and plug it into our step definition.

Also we can catch bad records
o	By Using annotations - @OnSkipInRead , @OnSkipInProcess , @OnSkipInWrite
o	By Using SkipListener Interface

### Retry mechanism

It is not always our data that is causing some issues , sometimes it may be the business into which we are writing that is having some issue .In such cases we use retry mechanism.

Retry mechanism is only applicable for item processor and item writer . It is not applicable for item reader.
o	We use retry and retryLimit to define the exceptions that qualify for a retry and the maximum retry count for an item, respectively.
 











