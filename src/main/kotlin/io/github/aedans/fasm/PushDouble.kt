package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushDouble(val double: Double) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(double)
    }
}
