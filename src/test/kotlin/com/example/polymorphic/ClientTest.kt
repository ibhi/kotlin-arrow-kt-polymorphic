package com.example.polymorphic

import arrow.effects.IO
import arrow.effects.async
import arrow.effects.fix
import arrow.effects.typeclasses.Duration
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class ClientTest {

    @Test
    fun getResponseTest() {
        val async = IO.async()
        val sessionService = SessionService(IO.async())
        val accountService = AccountService(IO.async())
        val client = Client(sessionService, accountService, IO.async())

        val result = client.getResponse("123")
                .fix()
                .attempt()
                .unsafeRunSync()
//                .unsafeRunTimed(Duration(1000, TimeUnit.MILLISECONDS))

        println("Success $result")
    }
}