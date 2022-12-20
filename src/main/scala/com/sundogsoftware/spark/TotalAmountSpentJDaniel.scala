package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._


object TotalAmountSpentJDaniel {

  def rowSplit(row:String): (Int, Float) = {
    val fields = row.split(",")
    (fields(0).toInt, fields(2).toFloat)
  }
  /** Main function where the action happens */
  def main(args: Array[String]) {
    // Set log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create spark context using every core of the local machine
    val sc = new SparkContext("local[*]", "TotalAmountSpent")

    // Load each line of customer-orders.csv
    val input = sc.textFile("data/customer-orders.csv")
    //    println(input)

    // split each comma-delimited line into fields
    val dataSplit = input.map(rowSplit)

    // Reduce by key: customer to get total spent per customer
    val custTotalTuple = dataSplit.reduceByKey( (x,y) => x + y)

    // flip customer tuple
//    val custTupleFlipped = custTotalTuple.map( x => (x._2, x._1) )

//    val totalSorted = custTupleFlipped.sortByKey()

    // collect the results for total
    val result = custTotalTuple.collect().sorted

    // collect and sort flipped cust tuple
//    val result = custTupleFlipped.collect().sorted
//    val result = totalSorted.collect()

    //mod float result
    result.foreach(println)
//    for (results <- result) {
//      val customerID = results._1
//      val totalSpent = results._2
////      val formattedTotal = f"$totalSpent%.2f"
//      val formattedTotal = f"$totalSpent"
////      println(s"$customerID : spent $formattedTotal")
//      println(s"$customerID $formattedTotal")
//    }
  }
}
