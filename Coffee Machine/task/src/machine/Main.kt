package machine

import machine.States.*

enum class States {IDLE, BUY, FILL1, FILL2, FILL3, FILL4, EXIT}

class CoffeeMachine(var beans: Int, var water: Int, var milk: Int, var cups: Int, var money: Int, var currentState: States = IDLE) {
    fun handleInput(input: String) = when (currentState) {
        IDLE -> makeAction(input)
        BUY -> chooseVariant(input)
        FILL1 -> {
            fillWater(input)
            currentState = FILL2
            println("Write how many ml of milk do you want to add: ")
        }
        FILL2 -> {
            fillMilk(input)
            currentState = FILL3
            println("Write how many grams of coffee beans do you want to add: ")
        }
        FILL3 -> {
            fillCoffeeBeans(input)
            currentState = FILL4
            println("Write how many disposable cups of coffee do you want to add: ")
        }
        FILL4 -> {
            fillCups(input)
            currentState = IDLE
        }
        EXIT -> {}
    }

    private fun makeAction(input: String) = when (input) {
        "buy" -> {
            currentState = BUY
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        }
        "fill" -> {
            currentState = FILL1
            println("Write how many ml of water do you want to add: ")
        }
        "take" -> takeAway()
        "remaining" -> checkRemaining()
        "exit" -> currentState = EXIT
        else -> println("Wrong input!")
    }

    private fun chooseVariant(input: String) = when (input) {
        "1" -> {
            if (water >= 250 && beans >= 16 && cups >= 1) {
                water -= 250
                beans -= 16
                cups -= 1
                money += 4
                println("I have enough resources, making you a coffee!")
            }
            else {
                when {
                    water < 250 -> println("Sorry, not enough water!")
                    beans < 16 -> println("Sorry, not enough coffee beans!")
                    cups < 1 -> println("Sorry, not enough disposable cups!")
                    else -> {}
                }
            }
            currentState = IDLE
        }
        "2" -> {
            if (water >= 350 && milk >= 75 && beans >= 20 && cups >= 1) {
                water -= 350
                milk -= 75
                beans -= 20
                cups -= 1
                money += 7
                println("I have enough resources, making you a coffee!")
            }
            else {
                when {
                    water < 350 -> println("Sorry, not enough water!")
                    milk < 75 -> println("Sorry, not enough milk!")
                    beans < 20 -> println("Sorry, not enough coffee beans!")
                    cups < 1 -> println("Sorry, not enough disposable cups!")
                    else -> {}
                }
            }
            currentState = IDLE
        }
        "3" -> {
            if (water >= 200 && milk >= 100 && beans >= 12 && cups >= 1) {
                water -= 200
                milk -= 100
                beans -= 12
                cups -= 1
                money += 6
                println("I have enough resources, making you a coffee!")
            }
            else {
                when {
                    water < 200 -> println("Sorry, not enough water!")
                    milk < 100 -> println("Sorry, not enough milk!")
                    beans < 12 -> println("Sorry, not enough coffee beans!")
                    cups < 1 -> println("Sorry, not enough disposable cups!")
                    else -> {}
                }
            }
            currentState = IDLE
        }
        "back" -> currentState = IDLE
        else -> println("Wrong input!")
    }

    private fun fillWater(input: String) {
        water += input.toInt()
    }
    private fun fillMilk(input: String) {
        milk += input.toInt()
    }
    private fun fillCoffeeBeans(input: String) {
        beans += input.toInt()
    }
    private fun fillCups(input: String) {
        cups += input.toInt()
    }

    private fun takeAway() {
        println("I gave you $$money")
        money = 0
    }
    private fun checkRemaining() {
        println("""The coffee machine has:
$water of water
$milk of milk
$beans of coffee beans
$cups of disposable cups
$$money of money
""")
    }
}

fun main() {
    val cm = CoffeeMachine(120, 400, 540, 9, 550, IDLE)
    while (cm.currentState != EXIT) {
        if (cm.currentState == IDLE)
            println("Write action (buy, fill, take, remaining, exit): ")
        val cmd = readLine()!!
        cm.handleInput(cmd)
    }
}



/*
var water = 400
var milk = 540
var beans = 120
var cups = 9
var money = 550

fun buy(type: Int) = when (type) {
    1 -> {
        if (water >= 250 && beans >= 16 && cups >= 1) {
            water -= 250
            beans -= 16
            cups -= 1
            money += 4
            println("I have enough resources, making you a coffee!")
        }
        else {
            when {
                water < 250 -> println("Sorry, not enough water!")
                beans < 16 -> println("Sorry, not enough coffee beans!")
                cups < 1 -> println("Sorry, not enough disposable cups!")
                else -> {}
            }
        }
    }
    2 -> {
        if (water >= 350 && milk >= 75 && beans >= 20 && cups >= 1) {
            water -= 350
            milk -= 75
            beans -= 20
            cups -= 1
            money += 7
            println("I have enough resources, making you a coffee!")
        }
        else {
            when {
                water < 350 -> println("Sorry, not enough water!")
                milk < 75 -> println("Sorry, not enough milk!")
                beans < 20 -> println("Sorry, not enough coffee beans!")
                cups < 1 -> println("Sorry, not enough disposable cups!")
                else -> {}
            }
        }
    }
    3 -> {
        if (water >= 200 && milk >= 100 && beans >= 12 && cups >= 1) {
            water -= 200
            milk -= 100
            beans -= 12
            cups -= 1
            money += 6
            println("I have enough resources, making you a coffee!")
        }
        else {
            when {
                water < 200 -> println("Sorry, not enough water!")
                milk < 100 -> println("Sorry, not enough milk!")
                beans < 12 -> println("Sorry, not enough coffee beans!")
                cups < 1 -> println("Sorry, not enough disposable cups!")
                else -> {}
            }
        }
    }
    else -> {}
}

fun fill() {
    println("Write how many ml of water do you want to add: ")
    val w = readLine()!!.toInt()
    println("Write how many ml of milk do you want to add: ")
    val m = readLine()!!.toInt()
    println("Write how many grams of coffee beans do you want to add: ")
    val b = readLine()!!.toInt()
    println("Write how many disposable cups of coffee do you want to add: ")
    val c = readLine()!!.toInt()
    water += w
    milk += m
    beans += b
    cups += c
}

fun take() {
    println("I gave you $$money")
    money = 0
}

fun remaining() {
    println("""The coffee machine has:
$water of water
$milk of milk
$beans of coffee beans
$cups of disposable cups
$money of money
""")
}

fun main() {

    println("Write action (buy, fill, take, remaining, exit): ")
    var choice = readLine()!!

    while (choice != "exit") {

        when (choice) {
            "buy" -> {
                val type = readLine()!!
                if (type != "back")
                    buy(type.toInt())
            }
            "fill" -> fill()
            "take" -> take()
            "remaining" -> remaining()
        }

        choice = readLine()!!
    }


}
*/