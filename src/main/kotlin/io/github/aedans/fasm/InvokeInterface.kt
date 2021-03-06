package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class InvokeInterface(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.invokeInterface(type.asm, methodType.asm)
    }
}
