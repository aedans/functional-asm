package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushBoolean(val boolean: Boolean) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(boolean)
    }
}
