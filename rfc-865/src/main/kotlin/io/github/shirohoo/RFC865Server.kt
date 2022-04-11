package io.github.shirohoo

import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RFC865Server {
    private val threadPool: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val port = 17
    private val backlog = 0

    fun run() {
        ServerSocket(port, backlog).use {
            while (true) {
                threadPool.submit(RFC865Worker(QuoteRepository, it.accept()))
            }
        }
    }
}
