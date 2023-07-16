package com.gt.matlab

import com.mathworks.engine.MatlabExecutionException
import com.mathworks.engine.MatlabSyntaxException
import java.io.StringWriter

interface MatlabEngine {

    fun eval(expression: String): MatlabOutput

}

val matlabEngine: MatlabEngine by lazy { MatlabEngineImpl(System.getenv("MATLAB_SHARED_SESSION_NAME")) }

class MatlabEngineImpl(sessionName: String?) : MatlabEngine {

    private val matlabEngine = initEngine(sessionName)

    private fun initEngine(sessionName: String?): com.mathworks.engine.MatlabEngine {
        if (sessionName == null) {
            val options = arrayOf("-nodesktop")
            return com.mathworks.engine.MatlabEngine.startMatlab(options)
        }
        return com.mathworks.engine.MatlabEngine.connectMatlab(sessionName)
    }

    override fun eval(expression: String): MatlabOutput {
        val output = StringWriter()
        val error = StringWriter()
        try {
            matlabEngine.eval(expression, output, error)

        } catch (e: Exception) {
            when (e) {
                is MatlabExecutionException, is MatlabSyntaxException -> return MatlabOutput(
                    output.toString(),
                    error.toString()
                )
                else -> throw e
            }
        }
        return MatlabOutput(output.toString(), error.toString())
    }

}