package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Invokes the static method of the given [type] with the given [methodType].
 */
data class InvokeStatic(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.invokeStatic(type.asm, methodType.asm)
    }
}
