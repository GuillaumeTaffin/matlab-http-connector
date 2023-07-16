package com.gt.plugins

import com.gt.matlab.matlabApiRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        matlabApiRouting()
    }
}
