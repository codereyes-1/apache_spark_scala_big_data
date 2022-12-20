//// Flow control
//
////If / else:
//if (1 > 3) println("Impossible!") else println("The world makes sense.")
//
//if (1 > 3) {
//  println("Impossible!")
//  println("Really?")
//} else {
//  println("The world makes sense.")
//  println("still.")
//}
//
////Matching
//// List of different cases to check against the value number
//// if =1, execute println, 2, etc
//// case _ is catchAll, any other statement we'll hit that instead
//
//val number = 3
//number match {
//  case 1 => println("One")
//  case 2 => println("Two")
//  case 3 => println("Three")
//  case _ => println("Something else")
//
//}
//
//// forLoops
//for (x <- 1 to 4 ) {
//  val squared = x * x
//  println(squared)
//}
////iters thru values 1-4 each time thru assigned current value to
//// then compute new value squared (x * x)
//
////whileLoops
//// set x to 10, while x greater than = to 0, print x and subtract 1 from X's current value
//var x = 10
//while (x >= 0) {
//  println(x)
//  x -= 1
//}
//
//// Other structure for flow control is doWhileLoop
//x = 0
//do { println( x); x+=1 } while ( x <= 10 )
//
//// Expressions
//// When you have expressions with Scala,
//// it implicitly returns value of expression automatically within that block
//{ val x = 10; x + 20 }
////can also print the return of the expression
//println({ val x = 10; x + 20 })
// didn't say explicitly to return anything. just act of having expression as last thing in that block
// means that's what it outputs as a result
// Important to remember in terms of functional programming
// Implicitly, last thing that happens within a block of
// code is going to be the return value of that expression

//Challenge: write code using flow control
// that prints out fibonacci sequence

//val x = 0
//val y = 1
//println({x + y})
//var x = 0; var y =  x + 1;
//do { prixntln(x + y)} while (y <= 34)

//0+1 = 1 / x = y, y = z / 1 + 1 = 2 / x = y, y = z / 1 + 2 = 3 / x = y, y = z / 2 + 3 = 5
//1+1 = 2
//1+2 = 3




//var x = 0
//var y = 1 = 1
//var x = 1
//var y = 2 = 3
//var x = 2
//var y = 3


var x = 0
var y = 1
do { println(x); println({y}); x += y; y = x + y} while (y <=34)

//prnt 0, 1
// inc x(0) by y(1)=1 and y by 1 = (2),
// prnt 1, 2
// inc x(1) by y(2)=3 and y by 3 = (5)
// prnt 3, 5
// inc x(3) by y(5)=8 and y(5) by 8 = (13)
// prnt 8, 13
// inc x(8) by y(13)=21 and y(13) by 21 = (34)