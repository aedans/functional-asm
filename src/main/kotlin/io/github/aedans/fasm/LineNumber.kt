package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class LineNumber(val number: Int) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        val label = generatorAdapter.mark()
        generatorAdapter.visitLineNumber(number, label)
    }
}
