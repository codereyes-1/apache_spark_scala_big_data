package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._

import scala.collection.mutable

/** Count up how many of each word occurs in a book, using regular expressions. */
object WordCountBetter {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

     // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "WordCountBetter")

    // Load each line of my book into an RDD
    val input = sc.textFile("data/book.txt")

    // Split using a regular expression that extracts words meaning break things up
    // on entire words, capital W means
    val words = input.flatMap(x => x.split("\\W+"))

    // To fix issues with upper, lower as diff words, Normalize everything to lowercase
    val lowercaseWords = words.map(x => x.toLowerCase())

    // transform each word into tuple of word & 1 (word, 1) now key/value rdd
    // .reduceByKey to sum up all the values. for each unique word, count the values
    // key is word, value is the 1. Key: "Astro", Value: 20(each time astro found, +=1 to the count)
    // when we add up all the number ones for each word occurrence
    // we end up with a count of how many times that word occurred
    val wordReduce = lowercaseWords.map( x => (x, 1)).reduceByKey( (x,y) => x + y )
    val wordCountSorted = wordReduce.map( x => (x._2, x._1)).sortByKey()
    println(wordCountSorted)

    for (result <- wordCountSorted) {
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }

    // Count of the occurrences of each word
//    val wordCounts = lowercaseWords.countByValue()
//    val wordSort = wordCounts.map(x => (x, 1))
//    val wordCollect = wordSort.collect(wordSort)
//    println(wordSort)
//    val sorting = mutable.ListMap(wordCounts.toSeq.sortBy(_._2): _*)
//    println(sorting)


    // Print the results
//    wordCounts.foreach(println)
  }

}

