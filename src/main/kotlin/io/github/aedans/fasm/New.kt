package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class New(val type: Type) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.newInstance(type.asm)
    }
}