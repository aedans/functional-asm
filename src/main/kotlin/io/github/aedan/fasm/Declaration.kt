package io.github.aedan.fasm

import org.objectweb.asm.ClassWriter

/**
 * A declaration for a class.
 */
interface Declaration {
    fun generate(classWriter: ClassWriter)
}
