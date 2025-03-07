package com.dilip.gamehok.core.common.functional

fun Boolean?.orDefault(default : Boolean = false) : Boolean = this ?: default

fun <T,R : Any> List<T>?.mapOrDefault(defaultListValue : List<R> = emptyList(), transform: (T) -> R): List<R> {
    return this?.filterNotNull()?.map(transform) ?: defaultListValue
}