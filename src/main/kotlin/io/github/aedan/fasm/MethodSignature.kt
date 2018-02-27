package io.github.aedan.fasm

/**
 * Class representing the methodType for a method.
 *
 * @param generics   The generic parameters of the method.
 * @param returnType The return type of the method
 * @param paramTypes The types of the parameters for the method
 * @param exceptions The set of declared exception types for the method.
 */
data class MethodSignature(
        val generics: List<Generic>,
        val returnType: Type,
        val paramTypes: List<Type>,
        val exceptions: Set<Type>
) {
    /**
     * Returns the string representation of this method methodType on the jvm.
     */
    fun internalString() = if (generics.isEmpty())
        ""
    else
        generics.joinToString("", "<", ">") {
            "${it.name}:${it.constraints.joinToString("", "", "") { ":${it.signature}" }}"
        } + paramTypes.joinToString("", "(", ")") { it.signature } +
                returnType.signature
}
