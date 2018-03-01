package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

object Return : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.returnValue()
    }
}
