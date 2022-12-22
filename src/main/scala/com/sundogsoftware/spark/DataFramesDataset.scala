package com.sundogsoftware.spark

import org.apache.spark.sql._
import org.apache.log4j._
    
object DataFramesDataset {
  
  case class Person(id:Int, name:String, age:Int, friends:Int)

  /** Our main function where the action happens */
  def main(args: Array[String]) {
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema.
    import spark.implicits._
    val people = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person]

    // There are lots of other ways to make a DataFrame.
    // For example, spark.read.json("json file path")
    // or sqlContext.table("Hive table name")
    
    println("Here is our inferred schema:")
    people.printSchema()
    
    println("Let's select the name column:")
    people.select("name").show()
    
    println("Filter out anyone over 21:")
    people.filter(people("age") < 21).show()
    //  same as select name where age < 21
    // having an expression as a parameter passed to a function works in scala
   
    println("Group by age:")
  people.groupBy("age").count().sort("age").show()
    // same as the group by key operation in RDD. collates all the distinct values of ages
    // then call count on them to get a total of how many ppl exist in each age year and show results
    // with RDDD, transform with map operation to insert a number 1 for each person then do reduce by key operation
    // to add them all up. with datasets can use more of SQL syntax and do in one line of code

    println("Make everyone 10 years older:")
    people.select(people("name"), people("age") + 10).show()
    // select all the people, their age and +10 for each age

    spark.stop()
    // This approach is closing the gap between Spark and using a relational database and having it used in same way w/Apache Spark in
    // newer versions. Difference is instead of single monolithic database server, an entire cluster doing this potentially
  }
}