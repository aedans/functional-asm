package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushType(val type: Type) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(type.asm)
    }
}
