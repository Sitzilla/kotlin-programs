package com.evansitzes.projects

import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    println("Welcome to character creation!")
    println("Please enter a character name...")
    val name = scanner.nextLine()

    println("Please enter a character gender (male|female)...")
    var gender = scanner.nextLine()
    while (gender != "male" && gender != "female") {
        println("Please enter a valid character gender (male|female)...")
        gender = scanner.nextLine()
    }

    println("Please enter a character race (human|elf|dwarf|half-elf|half-orc)...")
    var race = scanner.nextLine()
    while (race != "human" &&
            race != "elf" &&
            race != "dwarf" &&
            race != "half-elf" &&
            race != "half-orc") {
        println("Please enter a valid character race (human|elf|dwarf|half-elf|half-orc)...")
        race = scanner.nextLine()
    }

    println("Please enter a character class (warrior|rouge|archer|mage)...")
    var clazz = scanner.nextLine()
    while (clazz != "warrior" &&
            clazz != "rouge" &&
            clazz != "archer" &&
            clazz != "mage") {
        println("Please enter a valid character class (warrior|rouge|archer|mage)...")
        clazz = scanner.nextLine()
    }

    var unassignedPoints = 48
    var complete = false

    var abilityScores = hashMapOf<String, Int>("strength" to 8,
                                                        "dexterity" to 8,
                                                        "constitution" to 8,
                                                        "intelligence" to 8,
                                                        "wisdom" to 8,
                                                        "charisma" to 8)

    var scoreToPointCost = hashMapOf<Int, Int>(8 to 0,
                                                        9 to 1,
                                                        10 to 2,
                                                        11 to 3,
                                                        12 to 4,
                                                        13 to 5,
                                                        14 to 6,
                                                        15 to 8,
                                                        16 to 10,
                                                        17 to 13,
                                                        18 to 16)

    printAbilityMessage(abilityScores, unassignedPoints)

    while (!complete) {
        val input = scanner.nextLine()

        if (input == "scores") {
            printAbilityMessage(abilityScores, unassignedPoints)
            continue
        }

        if (input == "finish") {
            complete = true
            continue
        }

        val abilityScore = input.split(" ")

        if (abilityScore.size < 2 || abilityScore.get(1).toIntOrNull() !is Int) {
            continue
        }

        val ability = abilityScore.first().toLowerCase()
        val score = abilityScore.get(1).toInt()

        if (abilityScores.containsKey(ability) && scoreToPointCost.containsKey(score)) {

            val newPoints = scoreToPointCost.get(score)!! - scoreToPointCost.get(abilityScores.get(ability))!!

            if (unassignedPoints - newPoints < 0) {
                continue
            }

            unassignedPoints += scoreToPointCost.get(abilityScores.get(ability))!!
            unassignedPoints -= scoreToPointCost.get(score)!!
            abilityScores.put(ability, score)
            println("Unassigned Points: ${unassignedPoints}")
        }

    }

    println("Name: " + name)
    println("Gender: " + gender)
    println("Race: " + race)
    println("Class: " + clazz)
    println("Ability Scores: ")
    printAbilityScores(abilityScores)
    val array = arrayOf("Name: ${name}, Gender: ${gender}, Race: ${race}, Class: ${clazz}, Ability Scores: ${abilityScores}")

    FileHelper.writeToFile("/tmp/Character Sheet for ${name}.txt", array)
}

fun printAbilityMessage(abilityScores: Map<String, Int>, unassignedPoints: Int) {
    println("Current Ability Scores:")
    printAbilityScores(abilityScores)
    println("Unassigned Points: ${unassignedPoints}")
    println()
    println("Assign Ability Scores (format <score> <points>, Example: 'strength 18'). Type 'scores' to show scores. Type 'finish' when done.")
}

fun printAbilityScores(abilityScores: Map<String, Int>) {
    for (score in abilityScores) {
        println("${score.key.capitalize()}: ${score.value}")
    }
}