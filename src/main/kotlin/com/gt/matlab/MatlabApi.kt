package com.gt.matlab

import com.mathworks.engine.MatlabEngine
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.io.StringWriter

fun Route.matlabApiRouting() {

    route("matlab") {

        get("shared-sessions") {
            call.respond(MatlabEngine.findMatlab())
        }

        route("{session-id}") {

            post("/eval") {
                val sessionId = call.parameters["session-id"]!!
                val command = call.receive<EvalCommandRequest>().command

                val output = useEngine(sessionId) { eval(command) }

                call.respond(EvalCommandResponse(command, output))
            }

        }

    }

}

class Matlab(sessionId: String) {

    private val engine = MatlabEngine.connectMatlab(sessionId)

    fun disconnect() = engine.disconnect()

    fun eval(command: String): MatlabOutput {
        val outputStream = StringWriter()
        val errorStream = StringWriter()
        engine.eval(command, outputStream, errorStream)
        return MatlabOutput(outputStream.toString(), errorStream.toString())
    }

}

fun useEngine(sessionId: String, bloc: Matlab.() -> MatlabOutput): MatlabOutput {
    val matlab = Matlab(sessionId)
    val result = matlab.bloc()
    matlab.disconnect()
    return result
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