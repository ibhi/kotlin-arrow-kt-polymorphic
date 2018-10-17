package com.example.polymorphic

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.example.polymorphic.domain.Account

class AccountService<F>(val A: Async<F>): Async<F> by A {
    fun getAccounts(userId: String): Kind<F, List<Account>> = runInAsyncContext(
            f = { listOf(Account("1234", userId)) },
            AC = A
    )
}
