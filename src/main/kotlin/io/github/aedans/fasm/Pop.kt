package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

object Pop : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.pop()
    }
}
