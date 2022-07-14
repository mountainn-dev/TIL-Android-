package com.example.kotlin_self_study

fun main(){
    var mycar2 = Caar(80.0, "Audi", "BMW")
    var myEcar2 = ElectricCar(80.0,"S-model", "Tesla", 85.0)

    mycar2.drive(200.0)
    myEcar2.drive(200.0)
    myEcar2.drive()
}

open class Caar(override val maxspeed: Double, name: String, brand: String): Drivable{
    open var range2: Double = 0.0

    fun extendRange(amount: Double){
        if(amount > 0){
            range2 += amount
        }
    }

    open fun drive(distance: Double){
        println("Drove for ${distance}km")
    }

    override fun drive(): String = "driving the interface drive"
}

class ElectricCar(maxspeed: Double, name: String, brand: String, battery: Double): Caar(maxspeed, name, brand), Drivable{
    override var range2 = battery * 6

    override fun drive(distance: Double){
        println("Drove for ${distance}km on electricity")
    }

    override fun drive(): String = "Drove for ${range2}km on electricity"
}

interface Drivable{
    val maxspeed: Double
    fun drive(): String
    fun brake(){
        println("The drivable is braking")
    }
}