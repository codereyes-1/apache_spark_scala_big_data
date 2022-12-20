package com.sundogsoftware.spark

import org.apache.spark.sql._
import org.apache.log4j._

object SparkSQLDataset {

//  Compact way to define a class, object definition.
  case class Person(id:Int, name:String, age:Int, friends:Int)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Use SparkSession interface
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
//      similar to mongos in MDB. query router, distributes to all other servers in the cluster
      .master("local[*]")
      .getOrCreate()

    // Load each line of the source data into an Dataset
    import spark.implicits._
    val schemaPeople = spark.read
//      schema from csv should match schema defined in the Person(id:Int, name:String, age:Int, friends:Int)
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person] // takes df read from csv, converts to ds w/schema known at compile time using person class
      // with .as[Person] commented out, becomes a DF and no schema inferred at runtime

    schemaPeople.printSchema()

    //Here have schemaPeople dataset and can create a database view on it
    // basically creates database called people. can query that table now
    schemaPeople.createOrReplaceTempView("people")

    // object teens contains spark.sql query to people view "SELECT * FROM people WHERE age >= 13..."
    val teenagers = spark.sql("SELECT * FROM people WHERE age >= 13 AND age <= 19")
    
    val results = teenagers.collect()
    
    results.foreach(println)
    
    spark.stop()
  }
}