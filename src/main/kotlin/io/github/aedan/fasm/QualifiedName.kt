package io.github.aedan.fasm

/**
 * Represents a name of the form "java.lang.Class".
 */
data class QualifiedName(val list: List<String>) {
    /**
     * The qualified of the qualified name, e.g. "java.lang".
     */
    val qualifier get() = list.dropLast(1)

    /**
     * The name of the qualified name, e.g. "Class".
     */
    val name get() = list.last()

    /**
     * Converts a qualified name to a string of the form "java/lang/Class".
     */
    fun toPathString() = list.joinToString(prefix = "", postfix = "", separator = "/")

    /**
     * Converts a qualified name to a string of the form "java.lang.Class".
     */
    fun toQualifiedString() = list.joinToString(prefix = "", postfix = "", separator = ".")

    companion object {
        /**
         * Returns the qualified name of a class.
         */
        @JvmStatic
        fun fromClass(clazz: java.lang.Class<*>) = fromQualifiedString(clazz.typeName)

        /**
         * Creates a qualified name from a [string] of the form "java/lang/String".
         */
        @JvmStatic
        fun fromPathString(string: String) = QualifiedName(string.split('/'))

        /**
         * Creates a qualified name from a [string] of the form "java.lang.String".
         */
        @JvmStatic
        fun fromQualifiedString(string: String) = QualifiedName(string.split('.'))
    }
}
