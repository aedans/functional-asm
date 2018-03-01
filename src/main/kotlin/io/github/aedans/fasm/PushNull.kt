package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

object PushNull : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(null as String?)
    }
}
