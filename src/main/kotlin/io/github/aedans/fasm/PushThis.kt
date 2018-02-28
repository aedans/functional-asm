package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Pushes the enclosing class to the top of the stack.
 */
object PushThis : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.loadThis()
    }
}
