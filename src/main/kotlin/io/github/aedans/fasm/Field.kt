package io.github.aedans.fasm

import org.objectweb.asm.ClassWriter

/**
 * Class representing a field on the jvm.
 *
 * @param access The access level of the field.
 * @param type   The type of the field.
 * @param name   The name of the field.
 * @param owner  The type of the owner of the field.
 */
data class Field(
        val access: Access,
        val type: Type,
        val name: String,
        val owner: Type
) : Declaration {
    override fun generate(classWriter: ClassWriter) {
        val fieldVisitor = classWriter.visitField(
                access.intValue,
                name,
                type.descriptor,
                type.signature,
                null
        )

        fieldVisitor.visitEnd()
    }
}
