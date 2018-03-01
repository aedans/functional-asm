package io.github.aedans.fasm

data class PushChar(val char: Char) : Operation by PushInt(char.toInt())
