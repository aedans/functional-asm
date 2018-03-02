package io.github.aedans.fasm

import org.objectweb.asm.ClassWriter

/**
 * Declaration that marks the name of the source file for a class.
 *
 * @param name The name of the source file.
 */
data class ClassSource @JvmOverloads constructor(val name: String, val debug: String? = null) : Declaration {
    override fun generate(classWriter: ClassWriter) {
        classWriter.visitSource(name, debug)
    }
}
