package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

object PushThis : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.loadThis()
    }
}
