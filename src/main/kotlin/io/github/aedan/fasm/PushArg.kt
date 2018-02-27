package io.github.aedan.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Pushes the argument with the given [index].
 */
data class PushArg(val index: Int) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.loadArg(index)
    }
}
