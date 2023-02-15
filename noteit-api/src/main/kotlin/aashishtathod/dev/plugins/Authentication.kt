package aashishtathod.dev.plugins

import aashishtathod.dev.auth.JWTController
import aashishtathod.dev.auth.UserPrincipal
import aashishtathod.dev.data.daoImpl.UserDaoImpl
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.logging.Logger

fun Application.configureAuthentication() {
    val jwtController = JWTController()
    val userDao = UserDaoImpl()

    install(Authentication) {
        jwt {
            verifier(jwtController.verifier)
            validate {
                val userId = it.payload.getClaim(JWTController.ClAIM).asInt()

                val user = userDao.findByUserId(userId)

                if (user != null) UserPrincipal(user) else null
            }
        }
    }
}