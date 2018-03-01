package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushNew(val type: Type, val methodType: MethodType) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        New(type).generate(generatorAdapter)
        Dup.generate(generatorAdapter)
        InvokeConstructor(type, methodType).generate(generatorAdapter)
    }
}
