package com.example.polymorphic

import arrow.Kind
import arrow.core.left
import arrow.core.right
import arrow.effects.typeclasses.Async

fun <F, A> runInAsyncContext(
        f: () -> A,
        AC: Async<F>
): Kind<F, A> {
    return AC.async { callback ->
//        something async will happen here
        try {
            callback(f().right())
        } catch (e: Exception) {
            callback(e.left())
        }
    }
}