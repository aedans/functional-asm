package io.github.aedans.fasm

data class PushShort(val short: Short) : Operation by PushInt(short.toInt())
