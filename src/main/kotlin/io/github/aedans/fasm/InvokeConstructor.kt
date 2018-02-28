package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Invokes the constructor of the given [type] with the given [methodType].
 */
data class InvokeConstructor(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.invokeConstructor(type.asm, methodType.asm)
    }
}
