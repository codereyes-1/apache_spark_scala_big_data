// Values are immutable constants.

val hello: String = "Hola!"
var helloThere: String = hello
helloThere = hello + "There!"
println(helloThere)

val immutableHelloThere = hello + "There"
println(immutableHelloThere)


// Data types

val numberOne: Int = 1
val truth: Boolean = true
val letterA: Char = 'a'
val pi: Double = 3.14159265
val piSinglePrecision: Float = 3.14159265f
val bigNumber: Long = 1234556687
val smallNumber: Byte = 127
// number represented in one byte so can only represent numbers
// from -127 to positive 127 or, if unassigned
// 0 - 255
// how to print and display data, and format output
println("Here is a mess: " + numberOne + truth + letterA + pi + bigNumber)

//$ indicates variable name or value name
// insert value of piSinglePrecision
// %.3f is floating point value to display there
// after decimal point, only want 3 digits of precision displayed
println(f"Pi is about $piSinglePrecision%.3f")
// %05 means I want to have at least 5 digits to the left of decimal point
println(f"Zero padding on the left: $numberOne%05d")

// want to substitute variable into a string w/out actually specifying formatting
// s for substitute. w/out having to use string concatenate
println(s"I can use s prefix to use variables like $numberOne $truth $letterA")

// Can include expressions in our string commands
// Curly brackets after a dollar sign, will evaluate the expression inside those curly brackets and print result of that as part of the string
println(s"The s prefix isn't limited to variables; I can include any expression. Like ${1 + 2}")
val theUltimateAnswer: String = "To life, the universe, and everything is 42"
val pattern = """.* ([\d]+).*""".r
// .* match anything, space, within parenth is thing we're trying to extract from that pattern
// [\d] means extract a number, + is any number of numbers, .* followed by any other characters
// looking for a bunch of characters followed by space, then a number, followed by anything else... will pull 42 out of that string
val pattern(answerString) = theUltimateAnswer
// means we'll take the regex defined in pattern, assign output of that to answerString
// wand to take what's in regex parentheses transfer result to what's in pattern(answerString) parentheses
val answer = answerString.toInt
println(answer)
//Booleans
val isGreater = 1 > 2
val isLesser = 1 < 2
val impossible = isGreater & isLesser
val anotherWay = isGreater && isLesser
// C: && logical and, & is bitwise and
val anotherWay = isGreater || isLesser
// isGreater or isLesser


val picard: String = "Picard"
val bestCaptain: String = "Picard"
val isBest: Boolean = picard == bestCaptain
// == going into value of strings and comparing them to see if true

// write code that takes value of pi, doubles it and
// prints it within a string with 3 decimals of precision to the right

println(f"Pi is about ${pi * 2}%.3f")