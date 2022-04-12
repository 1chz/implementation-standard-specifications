package io.github.shirohoo

import io.github.shirohoo.rfc865.Client

fun main() {
    Client().connect().also(::println)
}