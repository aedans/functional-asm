package io.github.aedans.fasm

import org.objectweb.asm.ClassWriter

import org.objectweb.asm.ClassWriter.COMPUTE_FRAMES
import org.objectweb.asm.Opcodes.V1_8
import java.io.File
import java.net.URLClassLoader
import java.nio.file.Files

/**
 * Class representing a class on the jvm.
 *
 * @param access       The access level of the class.
 * @param name         The name of the class.
 * @param signature    The signature of the class
 * @param declarations The declarations declared in the class.
 */
data class Class(
        val access: Access,
        val name: QualifiedName,
        val signature: ClassSignature,
        val declarations: List<Declaration>
) {
    constructor(
            access: Access,
            name: QualifiedName,
            generics: List<Generic>,
            superType: Type,
            interfaceTypes: Set<Type>,
            declarations: List<Declaration>
    ) : this(access, name, ClassSignature(generics, superType, interfaceTypes), declarations)

    /**
     * Generates this class, returning a byte array that represents the class file for this class.
     */
    fun generate(): ByteArray {
        val classWriter = ClassWriter(COMPUTE_FRAMES)

        classWriter.visit(
                V1_8,
                access.intValue,
                name.toPathString(),
                signature.internalString().let { if (it.isEmpty()) null else it },
                signature.superType.toQualifiedName().toPathString(),
                signature.interfaceTypes.map { it.toQualifiedName().toPathString() }.toTypedArray()
        )

        declarations.forEach {
            it.generate(classWriter)
        }

        classWriter.visitEnd()
        return classWriter.toByteArray()
    }

    /**
     * Writes the bytes of this class to a file in the given [directory].
     */
    fun generate(directory: File): File {
        val file = File(directory, "${name.toPathString()}.class")
        Files.write(file.toPath(), generate())
        return file
    }

    /**
     * Returns the java class for this class by creating a temporary file and loading it.
     */
    fun load(): java.lang.Class<*> {
        val directory = Files.createTempDirectory("functional_asm").toFile()
        val file = generate(directory)
        val urlClassLoader = URLClassLoader(arrayOf(directory.toURI().toURL()))
        val clazz = urlClassLoader.loadClass(name.toQualifiedString())
        file.delete()
        return clazz
    }
}
