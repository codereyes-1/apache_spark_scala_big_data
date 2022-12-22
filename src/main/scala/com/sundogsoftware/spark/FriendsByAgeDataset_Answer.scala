package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/** Compute the average number of friends by age in a social network. */
object FriendsByAgeDataset_Answer {

  // Create case class with schema of fakefriends.csv
  case class FakeFriends(id: Int, name: String, age: Int, friends: Long)

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkSession using every core of the local machine
    val spark = SparkSession
      .builder
      .appName("FriendsByAge")
      .master("local[*]")
      .getOrCreate()

    // Load each line of the source data into an Dataset
    import spark.implicits._
    val friendsByAge = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[FakeFriends]

    // Select only age and numFriends columns
    println("age/friends table")
    friendsByAge.select("age", "friends").show()

    // Get avg # of friends
    println("avg #friends by age")
    println(friendsByAge.groupBy("age").avg("friends").sort("age").show())

    println("avg #friends, rounded")
    println(friendsByAge.groupBy("age").agg(round(avg("friends"), 2)).sort("age").show())

    println("avg #friends, col renamed")
    println(friendsByAge.groupBy("age").agg(round(avg("friends"), 2).alias("friends_avg")).sort("age").show())

    spark.stop()
  }







//    // From friendsByAge we group by "age" and then compute average
//    friendsByAge.groupBy("age").avg("friends").show()
//
//    // Sorted:
//    friendsByAge.groupBy("age").avg("friends").sort("age").show()
//
//    // Formatted more nicely:
//    friendsByAge.groupBy("age").agg(round(avg("friends"), 2))
//      .sort("age").show()
//
//    // With a custom column name:
//    friendsByAge.groupBy("age").agg(round(avg("friends"), 2)
//      .alias("friends_avg")).sort("age").show()
//  }
}
  