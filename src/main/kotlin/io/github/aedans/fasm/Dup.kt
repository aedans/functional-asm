package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

object Dup : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.dup()
    }
}
