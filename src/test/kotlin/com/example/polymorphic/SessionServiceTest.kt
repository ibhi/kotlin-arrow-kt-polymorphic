package com.example.polymorphic

import arrow.effects.IO
import arrow.effects.async
import arrow.effects.fix
import org.junit.Test

class SessionServiceTest {
    @Test
    fun createSession() {
        val sessionService = SessionService(IO.async())
        val session = sessionService.createSession("1234")
                .fix()
                .attempt()
                .unsafeRunSync()
        println(session)
    }
}