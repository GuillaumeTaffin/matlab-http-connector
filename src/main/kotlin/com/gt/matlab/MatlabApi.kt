package com.gt.matlab

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Route.matlabApiRouting() {

    post("/eval") {
        val command = call.receive<EvalCommandRequest>().command
        val output = matlabEngine.eval(command)
        call.respond(EvalCommandResponse(command, output))
    }

}

@Serializable
data class EvalCommandRequest(
    val command: String
)

@Serializable
data class EvalCommandResponse(
    val command: String,
    val matlabOutput: MatlabOutput
)

@Serializable
data class MatlabOutput(
    val output: String,
    val error: String,
)