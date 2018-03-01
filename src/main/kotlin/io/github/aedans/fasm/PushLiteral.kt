package io.github.aedans.fasm

data class PushLiteral(val literal: Any?) : Operation by when (literal) {
    null -> PushNull
    is Byte -> PushByte(literal)
    is Short -> PushShort(literal)
    is Int -> PushInt(literal)
    is Long -> PushLong(literal)
    is Float -> PushFloat(literal)
    is Double -> PushDouble(literal)
    is Char -> PushChar(literal)
    is Boolean -> PushBoolean(literal)
    is String -> PushString(literal)
    else -> throw RuntimeException("Invalid literal $literal")
}
