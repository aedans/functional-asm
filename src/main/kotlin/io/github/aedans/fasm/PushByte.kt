package io.github.aedans.fasm

data class PushByte(val byte: Byte) : Operation by PushInt(byte.toInt())
