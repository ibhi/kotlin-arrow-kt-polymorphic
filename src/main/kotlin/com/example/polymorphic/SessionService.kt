package com.example.polymorphic

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.example.polymorphic.domain.Session

class SessionService<F>(val A: Async<F>): Async<F> by A {
    fun createSession(userId: String): Kind<F, Session> = runInAsyncContext(
            f = { Session("123", userId) },
            AC = A
    )
}