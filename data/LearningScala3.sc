// Functions

// format def <function name>(parameter name: type...) : return type = {}

def squareIt(x: Int) : Int = {
  x * x
}


//Syntax: function name (input:type) : return type = { function operation }
def cubeIt(x: Int) : Int = { x * x * x }

println(squareIt( 2 ))
println(cubeIt( 3 ))

// Functions can also take other functions
//transformInt(takes Int, a function that
// takes int => returns Int): Int = { f(x) }
def transformInt(x: Int, f: Int => Int) : Int = {
  f(x)
}
//transformInt(takes int value named x, and a function that takes in an integer and returns another integer)
// : returns Int = { expression f(x) } so take function f, pass in x
//first part defines the args, second part defines the function

val result = transformInt(2, cubeIt)
println(result)

// Can define a lambda function as a parameter for the funciton
transformInt(3, x => x * x * x)
println(transformInt(3, x => x * x * x))

transformInt(10, x => x / 2)

transformInt(2, x => {val y = x * 2; y * y})
// 2, x => { val y(4) = x(2) * 2; 4 * 4 = 16


val upperString: String = "spark with scala"
val clown: String = "but dr, i am pagliacci"

println(upperString.toUpperCase())

def makeUpper(x: String): Unit = {
  println(x.toUpperCase())
}

//takes string, takes lambda f of type string => returns string
def makeUper(x: String, f: String => String): String = {
  f(x)
}

val upCase: String = upperString + "Test"
println(upperString)
makeUpper(upCase)

makeUpper(clown)
//def makeUpper(x: String, x: String ): Unit = {
//  println(x.toUpperCase())//}
//takes args clown, returns clown as clown.upperCase
println(makeUper(clown, x => x.toUpperCase()))
