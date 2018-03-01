package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushFloat(val float: Float) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(float)
    }
}
