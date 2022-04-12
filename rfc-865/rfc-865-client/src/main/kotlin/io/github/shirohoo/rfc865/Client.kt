package io.github.shirohoo.rfc865

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class Client {
    fun connect(): String {
        Socket(HOST, PORT).use {
            val bw = BufferedReader(InputStreamReader(it.getInputStream()))
            return bw.readText()
        }
    }

    companion object {
        private const val HOST = "127.0.0.1"
        private const val PORT = 17
    }
}