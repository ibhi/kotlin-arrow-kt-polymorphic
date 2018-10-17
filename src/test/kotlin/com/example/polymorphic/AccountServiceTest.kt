package com.example.polymorphic

import arrow.effects.IO
import arrow.effects.async
import arrow.effects.fix
import org.junit.Test

class AccountServiceTest {

    @Test
    fun getAccounts() {
        val accountService = AccountService(IO.async())
        val accounts = accountService.getAccounts("1234")
                .fix()
                .attempt()
                .unsafeRunSync()
        println(accounts)
    }
}