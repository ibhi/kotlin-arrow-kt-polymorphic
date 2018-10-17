package com.example.polymorphic

import arrow.Kind
import arrow.effects.typeclasses.Async
import arrow.typeclasses.bindingCatch
import com.example.polymorphic.domain.Response

class Client<F>(
        val sessionService: SessionService<F>,
        val accountService: AccountService<F>,
        A: Async<F>
): Async<F> by A {
    fun getResponse(userId: String): Kind<F, Response> = async {
        bindingCatch {
            val session = sessionService.createSession(userId).bind()
            val accounts = accountService.getAccounts(userId).bind()
            Response(session, accounts)
        }
    }

}