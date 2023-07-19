package com.gt

import com.gt.plugins.configureAdministration
import com.gt.plugins.configureMonitoring
import com.gt.plugins.configureRouting
import com.gt.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(vararg args: String) {
    embeddedServer(Netty, port = 6081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureAdministration()
    configureRouting()
}
