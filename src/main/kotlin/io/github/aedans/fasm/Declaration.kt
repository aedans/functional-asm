package io.github.aedans.fasm

import org.objectweb.asm.ClassWriter

/**
 * A declaration for a class.
 */
interface Declaration {
    fun generate(classWriter: ClassWriter)
}
