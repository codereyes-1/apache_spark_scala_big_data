////Many RDD methods accept a function as a parameter
//rdd.map(x => x*x)
//is the same thing as
//
//def squareIt(x:Int) : Int = {
//  return x*x
//}
//rdd.map(squareIt)
//
//// Value RDD and the average age friends by age example
//
//Key is age, value is number of friends
//instead of listt of ages or a list of # of friends, we can store
//(age, #friends), (age, #friends)
//
//In Scala, just map pais of data into the RDD using tuples
//
//totalsByAge = rdd.map(x => (x, 1))
//
//Voila, now have a key/value RDD
//Ok to have tuples or other objects as values as well
//
//Once have k/v pairs in RDD spark can do special stuff with them
//
//reduceByKey(): combine values with the same key using some function.
//rdd.reduceByKey((x, y) => x+y) adds them up
//
//groupByKey(): group values with the same key
//sortByKey(): sort rdd by key values
//keys(), value() - create rdd of just the keys, or just the values
//
//possible to do sql-style joins on twl k/v rdd's
//join, rightOuterJoin, leftOuterJoin, cogroup, subtractByKey
//
//if doing map operations on k/v rdd and only tranforming the value part
//  use mapValue() and flatMappValues()
//
//Input Data: ID, name, age, number of friends
//
//0,Will,33,385
//1,Jean-Luc,33,2
//2,Hugh,55,221
//
//first map that data and split into some structure
//
//Here defining parsed line function that takes in each individual line of that
//rdd and splits it out based on the comma delimiter
//extracts age field and calls it age
//extracts num friends fiels and calls it numFriends returns
//a tuple (k/v pair) of age, numFriends
//create lines rdd by loading up textFile(../fakefriends.csv)
//then call map with parseLine function to tranform data into new rdd called rdd
//that contains k/v pairs
//
//def parseLine(line: String) = {
//  val fields = line.split(",")
//  val age = fields(2).toInt
//  val numFriends = fields(3).toInt
//  (age, numFriends)
//}
//
//val lines = sc.textFile('../fakefriends.csv')
//val rdd = lines.map(parseLine())
//
//In order to compute the average need grant total sum of friends exists for a given age
//2 and how many people that represented
//total #friends for given age
//  number of people that existed for that given age
//
//
//val totaleByAge = rdd.mapValues( x => (x, 1)).reduceByKey((x,y) => (x._1 + y._1 + x._2 + y._1))
////operating on values, we take each value and turn into it's own tuple
//rdd.mapValue( x => (x, 1))
////33yo has 385 friends = > tranformed to key:value (33, (385,1))
//(33, 385) => (33, (385,1))
//(33, 2) => (33, (2,1))
//(55, 221) => (55, (221,1))
////by associating 1 to each value, can add all values to get a grand total
////passing this new rdd directly to another operation, the .reduceByKey action
////add up first element of both tuple and second element of both tuples
////and add them all up for each unique key
//reduceByKey( (x, y) => (x._1 + y._1 + x._2 + y._1))
//x+ = adding number of friends exist for that age y+ =  how many people exist for that age
//reduced data down for all 33yo to grand total of all the number of friends that 33yo's had (387)
//  and total # of 33yo that existed (2 of them)
//(33, (387, 2))
//then by adding all those 1's we get a count of how many 33yo there were
//  that gives us the two things tha we need to compute the average that we want
//
//
//val averagesByAge = totalsByAge.mapValues( x => x._1 / x._2 )
////tuple of (age, (totalfriends, totalInstances))
////to compute average, divide totalFriends / totalInstances for each age
////go thru every value every tuple ended up with, divide them by each other to get the final average
//(33, (387, 2)) => (33, 193.5)
//
//val results = averagesByAge.collect()
////to get reduced average values
//results.sorted.foreach(println())
//
//// ******************************************
//// ******************************************
//// ******************************************
//// ******************************************
//// ******************************************
//// ******************************************
//
////****** What was the minimum temperature throughout the year for each weather station
//val minTemps = parseLines.filter( x => x._2 == "TMIN")
//// throws out all data where min temp type does not return true
//// every row of data is called x and we check if 2nd element of x is == "TMIN"
//// if it is then we keep it
//// minTemps contains filtered results of parsedLines
//
//// temp data is degrees celsius multiplied by 10 so -75 is actually 7.5 celc
//// ITE00100554, 18000101, TMAX, -75,,E
//// ITE00100554, 18000101, TMIN, -148,,E
//
//// Here is code that parses that data
//def parseLine(line:String) = {
//  val fields = line.split(',')
//  val stationID = fields(0)
//  val entryType = fields(2)
//  val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
//  (stationID, entryType, temperature)
//}
//
//val lines = sc.textFile('../1800.csv')
//val parsedLines = lines.map(parseLine)
////Output is (stationID, entryType, temperature)
//
////val minTemps = parseLines.filter( x => x._2 == "TMIN")
////after this we can create (stationID:x._1, temperature:x._3) k/v pairs
//val stationTemps = minTemps.map( x => (x._1, x._3.toFloat))
//
//// x = current minimum, y = incoming row and return minimum between those two
//val minTempsByStation = stationTemps.reduceByKey( (x,y) => min(x,y))
//
//// to find min from all temps for a given stationID, use reduceByKey
//// take every running total we have for current min temp compare that to a new row (x,y)
//// run min operation to only preserver the minimum value of min(x, y)
//// will go thru row by row all the min temps for a given key where every key reps a weather station
//// is current row less than next compared row? no, ok great that's the new minimum
//// end up with smaller rdd called minTempsByStation that contains all unique keys
//// this case just two weather stationIDs and minimum temp seen for each station
//
//// now collect results with the collect action
//val results = minTempsByStation.collect()
//  for (result <- results.sorted) {
//    val station = result._1
//    val temp = result._2
//    val formattedTemp = f"$temp%.2f F"
////    s for subtitute $station, $formattedTemp
//    println(s"$station minimum temperature: $formattedTemp")
//  }
//
//
//// Counting word occurrences using flatMap()
//
//"The quick red
//fox jumped
//  over the lazy
//brown dogs"
//
//val lines = sc.textFile('redfox.txt')
//val rageCaps = lines.map( x => x.toUpperCase)
//
//THE QUICK RED
//FOX JUMPED
//  OVER THE LAZY
//BROWN DOGS
//
//Map() tranformws each element of an rdd into one new element
//Map() takes every input row and convert to one and only one output row
//  start with 4 rows input rdd end up with 4 rows in output rdd
//One row goes in, one row goes out
//
//FlapMap() removes that restriction
//  start with same input rdd called lines but now calling flatMap()
//
//val lines = sc.textFile('redfox.txt')
//val words = lines.flatMap( x => x.split(''))
//take in each row, call it x and split it up based on space character
//splits each row into individual words each on its own row in the resulting words rdd
//
//flatMap() can create many new elements from each one
//
//(the,1176)
//(Key,1)
//(row,,1)
//(trends,10)
//(worker,2)
//(EBay,,2)
//(�minimal,1)
//(says,,1)
//(wardrobe.,1)
//(reach,,1)
//(decline,2)
//(STICK,1)
//(generous,1)
//(---------------,2)
//(entrepreneurs,,1)
//(IDEAS,2)
//(parking?,1)
//(millions-of-dollars-per-year,1)
//(updates?,1)
//(***,1)
//(News,1)
//(2009.,1)
//
//works but some issues:
//  depending on capitalization might treat some as different words
//did Products only appear once or just once in capital letters?
//  Is open-ended one or two words?
//  do we strip punctuation marks on words like decision? owners, appeared 2 times
//
///// *** improving wordcount script with regex
// "Why is counted as one word
// fix things like commas punctuation marks
//
//
//
//
//  split each comma-delimited line into fields
//  map each line to key/value pairs of customer ID and dollar amount
//  use reduceByKey to add up amount spend by customer ID
//  collect() the results and print them
//  Review: split comma-delimited fields
//  Treat field 0 as an integer, and field 2 as a floating point
//  ( fields(0).toInt, fields(2).toFloat)
//
//
//// Introduction to Spark SQL SparQL
//
//
// Extends RDD to a 'DataFrame' object
// DF's
// - contain row objects
// - can run SQL queries
// - has schema(more efficient storage)
// - read and write JSON, Hive , parquet
// - communicates with JBDC/ODBC, Tableau
// DF a lot like RDB, with row objects with some sort of structured information
// Can treat just like a sql db
// can read write to json or hive
// can look just like a rdb even though it's apache spark running on a cluster

// DataSet is a dataframe of row objects
// Dataset wraps in explicit type where we know schema upfront at compile time
// can create dataset of a person case class
// want a string and a double on each row
// with DF all I know is I have a dataset of rows that could contain anything
// a DS knows upfront what types are inside it
// a DS knows it's a schema at compile time
// This means type-related errors are detected when build your script
// as opposed to when you actually run the script
// running a script can be expensive operation when run on a huge cluster
// Because DS doing things at compile time, can only use them with languages that are compiled
// like Java or Scala in the world of Spark
// A good reason to use Scala instead of Python
// w python we're limited to data frames and no compile time optimization
// can go between RDD and DS with .toDS and reverse
//
// We will be using spark session object instead of spark context, just like a JDBC/ODBC session
// Stop the session when you're done
// Once you have dataset object loaded, can so sql commands
  // myResultDataSet.show()
//  myResultDataSet.select('some field')
// myResultDataSet.filter(myResultDataSet('someFieldName') > 200)
// myResultDataSet.groupBy('myResultDataSet'('someFieldName'))
// myResultDataSet.rdd().map(mapperFunction) <-- convert back to rdd and pass in custom map function

// SparkSQL exposes a JDBC/ODBC server
// Start with sbin/start-thriftserver.js
// Listens on port 10000 by default
// Connect using bin/beeline -u jdbc:hive2://localhost:10000
// Voila, have SQL shell to SparkSQL
// Can create new tables or query existing doesn that were cached using hiveCtx.cacheTable('tableName')
// USER DEFINED FUNCTIONS (UDF'S)
// import org.apache.spark.sql.function.udf
// val square = udf{ ( x => x*x) }
// squareDF = df.withColumn('square', square('value'))












//