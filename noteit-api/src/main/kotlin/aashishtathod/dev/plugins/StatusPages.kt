package aashishtathod.dev.plugins

import aashishtathod.dev.utils.exceptions.FailureMessages
import aashishtathod.dev.utils.responses.FailureResponse
import aashishtathod.dev.utils.responses.State
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<BadRequestException> { call, status  ->
            call.respond(HttpStatusCode.BadRequest, FailureResponse(State.FAILED.value, status .message ?: "Bad Request"))
        }

        status(HttpStatusCode.Unauthorized) { call, _ ->
            call.respond(HttpStatusCode.Unauthorized, FailureResponse(State.UNAUTHORIZED.value, FailureMessages.MESSAGE_ACCESS_DENIED))
        }
    }
}