package com.example.polymorphic

import arrow.Kind
import arrow.core.Try
import arrow.effects.typeclasses.Async

fun <F, A> runInAsyncContext(
        f: () -> A,
        AC: Async<F>
): Kind<F, A> {
    return AC.async { callback ->
//        something async will happen here
        callback(Try {
            f()
        }.toEither())
    }
}