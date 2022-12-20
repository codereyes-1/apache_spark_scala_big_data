// Data structures

// Tuples
// Immutable lists

// Not zero-based.. count starts at 1, compared to lists start at 0
val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D")
println(captainStuff)

// Refer to the individual fields iwth a ONE-BASED index
println(captainStuff._1)
println(captainStuff._2)
println(captainStuff._3)

val picardsShip = "Picard" -> "Enterprise-D"
println(picardsShip._2)

val aBunchofStuff = ("Kirk", 1964, true)
println(aBunchofStuff._1)
//tuples can hold different types of data


// Lists
// Like a tuple, but a collection object with more functionality
// Must be of same type. cannot hold items of different types

//Unlike tuple syntax, List is 0 based
val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")
println(shipList(1))

val shipNumbers = List(1232, 3544353, 543, 534.643, 43.543)
println(shipNumbers(4))


//Returns first item
println(shipList.head)
//Returns remaining items after the first
println(shipList.tail)

//var ship for each item in shipList :  print ship
for (ship <- shipList) {println(ship)}

// map: map some function to every element of that list
//here, function is a function literal: some ship string is mapped in reverse
// shipList.map means we're going to map every item in that list to function we're
// going to provide. {ship.reverse} using built-in reverse operator on the string operator
// prints every item in the variable backwards
val backwardShips = shipList.map( (ship: String) => {ship.reverse})
for ( ship <- backwardShips) {println(ship)}


// reduce() to combine together all the items in a collection using some function
// map applies a function to all elements in a list
// reduce will go through every element in the list and run the same function on it
// and let you accumulate all the results of that
// keeps a running total of some sort
val numberList = List(1, 2, 3, 4, 5)
val evensList = List(2, 4, 6, 8, 10)
val sum = numberList.reduce( (x: Int, y: Int) => x + y)
println( sum )
// What's happening:
// Start with 1. 1 + 2 =3
//x + y | 1 + 2 =3, result 3 carries as x into next iteration
//x + y | 3 + 3 = 6, result 6 carries as x into next iteration
//x + y | 6 + 4 = 10, result 10 carries as x into next iteration
//x + y | 10 + 5 = 15, result 15
// here, basically destructuring a function saying add all these numbers together
val evenSum = evensList.reduce( (x: Int, y: Int) => x + y )
println(evenSum)

val iHateFives = numberList.filter( ( x: Int) => x != 5)
println(iHateFives)

val iHateThrees = numberList.filter(_ !=3)
println(iHateThrees)
// _ in _ !=3 is a placeholder for 'every element in a list'
// this is scala's built in map/reduce/filter
//spark also has its own map/reduce/filter
// difference is spark will parallelize that across all machines in your cluster


// Concatenate lists
val moreNumbers = List(6,7,8)
val lotsOfNumbers = numberList ++ moreNumbers
val reversed = numberList.reverse
val reversedLots = lotsOfNumbers.reverse
val sorted = reversed.sorted
val sortedLots = reversedLots.reverse
val lotsOfDuplicates = numberList ++ numberList
val distinctValues = lotsOfDuplicates.distinct
val maxValue = numberList.max
val total = numberList.sum
val hasThree = iHateThrees.contains(3)


// MAPS (known as dictionaries in other langs, key:value lookups)

val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space")

println(shipMap("Sisko"))
// returns value "Deep Space" given the key name "Sisko"
println(shipMap.contains("Archer"))
val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown"
println(archersShip)
// example of exception handling




// EXERCISE
// create a list of numbers 1-20; print out numbers evenly divisible by 3
// Scala's modula operator is % which gives the remainder after division
// ex: 9 % 3 = 0 because 9 is evenly divisible by 3
// do this first by iterating through all items in the list and
// testing each one as you go.
// Then, do it again by using a filter function on the list instead




//first by map, then filter function on the list instead
//if (1 > 3) println("Impossible!") else println("The world makes sense.")

//val backwardShips = shipList.map( (ship: String) => {ship.reverse})
//for ( ship <- backwardShips) {println(ship)}

//for x in oneTwenty if x % 3 = 0 return x

//divs(oneTwenty)

//def even(x: Int, divs: Int => Int) : Int = { }

val oneTwenty = 1 to 20 toList
// returns Int x % 3
def divs(x: Int) : Int = { x % 3 }
// for even in oneTwenty: if division by three = remainder 0 : print even
for ( even <- oneTwenty if (divs(even) == 0)) {println(even)}
// val evFil = filter on list oneTwenty where function divs(x) returns 0
val evenFilter = oneTwenty.filter( (x: Int) => divs(x) == 0)
println(evenFilter)

//val iHateFives = numberList.filter( ( x: Int) => x != 5)
//println(iHateFives)

//val evs = oneTwenty.map(divy)
//println(evs)
//val iHateFives = numberList.filter( ( x: Int) => x != 5)
//println(iHateFives)
//
//val backwardShips = shipList.map( (ship: String) => {ship.reverse})

//for ( even <- oneTwenty)  if (divs(even)= 0) {println(even)}
//def evenDivs(x: Int, divs: Int => Int) : Int = {
//  divs(x if x == 0)
//}
println("test")

// hkjh