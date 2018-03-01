package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class Invoke(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.invokeVirtual(type.asm, methodType.asm)
    }
}
