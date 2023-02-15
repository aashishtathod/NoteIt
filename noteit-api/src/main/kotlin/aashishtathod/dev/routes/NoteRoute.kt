package aashishtathod.dev.routes

import aashishtathod.dev.auth.UserPrincipal
import aashishtathod.dev.controllers.NoteController
import aashishtathod.dev.utils.exceptions.FailureMessages
import aashishtathod.dev.utils.exceptions.UnauthorizedActivityException
import aashishtathod.dev.utils.requests.NoteRequest
import aashishtathod.dev.utils.requests.PinRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.NoteRoute(noteController: NoteController) {

    authenticate {
        get("/notes") {
            val principal = call.principal<UserPrincipal>()
                ?: throw UnauthorizedActivityException(FailureMessages.MESSAGE_ACCESS_DENIED)

            val noteResponse = noteController.getNotesByUser(principal.user)
            call.respond(HttpStatusCode.OK, noteResponse)
        }

        route("/note") {

            get("/{id}") {
                val noteId = call.parameters["id"] ?: return@get

                val principal = call.principal<UserPrincipal>()
                    ?: throw UnauthorizedActivityException(FailureMessages.MESSAGE_ACCESS_DENIED)

                val noteResponse = noteController.getNoteById(noteId.toInt())
                call.respond(HttpStatusCode.OK, noteResponse)
            }

            post("/new") {
                val principal = call.principal<UserPrincipal>()
                    ?: throw UnauthorizedActivityException(FailureMessages.MESSAGE_ACCESS_DENIED)

                val noteRequest = runCatching { call.receive<NoteRequest>() }.getOrElse {
                    throw BadRequestException(FailureMessages.MESSAGE_MISSING_CREDENTIALS)
                }
                val noteResponse = noteController.addNote(principal.user, noteRequest)
                call.respond(HttpStatusCode.OK, noteResponse)
            }

            put("/{id}") {
                val noteId = call.parameters["id"] ?: return@put

                val principal = call.principal<UserPrincipal>()
                    ?: throw UnauthorizedActivityException(FailureMessages.MESSAGE_ACCESS_DENIED)

                val noteRequest = runCatching { call.receive<NoteRequest>() }.getOrElse {
                    throw BadRequestException(FailureMessages.MESSAGE_MISSING_NOTE_DETAILS)
                }

                val noteResponse = noteController.updateNote(principal.user, noteId.toInt(), noteRequest)
                call.respond(HttpStatusCode.OK, noteResponse)
            }

            delete("/{id}") {
                val noteId = call.parameters["id"] ?: return@delete
                val principal = call.principal<UserPrincipal>()
                    ?: throw UnauthorizedActivityException(FailureMessages.MESSAGE_ACCESS_DENIED)

                val noteResponse = noteController.deleteNote(principal.user, noteId.toInt())

                call.respond(HttpStatusCode.OK, noteResponse)
            }

            patch("/{id}/pin") {
                val noteId = call.parameters["id"] ?: return@patch

                val pinRequest = runCatching { call.receive<PinRequest>() }.getOrElse {
                    throw BadRequestException(FailureMessages.MESSAGE_MISSING_PIN_DETAILS)
                }

                val principal = call.principal<UserPrincipal>()
                    ?: throw UnauthorizedActivityException(FailureMessages.MESSAGE_ACCESS_DENIED)

                val noteResponse = noteController.updateNotePin(principal.user, noteId.toInt(), pinRequest)

                call.respond(HttpStatusCode.OK, noteResponse)
            }


        }
    }
}