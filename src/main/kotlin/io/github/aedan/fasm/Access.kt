package io.github.aedan.fasm

import org.objectweb.asm.Opcodes.*

/**
 * Represents the access flags for a declaration.
 */
data class Access @JvmOverloads constructor(@JvmField internal val intValue: Int = 0) {
    fun with(int: Int) = copy(intValue = intValue + int)

    fun asPublic() = with(ACC_PUBLIC)
    fun asPrivate() = with(ACC_PRIVATE)
    fun asProtected() = with(ACC_PROTECTED)
    fun asStatic() = with(ACC_STATIC)
    fun asFinal() = with(ACC_FINAL)
    fun asSuper() = with(ACC_SUPER)
    fun asSynchronized() = with(ACC_SYNCHRONIZED)
    fun asVolatile() = with(ACC_VOLATILE)
    fun asBridge() = with(ACC_BRIDGE)
    fun asVararg() = with(ACC_VARARGS)
    fun asTransient() = with(ACC_TRANSIENT)
    fun asNative() = with(ACC_NATIVE)
    fun asInterface() = with(ACC_INTERFACE)
    fun asAbstract() = with(ACC_ABSTRACT)
    fun asStrict() = with(ACC_STRICT)
    fun asSynthetic() = with(ACC_SYNTHETIC)
    fun asAnnotation() = with(ACC_ANNOTATION)
    fun asEnum() = with(ACC_ENUM)
    fun asMandated() = with(ACC_MANDATED)
    fun asDeprecated() = with(ACC_DEPRECATED)
}
