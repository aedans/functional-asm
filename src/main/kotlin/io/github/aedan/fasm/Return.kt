package io.github.aedan.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Represents the operation of returning the top argument of the stack, or none if the stack is empty.
 */
object Return : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.returnValue()
    }
}
