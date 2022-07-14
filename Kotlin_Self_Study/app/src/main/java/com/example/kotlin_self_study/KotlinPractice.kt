package com.example.kotlin_self_study

fun main(){
    var myName = "Denis"
    println("Hello ${myName}")
    // Todo: read this

    var className: String = "Android Masterclass"
    val myFloat: Float = 13.37F
    val pi: Double = 3.1415
    var myAge: Int = 25
    var thisYear: Short = 2020
    var myBirthCode: Long = 188812345678
    var isRight: Boolean = true
    var myChar: Char = 'a'

    var a1 = 5.0
    var a2 = 3
    var resultDouble: Double = a1 / a2
    println(resultDouble)

    if(myAge >= 21){
        println("you may drink in US")
    } else if(myAge >= 18){
        println("you may vote")
    } else if(myAge >= 16){
        println("you may drive")
    } else{
        println("you are too young")
    }

    when(myAge){
        in 21..150 -> println("you may drink in US")
        in 18..20 -> println("you may vote")
        16, 17 -> println("you may drive")
        else -> println("you are too young")

    }

    //create a variable for testing all condition
    val age = 16
//create a variable for drinkingAge
    val drinkingAge = 21
//create a variable for votingAge
    val votingAge = 18
//create a variable for drivingAge
    val drivingAge = 16

//Assign the if statement to a variable
    val currentAge =  if (age >=drinkingAge){
        println("Now you may drink in the US")
//return the value for this block
        drinkingAge
    }else if(age >=votingAge){
        println("You may vote now")
//return the value for this block
        votingAge
    }else if (age>=drivingAge){
        println("You may drive now")
//return the value for this block
        drivingAge
    }else{
        println("You are too young")
//return the value for this block
        age
    }
//print the age for the passing condition
    println("current age is $currentAge")

    val x : Any = 13.37
//assign when to a variable
    var result =  when(x) {
//let condition for each block be only a string
        is Int -> "is an Int"
        !is Double -> "is not Double"
        is String -> "is a String"
        else -> println("is none of the above")
    }
//then print x with the result
    println("$x $result")

    var x1 = 100
    while (x1 >=20){
        print("$x1")
        x1 -= 2
    }
    println("\ndone")
    var x2 = 15
    do{
        print(x2)
        x2++
    }while (x2 <= 10)

    for (i in 10 downTo 1){
        print("\n$i")
    }
    var x3 = 0
    while (x3 <= 10000){
        x3++
        if (x3 == 9001){
            println("IT'S OVER 9000!")
        }
    }

    var humidity = "humid"
    var humidityLevel = 80
    while (humidityLevel > 60){
        humidityLevel -= 5
        println("humidity decreased")
        if (humidityLevel <= 60){
            humidity = "comfy"
            println("IT'S COMFY")
        }
    }
    result = myAverage(2.0F, 3)
    println(result)
    // nullable
    var nullableName: String? = null
    var nameLength = nullableName?.length
    println(nameLength)
    var lowCaseName = nullableName?.toLowerCase()
    print("$nameLength $lowCaseName")
    nullableName?.let { print("\nIT'S NOT NULL!") }

    // 앨비스 연산자
    nullableName = null
    val userName = nullableName ?: "User"
    println("\n$userName")


}
// function
fun myAverage(a: Float, b: Int): Float {
    return (a+b)/2
}
