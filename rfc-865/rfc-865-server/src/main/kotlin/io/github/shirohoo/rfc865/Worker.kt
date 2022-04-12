package io.github.shirohoo.rfc865

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket

class Worker(
    private val quoteRepository: QuoteRepository,
    private val socket: Socket,
) : Runnable {
    override fun run() {
        val bw = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        bw.write(QuoteRepository.quoteOfTheDay())
        bw.flush()

        socket.close()
    }
}
