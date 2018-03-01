package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushArg(val index: Int) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.loadArg(index)
    }
}
