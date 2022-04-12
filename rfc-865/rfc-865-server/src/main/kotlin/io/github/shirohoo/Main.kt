package io.github.shirohoo

import io.github.shirohoo.rfc865.Server
import java.util.concurrent.Executors

fun main() {
    val processors = Runtime.getRuntime().availableProcessors()
    val threadPool = Executors.newFixedThreadPool(processors)

    Server(threadPool).run()
}
