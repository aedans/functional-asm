package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

/**
 * A operation for a method.
 */
interface Operation {
    fun generate(generatorAdapter: GeneratorAdapter)
}
