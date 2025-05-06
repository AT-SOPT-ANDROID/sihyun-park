package org.sopt.at.remote

import org.sopt.at.data.service.UserService

object ServicePool {
    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }
}
