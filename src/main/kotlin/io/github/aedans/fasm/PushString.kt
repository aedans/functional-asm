package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushString(val string: String) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        generatorAdapter.push(string)
    }
}
