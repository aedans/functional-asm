package io.github.aedan.fasm

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.commons.GeneratorAdapter

/**
 * Class representing a method on the jvm.
 *
 * @param access     The access level of the method.
 * @param type       The type of the method.
 * @param operations The list of operations that make up the method.
 */
data class Method(
        val access: Access,
        val type: MethodType,
        val operations: List<Operation>
) : Declaration {
    constructor(
            access: Access,
            name: String,
            generics: List<Generic>,
            returnType: Type,
            paramTypes: List<Type>,
            exceptions: Set<Type>,
            operations: List<Operation>
    ) : this(access, MethodType(name, generics, returnType, paramTypes, exceptions), operations)

    override fun generate(classWriter: ClassWriter) {
        val generatorAdapter = GeneratorAdapter(
                access.intValue,
                type.asm,
                type.signature.internalString(),
                type.signature.exceptions.map { it.asm }.toTypedArray(),
                classWriter
        )

        operations.forEach {
            it.generate(generatorAdapter)
        }

        Return.generate(generatorAdapter)

        generatorAdapter.endMethod()
    }

    companion object {
        /**
         * Creates a default constructor with the given [access].
         */
        @JvmStatic
        @JvmOverloads
        fun defaultConstructor(access: Access = Access().asPublic()) = constructor(
                access,
                emptyList(),
                emptySet(),
                Type.`object`,
                MethodType("<init>", emptyList(), Type.void, emptyList(), emptySet()),
                emptyList()
        )

        /**
         * Creates a method for a constructor.
         */
        @JvmStatic
        fun constructor(
                access: Access,
                paramTypes: List<Type>,
                exceptions: Set<Type>,
                superType: Type,
                superSignature: MethodType,
                operations: List<Operation>
        ) = Method(
                access,
                "<init>",
                emptyList(),
                Type.void,
                paramTypes,
                exceptions,
                operations + listOf(
                        PushThis,
                        InvokeConstructor(superType, superSignature)
                )
        )
    }
}
