package com.example.kotlin_self_study

fun main(){
    // val numbers: intArray = IntArrayof(1,2,3,4,5)
    // val numbers = intArrayof(1,2,3,4,5)
    val numbers = arrayOf(1,2,3,4,5)
    print(numbers.contentToString())

    val months = listOf("January", "February", "March")
    val newmonths = listOf("April", "May", "June")
    val restmonths = listOf("August", "September", "November", "December")
    val additionalmonths = months.toMutableList()   // 리스트를 우선 변형가능한 뮤터블리스트로 변경
    additionalmonths.addAll(newmonths)
    additionalmonths.add("July")
    additionalmonths.addAll(restmonths)
    println(additionalmonths)

    val fruits = setOf("Orange", "Apple", "Mango", "Grape", "Apple", "Orange")
    println(fruits.toSortedSet())

    val newfruits = fruits.toMutableList()

    val testList = arrayListOf<Double>()
    for (i in 0..4){
        testList.add((i+3.0)*2)
    }
    println(testList.average())
}

