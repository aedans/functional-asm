package io.github.aedans.fasm

/**
 * Represents a generic type parameter.
 *
 * @param name        The name of the type parameter.
 * @param constraints The list of constraints for the type parameter.
 */
data class Generic(val name: String, val constraints: Set<Type>)
