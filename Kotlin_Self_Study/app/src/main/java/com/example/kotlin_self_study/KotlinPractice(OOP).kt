package com.example.kotlin_self_study

import java.lang.IllegalArgumentException

fun main() {
    var man1 = Person("Chris", "Daniel")
    var man2 = Person()
    var man3 = Person(lastName = "Joel")   // 클래스 생성자의 디폴트값을 설정하더라도, 따로 파라미터명을 통해 인수를 입력해줄 수 있다.
    var man4 = Person()
    println(man1.firstName)
    println(man4.age)

    var phone1 = mobilePhone()
    var phone2 = mobilePhone("android", "Samsung", "Galaxy20")
    var phone3 = mobilePhone(model = "Iphone7")

    man1.stateHobby()
    man1.hobby = "to skateboard"
    man1.stateHobby()

    var car1 = Car()
    println(car1.owner)
    println("maxspeed is ${car1.maxspeed}")
    car1.maxspeed = 100

    val user1 = User(1, "Michael")
    val user2 = user1.copy(name = "Doel")
    println(user2.component1())   // prints 1
    println(user2.component2())   // prints Doel

    val (userID, userName) = user2   // deconstruction
    println("$userID, $userName")

    val phone4 = MobilePhone2("Android", "Samsung", "Galaxy")
    phone4.chargeBattery(30)
}

class Person(firstName: String = "John", lastName: String = "Doe") {
    // member variables - properties
    var age: Int? = null
    var hobby: String = "watch Netflix"
    var firstName: String? = null
    var name: String = "$firstName $lastName"

    constructor(firstName: String = "A", lastName: String = "B", age: Int = 8): this(firstName, lastName){
        this.age = age
        println("you created new Person: His name is $firstName $lastName and age is $age")
    }
    init {
        this.firstName = firstName
        println("you created new Person! His name is $firstName $lastName")
    }

    // member function - method
    fun stateHobby(){
        println("$name hobby is $hobby")
    }

}

class mobilePhone(osName: String = "ios", brand: String = "Apple", model: String = "Iphone5") {
    init {
        println("$brand $model $osName")
    }
}

class Car(){
    lateinit var owner: String

    val mybrand: String = "BMW"
    get() {
        return field.lowercase()
    }

    var maxspeed: Int = 250
    set(value){
        field = if (value > 0) value else throw IllegalArgumentException("Maxspeed cannot be less than 0")
    }


    var myModel: String = "M5"

    init {
        this.owner = "Kate"
    }
}

data class User(val id: Long, val name: String)

class MobilePhone2(osName: String, brand: String, model: String){
    val range = (1..50)
    var battery = range.random().toInt()
    init {
        println("The phone $model from $brand uses $osName as its Operating System")
    }

    fun chargeBattery(charge: Int){
        var chargeBattery = battery + charge   // 단순 출력용이면 굳이 변수를 생성하지 않고 스트링 템플릿으로 바로 처리해버리는게 좋을듯
        println("battery charged about ${charge}%")
        println("battery was about ${battery}%, now battery is about ${chargeBattery}%")
    }
}