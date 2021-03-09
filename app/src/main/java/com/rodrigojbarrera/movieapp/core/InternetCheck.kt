package com.rodrigojbarrera.movieapp.core

import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.Exception

object InternetCheck {

    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 5000)
            sock.close()
            true

        } catch (e: Exception) {
            false
        }
    }
}