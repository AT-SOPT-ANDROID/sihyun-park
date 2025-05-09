package org.sopt.at.remote

import org.sopt.at.data.service.AuthService
import org.sopt.at.data.service.UserService

object ServicePool {
    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }

    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}
