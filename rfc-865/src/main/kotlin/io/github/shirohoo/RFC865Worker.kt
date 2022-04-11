package io.github.shirohoo

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket

class RFC865Worker(
    private val quoteRepository: QuoteRepository,
    private val socket: Socket
) : Runnable {
    override fun run() {
        val bw = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        bw.write(quoteRepository.quoteOfTheDay())
        bw.flush()

        socket.close()
    }
}
