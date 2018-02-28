package io.github.aedan.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Invokes the virtual method of the given [type] with the given [methodType].
 */
data class Invoke(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.invokeVirtual(type.asm, methodType.asm)
    }
}
