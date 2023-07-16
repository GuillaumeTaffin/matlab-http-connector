package com.gt

import com.gt.matlab.matlabEngine
import com.gt.plugins.configureAdministration
import com.gt.plugins.configureMonitoring
import com.gt.plugins.configureRouting
import com.gt.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.launch


fun main(vararg args: String) {
    val port = args.firstOrNull()?.toInt() ?: 8080

    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    initMatlabEngine()
    configureMonitoring()
    configureSerialization()
    configureAdministration()
    configureRouting()
}

private fun Application.initMatlabEngine() = launch {
    val matlabOutput = matlabEngine.eval("ver")
    log.info("Successfully connected to Matlab :")
    log.info(matlabOutput.output)
}
