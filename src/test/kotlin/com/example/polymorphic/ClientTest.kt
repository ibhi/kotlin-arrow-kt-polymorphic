package com.example.polymorphic

import arrow.effects.IO
import arrow.effects.async
import arrow.effects.fix
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClientTest {

    @Test
    fun getResponseTest() {
        val sessionService = SessionService(IO.async())
        val accountService = AccountService(IO.async())
        val client = Client(sessionService, accountService, IO.async())

        client.getResponse("123")
                .fix()
                .unsafeRunAsync {either ->
                    either.fold(
                            {println("Success $it") },
                            { println("Failed $it") }
                    )
                }
    }
}