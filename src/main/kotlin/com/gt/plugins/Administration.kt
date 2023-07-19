package com.gt.plugins

import io.ktor.server.application.*
import io.ktor.server.engine.*

fun Application.configureAdministration() {
    install(ShutDownUrl.ApplicationCallPlugin) {
        shutDownUrl = "/connector-shutdown"
        exitCodeSupplier = { 0 }
    }
}
