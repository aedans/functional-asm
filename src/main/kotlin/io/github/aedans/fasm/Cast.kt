package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class Cast(val type: Type) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.checkCast(type.asm)
    }
}
