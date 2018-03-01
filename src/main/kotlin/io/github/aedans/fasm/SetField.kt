package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class SetField(val owner: Type, val name: String, val type: Type) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.putField(owner.asm, name, type.asm)
    }
}
