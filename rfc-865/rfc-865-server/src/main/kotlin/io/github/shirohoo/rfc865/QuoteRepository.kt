package io.github.shirohoo.rfc865

object QuoteRepository {
    private val quotes: Set<String> = this::class.java.classLoader
        .getResource("quotes.txt")!!
        .readText()
        .split("${System.lineSeparator()}${System.lineSeparator()}")
        .toSet()

    fun quoteOfTheDay(): String = randomQuote().takeUnless { it.length > 512 } ?: randomQuote()

    private fun randomQuote() = quotes.random()
        .split("\r\n")
        .joinToString(separator = " ")
}
