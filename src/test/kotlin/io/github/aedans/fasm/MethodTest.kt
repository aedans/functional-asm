package io.github.aedans.fasm

import io.github.aedan.fasm.*
import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec
import java.io.*

class MethodTest : StringSpec() {
    init {
        fun clazz(method: Method) =
                ClassTest.test.copy(declarations = ClassTest.test.declarations + method)

        "Default constructor" {
            val clazz = clazz(test)
            val load = clazz.load()
            load.newInstance()
        }

        "Generics" {
            val clazz = clazz(test.copy(
                    type = test.type.copy(
                            signature = test.type.signature.copy(
                                    generics = listOf(Generic("T", setOf(Type.`object`, Type.clazz(Serializable::class.java))))
                            ))))
            val load = clazz.load()
            val method = load.getMethod("test")
            val typeParameters = method.typeParameters
            typeParameters.size shouldEqual 1
            typeParameters[0].name shouldEqual "T"
            val bounds = typeParameters[0].bounds
            bounds.size shouldEqual 2
            bounds[0].typeName shouldEqual "java.lang.Object"
            bounds[1].typeName shouldEqual "java.io.Serializable"
        }

        "Exceptions" {
            val clazz = clazz(test.copy(
                    type = test.type.copy(
                            signature = test.type.signature.copy(
                                    exceptions = setOf(Type.clazz(IOException::class.java), Type.clazz(NullPointerException::class.java))
                            ))))
            val load = clazz.load()
            val method = load.getMethod("test")
            val exceptions = method.exceptionTypes
            exceptions.size shouldEqual 2
            exceptions[0] shouldEqual IOException::class.java
            exceptions[1] shouldEqual NullPointerException::class.java
        }

        "Test" {
            val clazz = clazz(test)
            val load = clazz.load()
            load.getMethod("test").invoke(load.newInstance())
        }

        "Static" {
            val clazz = clazz(test.copy(access = test.access.asStatic()))
            val load = clazz.load()
            load.getMethod("test").invoke(null)
        }

        "PushThis" {
            val clazz = clazz(test.copy(
                    operations = listOf(PushThis, Return),
                    type = test.type.copy(
                            signature = test.type.signature.copy(
                                    returnType = Type.clazz(ClassTest.test.name)
                            )
                    )))
            val load = clazz.load()
            val instance = load.newInstance()
            load.getMethod("test").invoke(instance) shouldEqual instance
        }

        "PushArg" {
            val clazz = clazz(test.copy(
                    operations = listOf(PushArg(0), Return),
                    type = test.type.copy(
                            signature = test.type.signature.copy(
                                    returnType = Type.clazz(ClassTest.test.name),
                                    paramTypes = listOf(Type.clazz(ClassTest.test.name))
                            )
                    )))
            val load = clazz.load()
            val instance = load.newInstance()
            load.getMethod("test", load).invoke(instance, instance) shouldEqual instance
        }
    }

    companion object {
        val test = Method(
                Access().asPublic(),
                "test",
                emptyList(),
                Type.void,
                emptyList(),
                emptySet(),
                listOf(Return)
        )
    }
}
