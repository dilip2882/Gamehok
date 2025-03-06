package com.dilip.gamehok.core.common.mapper

fun interface ResultMapper<T, R> {
    fun map(input: T): R
}