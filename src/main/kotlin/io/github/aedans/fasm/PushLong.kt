package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushLong(val long: Long) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(long)
    }
}
