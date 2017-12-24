package com.evansitzes.projects

import java.util.*
import java.util.regex.Pattern

/**
 Randomly generated xkcd calendar trivia
 Source: http://www.explainxkcd.com/wiki/index.php/1930
 Example Output:
 "Did you know that Toyota Truck Month might happen twice this year because of an arbitrary decision by Benjamin Franklin?
 Apparently that's why we have leap seconds.
 While it may seem like trivia, it is now recognized as a major cause of World War I."
*/
fun main(args : Array<String>) {
    val times: Array<String> = arrayOf("The [Fall/Spring] Equinox",
                                    "The [Winter/Summer] Solstice",
                                    "The [Winter/Summer] Olympics",
                                    "The [latest/earliest] [sunset/sunrise]",
                                    "Daylight [saving/savings] time",
                                    "Leap [day/year]",
                                    "Easter",
                                    "The [harvest/super/blood] moon",
                                    "Toyota Truck Month",
                                    "Shark Week")

    val methods: Array<String> = arrayOf("happens [earlier/later/at the wrong time] every year",
                                        "drifts out of sync with the [sun/moon/zodiac/atomic clock in Colorado]",
                                        "drifts out of sync with the [Gregorian/Mayan/lunar/iPhone] calendar",
                                        "might [not happen/happen twice] this year")

    val phenomena: Array<String> = arrayOf("time zone legislation in [Indiana/Arizona/Russia]",
                                        "a decree by the Pope in the 1500s",
                                        "the [precession/libration/nutation/libation/eccentricity/obliquity] of the [Moon/Sun/Earth's axis/Equator/Prime Meridian/International Date Line/Mason-Dixon Line]",
                                        "magnetic field reversal",
                                        "an arbitrary decision by [Benjamin Franklin/Isaac Newton/FDR]")

    val consequences: Array<String> = arrayOf("it causes a predictable increase in car accidents",
                                            "that's why we have leap seconds",
                                            "scientists are really worried",
                                            "it was even more extreme during the [Bronze Age/IceAge/Cretaceous/1990s]",
                                            "there's a proposal to fix it, but it [will never happen/actually makes things worse/is stalled in Congress/may be unconstitutional]",
                                            "it's getting worse and no one knows why")

    val trivia: Array<String> = arrayOf("causes huge headaches for software developers",
                                        "is taken advantage of by high-speed traders",
                                        "triggered the 2003 Northeast Blackout",
                                        "has to be corrected for by GPS satellites",
                                        "is now recognized as a major cause of World War I")

    println("Did you know that ${parseText(times)} ${parseText(methods)} because of ${parseText(phenomena)}? Apparently ${parseText(consequences)}.")
    println("While it may seem like trivia, it ${parseText(trivia)}.")
}

fun parseText(text: Array<String>): String {
    val random = Random()
    val pattern = Pattern.compile("\\[(.*?)]")
    var randomSentence = text.get(random.nextInt(text.size - 0))
    val matcher = pattern.matcher(randomSentence )

    while (matcher.find()) {
        val selection = matcher.group()
        val choices = selection.replace("[", "").replace("]", "").split("/")
        val subIndex = random.nextInt(choices.size - 0)

        randomSentence = randomSentence.replace(selection, choices[subIndex])
    }

    return randomSentence
}
