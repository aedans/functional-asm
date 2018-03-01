package io.github.aedans.fasm

import org.objectweb.asm.commons.GeneratorAdapter

data class PushNew(val type: Type, val methodType: MethodType, val args: List<Operation>) : Operation {
    override fun generate(generatorAdapter: GeneratorAdapter) {
        New(type).generate(generatorAdapter)
        Dup.generate(generatorAdapter)
        args.forEach { it.generate(generatorAdapter) }
        InvokeConstructor(type, methodType).generate(generatorAdapter)
    }
}
