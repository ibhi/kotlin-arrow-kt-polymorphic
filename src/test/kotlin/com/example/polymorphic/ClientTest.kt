package com.example.polymorphic

import arrow.effects.*
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

    @Test
    fun getResponseWithDeferredKTest() {
        val sessionService = SessionService(DeferredK.async())
        val accountService = AccountService(DeferredK.async())
        val client = Client(sessionService, accountService, DeferredK.async())

        val result = client.getResponse("123")
                .fix()
                .unsafeRunSync()
//                .unsafeRunTimed(Duration(1000, TimeUnit.MILLISECONDS))

        println("Success $result")
    }
}