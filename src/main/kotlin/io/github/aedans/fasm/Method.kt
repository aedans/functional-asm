package io.github.aedans.fasm

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
                type.signature.internalString().let { if (it.isEmpty()) null else it },
                type.signature.exceptions.map { it.asm }.toTypedArray(),
                classWriter
        )

        operations.forEach {
            it.generate(generatorAdapter)
        }

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
                MethodSignature(emptyList(), Type.void, emptyList(), emptySet()),
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
                superSignature: MethodSignature,
                operations: List<Operation>
        ) = Method(
                access,
                "<init>",
                emptyList(),
                Type.void,
                paramTypes,
                exceptions,
                operations + PushThis + InvokeConstructor(superType, MethodType("<init>", superSignature)) + Return
        )

        /**
         * Creates a method that is called upon the static initialization of an object.
         */
        @JvmStatic
        fun staticInit(operations: List<Operation>) = Method(
                Access().asPublic().asStatic(),
                "<clinit>",
                emptyList(),
                Type.void,
                emptyList(),
                emptySet(),
                operations + Return
        )
    }
}
