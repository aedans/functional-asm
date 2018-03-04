package io.github.aedans.fasm

import java.lang.Class

/**
 * Class representing a type on the jvm.
 *
 * @param name       The simple java name of the type, e.g. "Class" or "void".
 * @param descriptor The internal descriptor of the type on the jvm, e.g. "Ljava/lang/Class;" or "V".
 * @param signature  The internal methodType of the type on the jvm, e.g. "Ljava/lang/Class<Ljava/lang/Object;>;" or "V".
 */
data class Type @JvmOverloads constructor(
        val name: String,
        val descriptor: String,
        val signature: String = descriptor
) {
    fun toQualifiedName() = QualifiedName.fromQualifiedString(name)

    internal val asm: org.objectweb.asm.Type get() = org.objectweb.asm.Type.getType(descriptor)

    companion object {
        /**
         * Creates a type from a class with the given [qualifiedName] with generic parameters [generics].
         */
        @JvmStatic
        @JvmOverloads
        fun clazz(
                qualifiedName: QualifiedName,
                generics: List<Type> = emptyList()
        ): Type {
            val params = if (generics.isEmpty()) "" else generics.joinToString("", "<", ">") { it.signature }
            val descriptorName = qualifiedName.toPathString()
            return Type(
                    qualifiedName.toQualifiedString(),
                    "L$descriptorName;",
                    "L$descriptorName$params;"
            )
        }

        /**
         * Creates a type from a class [clazz] with generic parameters [generics].
         */
        @JvmStatic
        @JvmOverloads
        fun clazz(
                clazz: Class<*>,
                generics: List<Type> = emptyList()
        ) = clazz(QualifiedName.fromClass(clazz), generics)

        /**
         * Creates a generic type with a given [name].
         */
        @JvmStatic
        fun generic(name: String) = Type(name, "T$name;")

        /**
         * Creates the type representing an array of [type]s.
         */
        @JvmStatic
        fun array(type: Type) = Type("${type.name}[]", "[${type.descriptor}")

        @JvmField
        val `object` = clazz(Object::class.java)

        @JvmField
        val string = clazz(String::class.java)

        @JvmField
        val void = Type("void", "V")

        @JvmField
        val boolean = Type("boolean", "Z")

        @JvmField
        val char = Type("char", "C")

        @JvmField
        val byte = Type("byte", "B")

        @JvmField
        val short = Type("short", "S")

        @JvmField
        val int = Type("int", "I")

        @JvmField
        val long = Type("long", "J")

        @JvmField
        val float = Type("float", "F")

        @JvmField
        val double = Type("double", "D")
    }
}
