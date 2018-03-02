package io.github.aedans.fasm

/**
 * Class representing the methodType for a class
 *
 * @param generics       The generic arguments of the class.
 * @param superType      The type of the class the class extends.
 * @param interfaceTypes The types of the interfaces the class implements.
 */
data class ClassSignature(
        val generics: List<Generic>,
        val superType: Type,
        val interfaceTypes: Set<Type>
) {
    /**
     * Returns the string representation of this class methodType on the jvm.
     */
    fun internalString() = when {
        generics.isEmpty() -> ""
        else -> generics.joinToString("", "<", ">") {
            "${it.name}:${it.constraints.joinToString("", "", "") { ":${it.descriptor}" }}"
        }
    } + superType.signature + interfaceTypes.joinToString("", "", "") { it.signature }
}
