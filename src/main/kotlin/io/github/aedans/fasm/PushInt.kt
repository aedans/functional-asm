package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushInt(val int: Int) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(int)
    }
}
