package aashishtathod.dev.plugins

import aashishtathod.dev.auth.JWTController
import aashishtathod.dev.auth.PasswordEncryptor
import aashishtathod.dev.controllers.AuthController
import aashishtathod.dev.controllers.NoteController
import aashishtathod.dev.data.daoImpl.NoteDaoImpl
import aashishtathod.dev.data.daoImpl.UserDaoImpl
import aashishtathod.dev.routes.AuthRoute
import aashishtathod.dev.routes.NoteRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        val userDao = UserDaoImpl()
        val encryptor = PasswordEncryptor()
        val jwtController = JWTController()
        val authController = AuthController(userDao,jwtController,encryptor)
        AuthRoute(authController)

        get("/hello") {
            call.respondText("Hello World!")
        }

        val noteDao = NoteDaoImpl()
        val noteController = NoteController(noteDao)
        NoteRoute(noteController)

    }
}
