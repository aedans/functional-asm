package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class GetField(val owner: Type, val name: String, val type: Type) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.getField(owner.asm, name, type.asm)
    }
}
