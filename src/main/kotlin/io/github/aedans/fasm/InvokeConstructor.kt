package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class InvokeConstructor(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.invokeConstructor(type.asm, methodType.asm)
    }
}
