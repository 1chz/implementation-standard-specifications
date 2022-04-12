package io.github.shirohoo.rfc865

object QuoteRepository {
    private val quotes: Set<String> = this::class.java.classLoader
        .getResource("quotes.txt")!!
        .readText()
        .split("\n\n")
        .toSet()

    fun quoteOfTheDay(): String = quotes.random()
}
