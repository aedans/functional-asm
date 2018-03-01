package io.github.aedans.fasm

import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec
import java.io.Serializable
import java.lang.Appendable

class ClassTest : StringSpec() {
    init {
        "Test" {
            val load = test.load()
            load.name shouldEqual test.name.toQualifiedString()
        }

        "Generics" {
            val clazz = test.copy(
                    signature = test.signature.copy(
                            generics = listOf(Generic("T", setOf(Type.`object`, Type.clazz(Serializable::class.java))))))
            val load = clazz.load()
            val typeParameters = load.typeParameters
            typeParameters.size shouldEqual 1
            typeParameters[0].name shouldEqual "T"
            val bounds = typeParameters[0].bounds
            bounds.size shouldEqual 2
            bounds[0].typeName shouldEqual "java.lang.Object"
            bounds[1].typeName shouldEqual "java.io.Serializable"
        }

        "Supertype" {
            val clazz = test.copy(
                    signature = test.signature.copy(
                            superType = Type.clazz(Throwable::class.java)))
            val load = clazz.load()
            load.superclass shouldEqual Throwable::class.java
        }

        "Interfaces" {
            val clazz = test.copy(
                    signature = test.signature.copy(
                            interfaceTypes = setOf(Type.clazz(Serializable::class.java), Type.clazz(Appendable::class.java))))
            val load = clazz.load()
            val interfaces = load.interfaces
            interfaces.size shouldEqual 2
            interfaces[0] shouldEqual Serializable::class.java
            interfaces[1] shouldEqual Appendable::class.java
        }
    }

    companion object {
        val test = Class(
                Access().asPublic(),
                QualifiedName.fromQualifiedString("Test"),
                emptyList(),
                Type.`object`,
                emptySet(),
                listOf(Method.defaultConstructor())
        )
    }
}
