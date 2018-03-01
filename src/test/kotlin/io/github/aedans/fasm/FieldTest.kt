package io.github.aedans.fasm

import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class FieldTest : StringSpec() {
    init {
        fun clazz(field: Field) =
                ClassTest.test.copy(declarations = ClassTest.test.declarations + field)

        "Test" {
            val clazz = clazz(test)
            val load = clazz.load()
            load.getField("test").get(load.newInstance()) shouldEqual null
        }

        "Static" {
            val clazz = clazz(test.copy(access = test.access.asStatic()))
            val load = clazz.load()
            load.getField("test").get(null) shouldEqual null
        }
    }

    companion object {
        val test = Field(
                Access().asPublic(),
                Type.`object`,
                "test",
                Type.clazz(QualifiedName.fromQualifiedString("Test"))
        )
    }
}
